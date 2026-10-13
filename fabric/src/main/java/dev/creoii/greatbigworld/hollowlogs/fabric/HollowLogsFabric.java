package dev.creoii.greatbigworld.hollowlogs.fabric;

import dev.creoii.greatbigworld.hollowlogs.HollowLogs;
import net.fabricmc.api.ModInitializer;

public final class HollowLogsFabric implements ModInitializer {

    @Override
    public void onInitialize() {
        HollowLogs.init();

    }
}
