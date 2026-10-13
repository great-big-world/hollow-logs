package dev.creoii.greatbigworld.hollowlogs.mixin;

import dev.creoii.greatbigworld.hollowlogs.util.AdjacentCollision;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Optional;
import java.util.function.Predicate;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.border.WorldBorder;
import net.minecraft.world.phys.Vec3;

@Mixin(Entity.class)
public abstract class EntityMixin {
    @Inject(method = "move", at = @At(value = "INVOKE", target = "Lnet/minecraft/util/profiling/ProfilerFiller;pop()V", ordinal = 2))
    private void gbw$collideAdjacentBlock(MoverType movementType, Vec3 movement, CallbackInfo ci) {
        Entity entity = (Entity) (Object) this;
        Optional<BlockPos> optionalPos = findClosestMatch(entity.blockPosition(), (int) (entity.getBbWidth() + .5f), (int) (entity.getBbHeight() + .5f), pos -> {
            return entity.level().getBlockState(pos).getBlock() instanceof AdjacentCollision;
        });
        if (optionalPos.isPresent()) {
            BlockPos pos = optionalPos.get();
            WorldBorder worldBorder = entity.level().getWorldBorder();
            if (!worldBorder.isWithinBounds(pos))
                return;
            BlockState state = entity.level().getBlockState(pos);
            if (state.getBlock() instanceof AdjacentCollision adjacentCollision) {
                adjacentCollision.entityAdjacent(entity, state, pos);
            }
        }
    }

    private static Optional<BlockPos> findClosestMatch(BlockPos blockPos, int i, int j, Predicate<BlockPos> predicate) {
        for(BlockPos blockPos2 : BlockPos.withinClippedManhattan(blockPos, i, j, i)) {
            if (predicate.test(blockPos2)) {
                return Optional.of(blockPos2);
            }
        }

        return Optional.empty();
    }
}
