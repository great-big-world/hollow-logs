package dev.creoii.greatbigworld.hollowlogs.fabric.config;

import dev.creoii.greatbigworld.hollowlogs.platform.fabric.HollowLogsPlatformUtilImpl;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

public class ModMenuConfig {
    public static Screen createConfigScreen(Screen parent) {
        return createConfigScreenBuilder(parent).build();
    }

    public static ConfigBuilder createConfigScreenBuilder(Screen parent) {
        ConfigBuilder builder = ConfigBuilder.create().setParentScreen(parent).setTitle(Component.translatable("title.hollow_logs.config"));
        builder.setSavingRunnable(() -> AutoConfig.getConfigHolder(HollowLogsConfig.class).save());

        ConfigEntryBuilder entryBuilder = builder.entryBuilder();
        ConfigCategory generalCategory = builder.getOrCreateCategory(Component.translatable("hollow_logs.config.category.general"));

        generalCategory.addEntry(entryBuilder.startBooleanToggle(Component.translatable("hollow_logs.config.option.allowLogAxeCraftingRecipe"), HollowLogsPlatformUtilImpl.allowLogAxeCraftingRecipe())
                .setDefaultValue(true)
                .setTooltip(Component.translatable("hollow_logs.config.option.allowLogAxeCraftingRecipe.tooltip"))
                .setSaveConsumer(HollowLogsPlatformUtilImpl::setAllowLogAxeCraftingRecipe)
                .build());

        return builder;
    }
}
