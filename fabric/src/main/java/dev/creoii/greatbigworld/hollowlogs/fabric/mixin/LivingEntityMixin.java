package dev.creoii.greatbigworld.hollowlogs.fabric.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import dev.creoii.greatbigworld.hollowlogs.block.HollowLogBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import org.apache.commons.lang3.mutable.MutableBoolean;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Optional;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends Entity {
    @Shadow private Optional<BlockPos> lastClimbablePos;
    @Shadow public abstract EntityDimensions getDimensions(Pose pose);

    public LivingEntityMixin(EntityType<?> type, Level world) {
        super(type, world);
    }

    @Inject(method = "onClimbable", at = @At(value = "RETURN", ordinal = 4), cancellable = true)
    private void gbw$livingClimbHollowLogs(CallbackInfoReturnable<Boolean> cir, @Local(name = "ladderCheckPos") BlockPos ladderCheckPos) {
        MutableBoolean canClimbHollowLog = new MutableBoolean(false);
        BlockPos.betweenClosedStream(getDimensions(Pose.STANDING).makeBoundingBox(Vec3.atCenterOf(ladderCheckPos))).forEach(pos -> {
            BlockState state = level().getBlockState(pos);
            if (state.getBlock() instanceof HollowLogBlock && state.getValue(HollowLogBlock.AXIS) == Direction.Axis.Y) {
                canClimbHollowLog.setTrue();
            }
        });

        if (canClimbHollowLog.booleanValue()) {
            lastClimbablePos = Optional.of(ladderCheckPos);
            cir.setReturnValue(true);
        }
    }
}
