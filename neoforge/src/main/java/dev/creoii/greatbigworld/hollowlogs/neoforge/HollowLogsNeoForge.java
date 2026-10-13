package dev.creoii.greatbigworld.hollowlogs.neoforge;

import dev.creoii.greatbigworld.hollowlogs.HollowLogs;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;

@Mod("hollow_logs")
public final class HollowLogsNeoForge {
    public HollowLogsNeoForge(ModContainer container) {
        HollowLogs.init();


        HollowLogsDataAttachmentsNeoForge.ATTACHMENT_TYPES.register(container.getEventBus());
    }
}
