package dev.creoii.greatbigworld.hollowlogs.fabric;

import dev.creoii.greatbigworld.hollowlogs.HollowLogs;
import dev.creoii.greatbigworld.hollowlogs.fabric.config.HollowLogsConfig;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;
import org.jspecify.annotations.Nullable;

public final class HollowLogsFabric implements ModInitializer {

    @Override
    public void onInitialize() {
        HollowLogs.init();

    }
}
