package dev.creoii.greatbigworld.hollowlogs.fabric.config;

import dev.creoii.greatbigworld.hollowlogs.fabric.HollowLogsFabric;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.serializer.Toml4jConfigSerializer;

@Config(name = "hollow_logs")
public class HollowLogsConfig implements ConfigData {
    public boolean allowLogAxeCraftingRecipe = true;

    public static void init() {
        AutoConfig.register(HollowLogsConfig.class, (conf, clazz) -> new Toml4jConfigSerializer<>(conf, clazz) {
            public HollowLogsConfig deserialize() {
                try {
                    return super.deserialize();
                } catch (Exception e) {
                    return createDefault();
                }
            }
        });

        HollowLogsFabric.CONFIG = AutoConfig.getConfigHolder(HollowLogsConfig.class).getConfig();
    }
}
