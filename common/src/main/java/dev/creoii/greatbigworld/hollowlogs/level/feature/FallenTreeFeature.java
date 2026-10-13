package dev.creoii.greatbigworld.hollowlogs.level.feature;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import dev.creoii.greatbigworld.hollowlogs.HollowLogsTags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.*;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.TreeFeature;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecorator;
import org.jetbrains.annotations.Nullable;

import java.util.*;
import java.util.function.BiConsumer;

public record FallenTreeFeature(Holder<BlockStateProvider> stumpState, Holder<BlockStateProvider> state, Optional<Holder<BlockStateProvider>> leafState, IntProvider stumpToTrunkGap, IntProvider length, FloatProvider stumpChance, FloatProvider leafChance, FloatProvider leafDensity, List<TreeDecorator> stumpDecorators, List<TreeDecorator> logDecorators, boolean adjustForNether) implements Feature {
    public static final MapCodec<FallenTreeFeature> CODEC = RecordCodecBuilder.mapCodec(instance -> {
        return instance.group(BlockStateProvider.CODEC.fieldOf("stump_state").forGetter(config -> {
            return config.stumpState;
        }), BlockStateProvider.CODEC.fieldOf("state").forGetter(config -> {
            return config.state;
        }), BlockStateProvider.CODEC.optionalFieldOf("leaf_state").orElse(Optional.empty()).forGetter(config -> {
            return Optional.ofNullable(config.state);
        }), IntProviders.codec(1, 16).fieldOf("stump_to_trunk_gap").orElse(ConstantInt.of(1)).forGetter(config -> {
            return config.stumpToTrunkGap;
        }), IntProviders.codec(1, 16).fieldOf("length").forGetter(config -> {
            return config.length;
        }), FloatProviders.codec(0f, 1f).fieldOf("stump_chance").orElse(ConstantFloat.of(1f)).forGetter(config -> {
            return config.stumpChance;
        }), FloatProviders.codec(0f, 1f).fieldOf("leaf_chance").orElse(ConstantFloat.ZERO).forGetter(config -> {
            return config.leafChance;
        }), FloatProviders.codec(0f, 1f).fieldOf("leaf_density").orElse(ConstantFloat.ZERO).forGetter(config -> {
            return config.leafDensity;
        }), TreeDecorator.CODEC.listOf().fieldOf("stump_decorators").orElse(List.of()).forGetter(config -> {
            return config.stumpDecorators;
        }), TreeDecorator.CODEC.listOf().fieldOf("log_decorators").orElse(List.of()).forGetter(config -> {
            return config.logDecorators;
        }), Codec.BOOL.fieldOf("adjust_for_nether").orElse(false).forGetter(config -> {
            return config.adjustForNether;
        })).apply(instance, FallenTreeFeature::new);
    });

    @Override
    public MapCodec<FallenTreeFeature> codec() {
        return CODEC;
    }

    @Override
    public boolean place(WorldGenLevel level, ChunkGenerator chunkGenerator, RandomSource random, BlockPos origin) {
        BlockState stumpState = stumpState().value().getState(level, random, origin);
        int stumpToLogGap = stumpToTrunkGap().sample(random) + 1;
        Direction direction = Direction.Plane.HORIZONTAL.getRandomDirection(random);

        BlockPos start;

        if (adjustForNether) {
            start = findNetherHeight(level, origin.relative(direction, stumpToLogGap));
        } else start = level.getHeightmapPos(Heightmap.Types.OCEAN_FLOOR, origin.relative(direction, stumpToLogGap));

        BlockState state = state().value().getState(level, random, start);
        @Nullable BlockState leafState = leafState().isPresent() ? leafState().get().value().getState(level, random, start) : null;
        int length = Math.max(1, length().sample(random));
        float stumpChance = stumpChance().sample(random);
        float leafChance = leafChance().sample(random);
        float leafDensity = leafDensity().sample(random);

        boolean trunkTooHigh = start.getY() - origin.getY() > 3; // Don't generate trunk if it is 3 or more blocks higher than the base position

        if (state.hasProperty(BlockStateProperties.AXIS)) {
            state = state.setValue(BlockStateProperties.AXIS, direction.getAxis());
        } else if (state.hasProperty(BlockStateProperties.HORIZONTAL_AXIS) && direction.getAxis().isHorizontal()) {
            state = state.setValue(BlockStateProperties.HORIZONTAL_AXIS, direction.getAxis());
        } else if (state.hasProperty(BlockStateProperties.FACING)) {
            state = state.setValue(BlockStateProperties.FACING, direction);
        } else if (state.hasProperty(BlockStateProperties.HORIZONTAL_FACING) && direction.getAxis().isHorizontal()) {
            state = state.setValue(BlockStateProperties.HORIZONTAL_FACING, direction);
        }

        Set<BlockPos> placementPositions = new HashSet<>();
        boolean unstable = false;

        int maxLength = length;
        for (int i = 0, j = (length / 2) - 1; i < maxLength; ++i) {
            BlockPos pos = start.relative(direction, i);

            if (!level.hasChunkAt(pos) || !level.hasChunkAt(pos.below())) {
                break;
            }

            BlockState downState = level.getBlockState(pos.below());
            if (downState.is(HollowLogsTags.DOES_NOT_SUPPORT_FALLEN_TREES)) {
                --j;
            } else if (j <= 0) {
                j = (length / 2) - 1;
                unstable = false;
            }

            if (!TreeFeature.validTreePos(level, pos)) {
                break;
            }

            placementPositions.add(pos);

            if (j <= 0) {
                unstable = true;
                maxLength = (int) (length * 1.2f) - 1; // allow for a bit more reach if unstable
            }
        }

        if (unstable || placementPositions.isEmpty()) {
            return false;
        }

        if (random.nextFloat() < stumpChance) {
            level.setBlockAndUpdate(origin, stumpState);
            decorateLogs(level, random, Set.of(origin), stumpDecorators);
        }

        if (trunkTooHigh) return true;

        boolean placeLeaves = random.nextFloat() < leafChance && leafState != null;

        int i = 0;
        for (BlockPos pos : placementPositions) {
            if (state.hasProperty(BlockStateProperties.WATERLOGGED)) {
                state = state.setValue(BlockStateProperties.WATERLOGGED, level.isWaterAt(pos));
            }
            level.setBlockAndUpdate(pos, state);

            if (placeLeaves) {
                for (Direction direction1 : Direction.values()) {
                    if (direction1.getAxis() == direction.getAxis())
                        continue;

                    BlockPos offset = pos.relative(direction1);
                    BlockState offsetState = level.getBlockState(offset);
                    if ((offsetState.isAir() || offsetState.canBeReplaced()) && !level.isWaterAt(offset)) {
                        if (i < placementPositions.size() * .3333f && random.nextFloat() < leafDensity) {
                            if (leafState.hasProperty(LeavesBlock.DISTANCE)) {
                                leafState = leafState.setValue(LeavesBlock.DISTANCE, 1);
                            }
                            level.setBlock(offset, leafState, 19);
                        }
                    }
                }
            }

            ++i;
        }

        decorateLogs(level, random, placementPositions, logDecorators);

        return !placementPositions.isEmpty();
    }

    private BlockPos findNetherHeight(WorldGenLevel level, BlockPos relative) {
        BlockPos.MutableBlockPos mutable = relative.mutable();

        while (mutable.getY() > level.getMinY()) {
            if (TreeFeature.validTreePos(level, mutable)) {
                return mutable.immutable();
            }
            mutable.move(Direction.DOWN);
        }

        mutable.set(relative);
        while (mutable.getY() < level.getMaxY() - 1) {
            if (TreeFeature.validTreePos(level, mutable)) {
                return mutable.immutable();
            }
            mutable.move(Direction.UP);
        }
        return relative;
    }

    private void decorateLogs(final WorldGenLevel level, final RandomSource random, final Set<BlockPos> logs, final List<TreeDecorator> decorators) {
        if (!decorators.isEmpty()) {
            TreeDecorator.Context decoratorContext = new TreeDecorator.Context(level, getDecorationSetter(level), random, logs, Set.of(), Set.of());
            decorators.forEach(decorator -> decorator.place(decoratorContext));
        }
    }

    private BiConsumer<BlockPos, BlockState> getDecorationSetter(final WorldGenLevel level) {
        return (pos, state) -> level.setBlock(pos, state, 19);
    }
}
