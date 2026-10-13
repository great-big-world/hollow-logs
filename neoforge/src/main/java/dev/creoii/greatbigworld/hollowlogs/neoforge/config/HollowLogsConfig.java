package dev.creoii.greatbigworld.hollowlogs.neoforge.config;

import net.neoforged.neoforge.common.ModConfigSpec;
import org.apache.commons.lang3.tuple.Pair;

public record HollowLogsConfig(ModConfigSpec.ConfigValue<Boolean> allowLogAxeCraftingRecipe) {
    public static final HollowLogsConfig CONFIG;
    public static final ModConfigSpec CONFIG_SPEC;

    private HollowLogsConfig(ModConfigSpec.Builder builder) {
        this(builder
                .comment("Whether Hollow Logs should be craftable in a Crafting Table with an Axe & the corresponding Log")
                .translation("hollow_logs.config.option.allowLogAxeCraftingRecipe")
                .define("allow_log_axe_crafting_recipe", true));
    }

    static {
        Pair<HollowLogsConfig, ModConfigSpec> pair = new ModConfigSpec.Builder().configure(HollowLogsConfig::new);
        CONFIG = pair.getLeft();
        CONFIG_SPEC = pair.getRight();
    }
}
