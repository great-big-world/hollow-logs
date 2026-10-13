package dev.creoii.greatbigworld.hollowlogs.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import dev.creoii.greatbigworld.hollowlogs.platform.HollowLogsPlatformUtil;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.network.ServerGamePacketListenerImpl;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerGamePacketListenerImpl.class)
public class ServerGamePacketListenerImplMixin {
    @Shadow public ServerPlayer player;

    @ModifyExpressionValue(method = "handlePlayerPositionChange", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/level/ServerPlayer;isInPostImpulseGraceTime()Z"))
    private boolean gbw$allowHollowLogMovement(boolean original) {
        if (original) {
            return true;
        }
        return HollowLogsPlatformUtil.getPlayerEnteringHollowLog(player);
    }

    @Inject(method = "handlePlayerPositionChange", at = @At("TAIL"))
    private void gbw$clearEnteringHollowLogAttachment(double requestedX, double requestedY, double requestedZ, float requestedYRot, float requestedXRot, boolean isOnGround, boolean horizontalCollision, CallbackInfo ci) {
        HollowLogsPlatformUtil.removePlayerEnteringHollowLog(player);
    }
}
