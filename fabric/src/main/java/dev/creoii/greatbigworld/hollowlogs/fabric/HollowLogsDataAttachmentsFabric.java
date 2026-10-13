package dev.creoii.greatbigworld.hollowlogs.fabric;

import dev.creoii.greatbigworld.hollowlogs.HollowLogs;
import net.fabricmc.fabric.api.attachment.v1.AttachmentRegistry;
import net.fabricmc.fabric.api.attachment.v1.AttachmentSyncPredicate;
import net.fabricmc.fabric.api.attachment.v1.AttachmentType;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.resources.Identifier;

public final class HollowLogsDataAttachmentsFabric {
    public static final AttachmentType<Boolean> PLAYER_ENTERING_HOLLOW_LOG = AttachmentRegistry.create(Identifier.fromNamespaceAndPath(HollowLogs.GBW, "player_entering_hollow_log"), builder -> builder.initializer(() -> false).syncWith(ByteBufCodecs.BOOL, AttachmentSyncPredicate.targetOnly()));
}
