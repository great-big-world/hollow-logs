package dev.creoii.greatbigworld.hollowlogs.util;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.block.state.BlockState;

public interface AdjacentCollision {
    default void entityAdjacent(Entity entity, BlockState state, BlockPos pos) {
    }
}
