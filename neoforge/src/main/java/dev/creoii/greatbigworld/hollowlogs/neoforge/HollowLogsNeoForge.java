package dev.creoii.greatbigworld.hollowlogs.neoforge;

import dev.creoii.greatbigworld.hollowlogs.HollowLogs;
import dev.creoii.greatbigworld.hollowlogs.neoforge.config.HollowLogsConfig;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.neoforge.client.gui.ConfigurationScreen;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;

@Mod("hollow_logs")
public final class HollowLogsNeoForge {
    public HollowLogsNeoForge(ModContainer container) {
        HollowLogs.init();

        container.registerConfig(ModConfig.Type.SYNCED, HollowLogsConfig.CONFIG_SPEC);
        container.registerExtensionPoint(IConfigScreenFactory.class, ConfigurationScreen::new);

        HollowLogsDataAttachmentsNeoForge.ATTACHMENT_TYPES.register(container.getEventBus());
    }
}
