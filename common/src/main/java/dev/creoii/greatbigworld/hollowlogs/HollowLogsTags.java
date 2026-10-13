package dev.creoii.greatbigworld.hollowlogs;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

public final class HollowLogsTags {
    public static final TagKey<Block> HOLLOW_LOGS = TagKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(HollowLogs.GBW, "hollow_logs"));
    public static final TagKey<Block> DOES_NOT_SUPPORT_FALLEN_TREES = TagKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(HollowLogs.GBW, "does_not_support_fallen_trees"));
}
