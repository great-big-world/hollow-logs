package dev.creoii.greatbigworld.hollowlogs.registry;

import com.mojang.serialization.MapCodec;
import dev.architectury.registry.registries.RegistrySupplier;
import dev.creoii.greatbigworld.hollowlogs.HollowLogs;
import dev.creoii.greatbigworld.hollowlogs.level.feature.FallenTreeFeature;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.levelgen.feature.Feature;

import static dev.creoii.greatbigworld.hollowlogs.HollowLogs.FEATURE_TYPES;

public final class HollowLogsFeatureTypes {
    public static RegistrySupplier<MapCodec<? extends Feature>> FALLEN_TREE = FEATURE_TYPES.register(Identifier.fromNamespaceAndPath(HollowLogs.GBW, "fallen_tree"), () -> FallenTreeFeature.CODEC);

    public static void register() {
        FEATURE_TYPES.register();
    }
}
