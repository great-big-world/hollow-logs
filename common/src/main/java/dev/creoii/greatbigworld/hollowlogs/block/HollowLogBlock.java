package dev.creoii.greatbigworld.hollowlogs.block;

import dev.creoii.greatbigworld.hollowlogs.util.AdjacentCollision;
import dev.creoii.greatbigworld.hollowlogs.platform.HollowLogsPlatformUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.*;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jspecify.annotations.Nullable;

public class HollowLogBlock extends RotatedPillarBlock implements SimpleWaterloggedBlock, AdjacentCollision {
    private static final VoxelShape X_SHAPE = Shapes.or(Block.box(0d, 0d, 0d, 16d, 16d, 3d), Block.box(0d, 13d, 0d, 16d, 16d, 16d), Block.box(0d, 0d, 13d, 16d, 16d, 16d), Block.box(0d, 0d, 0d, 16d, 3d, 16d));
    private static final VoxelShape Y_SHAPE = Shapes.or(Block.box(0d, 0d, 0d, 16d, 16d, 3d), Block.box(0d, 0d, 0d, 3d, 16d, 16d), Block.box(0d, 0d, 13d, 16d, 16d, 16d), Block.box(13d, 0d, 0d, 16d, 16d, 16d));
    private static final VoxelShape Z_SHAPE = Shapes.or(Block.box(13d, 0d, 0d, 16d, 16d, 16d), Block.box(0d, 0d, 0d, 3d, 16d, 16d), Block.box(0d, 13d, 0d, 16d, 16d, 16d), Block.box(0d, 0d, 0d, 16d, 3d, 16d));
    private static final VoxelShape X_COLLISION_SHAPE = Shapes.or(Block.box(0d, 0d, 0d, 16d, 16d, 2d), Block.box(0d, 13.75d, 0d, 16d, 16d, 16d), Block.box(0d, 0d, 14d, 16d, 16d, 16d), Block.box(0d, 0d, 0d, 16d, 2.25d, 16d));
    private static final VoxelShape Y_COLLISION_SHAPE = Shapes.or(Block.box(0d, 0d, 0d, 16d, 16d, 2.25d), Block.box(0d, 0d, 0d, 2.25d, 16d, 16d), Block.box(0d, 0d, 13.75d, 16d, 16d, 16d), Block.box(13.75d, 0d, 0d, 16d, 16d, 16d));
    private static final VoxelShape Z_COLLISION_SHAPE = Shapes.or(Block.box(14d, 0d, 0d, 16d, 16d, 16d), Block.box(0d, 0d, 0d, 2d, 16d, 16d), Block.box(0d, 13.75d, 0d, 16d, 16d, 16d), Block.box(0d, 0d, 0d, 16d, 2.25d, 16d));
    private static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

    public HollowLogBlock(Properties settings) {
        super(settings);
        registerDefaultState(getStateDefinition().any().setValue(AXIS, Direction.Axis.Y).setValue(WATERLOGGED, false));
    }

    public ItemStack pickupBlock(final @Nullable LivingEntity user, final LevelAccessor level, final BlockPos pos, final BlockState state) {
        if (state.getValue(BlockStateProperties.WATERLOGGED)) {
            level.setBlockAndUpdate(pos, state.setValue(BlockStateProperties.WATERLOGGED, false));
            if (!state.canSurvive(level, pos)) {
                level.destroyBlock(pos, true);
            }

            return new ItemStack(Items.WATER_BUCKET);
        } else return ItemStack.EMPTY;
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
        return switch (state.getValue(AXIS)) {
            case X -> X_SHAPE;
            case Y -> Y_SHAPE;
            case Z -> Z_SHAPE;
        };
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
        return switch (state.getValue(AXIS)) {
            case X -> X_COLLISION_SHAPE;
            case Y -> Y_COLLISION_SHAPE;
            case Z -> Z_COLLISION_SHAPE;
        };
    }

    @Override
    public VoxelShape getInteractionShape(BlockState state, BlockGetter world, BlockPos pos) {
        return Shapes.block();
    }

    @Override
    public FluidState getFluidState(BlockState state) {
        if (state.getValue(WATERLOGGED)) {
            return Fluids.WATER.getSource(false);
        }
        return super.getFluidState(state);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(AXIS, WATERLOGGED);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext ctx) {
        return defaultBlockState().setValue(AXIS, ctx.getClickedFace().getAxis()).setValue(WATERLOGGED, ctx.getLevel().getFluidState(ctx.getClickedPos()).is(Fluids.WATER));
    }

    @Override
    protected boolean isPathfindable(BlockState state, PathComputationType type) {
        return false;
    }

    @Override
    public void entityAdjacent(Entity entity, BlockState state, BlockPos pos) {
        if (!(entity instanceof Player player) || entity.level().isClientSide() || !entity.isSprinting() || entity.isVehicle() || entity.isPassenger() || entity.isVisuallyCrawling())
            return;
        BlockPos difference = pos.subtract(entity.blockPosition());
        if (Math.abs(difference.getY()) > .1d || difference.equals(BlockPos.ZERO))
            return;
        Vec3 bottomCenter = Vec3.atBottomCenterOf(pos);
        Vec3 vecDifference = bottomCenter.subtract(entity.position());

        boolean canEnter = switch (state.getValue(AXIS)) {
            case Y -> false;
            case X -> Math.abs(vecDifference.z) < .1d && difference.getX() == entity.getDirection().getStepX();
            case Z -> Math.abs(vecDifference.x) < .1d && difference.getZ() == entity.getDirection().getStepZ();
        };

        if (canEnter) {
            player.setPose(Pose.SWIMMING);
            player.setSwimming(true);

            Vec3 direction = entity.position().subtract(bottomCenter).normalize().scale(.5d);
            Vec3 destPos = bottomCenter.add(direction.x, .2d, direction.z);
            player.setPos(destPos.x, destPos.y, destPos.z);

            HollowLogsPlatformUtil.setPlayerEnteringHollowLog(player, true);
        }
    }
}
