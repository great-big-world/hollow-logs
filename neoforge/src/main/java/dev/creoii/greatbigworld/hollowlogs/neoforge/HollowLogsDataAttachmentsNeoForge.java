package dev.creoii.greatbigworld.hollowlogs.neoforge;

import dev.creoii.greatbigworld.hollowlogs.HollowLogs;
import net.minecraft.network.codec.ByteBufCodecs;
import net.neoforged.neoforge.attachment.AttachmentType;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import java.util.function.Supplier;

public final class HollowLogsDataAttachmentsNeoForge {
    public static final DeferredRegister<AttachmentType<?>> ATTACHMENT_TYPES = DeferredRegister.create(NeoForgeRegistries.ATTACHMENT_TYPES, HollowLogs.NAMESPACE);

    public static final Supplier<AttachmentType<Boolean>> PLAYER_ENTERING_HOLLOW_LOG = ATTACHMENT_TYPES.register("player_entering_hollow_log", () -> AttachmentType.builder(() -> false).sync(ByteBufCodecs.BOOL).build());
}
