package dev.creoii.greatbigworld.hollowlogs.registry;

import dev.creoii.greatbigworld.hollowlogs.HollowLogs;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

public final class HollowLogsFeatures {
    public static final ResourceKey<PlacedFeature> FALLEN_OAK_TREE = ResourceKey.create(Registries.PLACED_FEATURE, Identifier.fromNamespaceAndPath(HollowLogs.GBW, "fallen_oak_tree"));
    public static final ResourceKey<PlacedFeature> FALLEN_SPRUCE_TREE = ResourceKey.create(Registries.PLACED_FEATURE, Identifier.fromNamespaceAndPath(HollowLogs.GBW, "fallen_spruce_tree"));
    public static final ResourceKey<PlacedFeature> FALLEN_BIRCH_TREE = ResourceKey.create(Registries.PLACED_FEATURE, Identifier.fromNamespaceAndPath(HollowLogs.GBW, "fallen_birch_tree"));
    public static final ResourceKey<PlacedFeature> FALLEN_JUNGLE_TREE = ResourceKey.create(Registries.PLACED_FEATURE, Identifier.fromNamespaceAndPath(HollowLogs.GBW, "fallen_jungle_tree"));
    public static final ResourceKey<PlacedFeature> FALLEN_POPLAR_TREE = ResourceKey.create(Registries.PLACED_FEATURE, Identifier.fromNamespaceAndPath(HollowLogs.GBW, "fallen_poplar_tree"));

    public static final ResourceKey<PlacedFeature> FALLEN_CRIMSON_FUNGUS = ResourceKey.create(Registries.PLACED_FEATURE, Identifier.fromNamespaceAndPath(HollowLogs.GBW, "fallen_crimson_fungus"));
    public static final ResourceKey<PlacedFeature> FALLEN_WARPED_FUNGUS = ResourceKey.create(Registries.PLACED_FEATURE, Identifier.fromNamespaceAndPath(HollowLogs.GBW, "fallen_warped_fungus"));
}
