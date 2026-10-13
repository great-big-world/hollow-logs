package dev.creoii.greatbigworld.hollowlogs;

import com.mojang.serialization.MapCodec;
import dev.architectury.registry.level.biome.BiomeModifications;
import dev.architectury.registry.registries.DeferredRegister;
import dev.creoii.greatbigworld.hollowlogs.registry.HollowLogsBlocks;
import dev.creoii.greatbigworld.hollowlogs.registry.HollowLogsFeatureTypes;
import dev.creoii.greatbigworld.hollowlogs.registry.HollowLogsFeatures;
import dev.creoii.greatbigworld.hollowlogs.registry.HollowLogsItems;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.feature.Feature;

public final class HollowLogs {
    public static final String NAMESPACE = "hollow_logs";
    public static final String GBW = "great_big_world";

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(NAMESPACE, Registries.BLOCK);
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(NAMESPACE, Registries.ITEM);
    public static final DeferredRegister<MapCodec<? extends Feature>> FEATURE_TYPES = DeferredRegister.create(NAMESPACE, Registries.FEATURE_TYPE);

    public static void init() {
        HollowLogsBlocks.register();
        HollowLogsItems.register();
        HollowLogsFeatureTypes.register();

        BiomeModifications.addProperties((context, mutable) -> {
            if (context.getKey().isEmpty()) {
                return;
            }

            if (context.getKey().get() == Biomes.CRIMSON_FOREST.identifier()) {
                mutable.getGenerationProperties().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, HollowLogsFeatures.FALLEN_CRIMSON_FUNGUS);
            } else if (context.getKey().get() == Biomes.WARPED_FOREST.identifier()) {
                mutable.getGenerationProperties().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, HollowLogsFeatures.FALLEN_WARPED_FUNGUS);
            }
        });
    }
}
