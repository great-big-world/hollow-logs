package dev.creoii.greatbigworld.hollowlogs.registry;

import dev.architectury.registry.CreativeTabRegistry;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import dev.creoii.greatbigworld.hollowlogs.HollowLogs;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;

import java.util.function.Function;
import java.util.function.Supplier;

import static dev.creoii.greatbigworld.hollowlogs.HollowLogs.ITEMS;

public final class HollowLogsItems {
    public static RegistrySupplier<Item> HOLLOW_OAK_LOG = registerBlockItem(ITEMS, "hollow_oak_log", () -> HollowLogsBlocks.HOLLOW_OAK_LOG.get());
    public static RegistrySupplier<Item> HOLLOW_SPRUCE_LOG = registerBlockItem(ITEMS, "hollow_spruce_log", () -> HollowLogsBlocks.HOLLOW_SPRUCE_LOG.get());
    public static RegistrySupplier<Item> HOLLOW_BIRCH_LOG = registerBlockItem(ITEMS, "hollow_birch_log", () -> HollowLogsBlocks.HOLLOW_BIRCH_LOG.get());
    public static RegistrySupplier<Item> HOLLOW_JUNGLE_LOG = registerBlockItem(ITEMS, "hollow_jungle_log", () -> HollowLogsBlocks.HOLLOW_JUNGLE_LOG.get());
    public static RegistrySupplier<Item> HOLLOW_DARK_OAK_LOG = registerBlockItem(ITEMS, "hollow_dark_oak_log", () -> HollowLogsBlocks.HOLLOW_DARK_OAK_LOG.get());
    public static RegistrySupplier<Item> HOLLOW_ACACIA_LOG = registerBlockItem(ITEMS, "hollow_acacia_log", () -> HollowLogsBlocks.HOLLOW_ACACIA_LOG.get());
    public static RegistrySupplier<Item> HOLLOW_MANGROVE_LOG = registerBlockItem(ITEMS, "hollow_mangrove_log", () -> HollowLogsBlocks.HOLLOW_MANGROVE_LOG.get());
    public static RegistrySupplier<Item> HOLLOW_CHERRY_LOG = registerBlockItem(ITEMS, "hollow_cherry_log", () -> HollowLogsBlocks.HOLLOW_CHERRY_LOG.get());
    public static RegistrySupplier<Item> HOLLOW_PALE_OAK_LOG = registerBlockItem(ITEMS, "hollow_pale_oak_log", () -> HollowLogsBlocks.HOLLOW_PALE_OAK_LOG.get());
    public static RegistrySupplier<Item> HOLLOW_POPLAR_LOG = registerBlockItem(ITEMS, "hollow_poplar_log", () -> HollowLogsBlocks.HOLLOW_POPLAR_LOG.get());
    public static RegistrySupplier<Item> HOLLOW_WARPED_STEM = registerBlockItem(ITEMS, "hollow_warped_stem", () -> HollowLogsBlocks.HOLLOW_WARPED_STEM.get());
    public static RegistrySupplier<Item> HOLLOW_CRIMSON_STEM = registerBlockItem(ITEMS, "hollow_crimson_stem", () -> HollowLogsBlocks.HOLLOW_CRIMSON_STEM.get());

    public static RegistrySupplier<Item> STRIPPED_HOLLOW_OAK_LOG = registerBlockItem(ITEMS, "stripped_hollow_oak_log", () -> HollowLogsBlocks.STRIPPED_HOLLOW_OAK_LOG.get());
    public static RegistrySupplier<Item> STRIPPED_HOLLOW_SPRUCE_LOG = registerBlockItem(ITEMS, "stripped_hollow_spruce_log", () -> HollowLogsBlocks.STRIPPED_HOLLOW_SPRUCE_LOG.get());
    public static RegistrySupplier<Item> STRIPPED_HOLLOW_BIRCH_LOG = registerBlockItem(ITEMS, "stripped_hollow_birch_log", () -> HollowLogsBlocks.STRIPPED_HOLLOW_BIRCH_LOG.get());
    public static RegistrySupplier<Item> STRIPPED_HOLLOW_JUNGLE_LOG = registerBlockItem(ITEMS, "stripped_hollow_jungle_log", () -> HollowLogsBlocks.STRIPPED_HOLLOW_JUNGLE_LOG.get());
    public static RegistrySupplier<Item> STRIPPED_HOLLOW_DARK_OAK_LOG = registerBlockItem(ITEMS, "stripped_hollow_dark_oak_log", () -> HollowLogsBlocks.STRIPPED_HOLLOW_DARK_OAK_LOG.get());
    public static RegistrySupplier<Item> STRIPPED_HOLLOW_ACACIA_LOG = registerBlockItem(ITEMS, "stripped_hollow_acacia_log", () -> HollowLogsBlocks.STRIPPED_HOLLOW_ACACIA_LOG.get());
    public static RegistrySupplier<Item> STRIPPED_HOLLOW_MANGROVE_LOG = registerBlockItem(ITEMS, "stripped_hollow_mangrove_log", () -> HollowLogsBlocks.STRIPPED_HOLLOW_MANGROVE_LOG.get());
    public static RegistrySupplier<Item> STRIPPED_HOLLOW_CHERRY_LOG = registerBlockItem(ITEMS, "stripped_hollow_cherry_log", () -> HollowLogsBlocks.STRIPPED_HOLLOW_CHERRY_LOG.get());
    public static RegistrySupplier<Item> STRIPPED_HOLLOW_PALE_OAK_LOG = registerBlockItem(ITEMS, "stripped_hollow_pale_oak_log", () -> HollowLogsBlocks.STRIPPED_HOLLOW_PALE_OAK_LOG.get());
    public static RegistrySupplier<Item> STRIPPED_HOLLOW_POPLAR_LOG = registerBlockItem(ITEMS, "stripped_hollow_poplar_log", () -> HollowLogsBlocks.STRIPPED_HOLLOW_POPLAR_LOG.get());
    public static RegistrySupplier<Item> STRIPPED_HOLLOW_WARPED_STEM = registerBlockItem(ITEMS, "stripped_hollow_warped_stem", () -> HollowLogsBlocks.STRIPPED_HOLLOW_WARPED_STEM.get());
    public static RegistrySupplier<Item> STRIPPED_HOLLOW_CRIMSON_STEM = registerBlockItem(ITEMS, "stripped_hollow_crimson_stem", () -> HollowLogsBlocks.STRIPPED_HOLLOW_CRIMSON_STEM.get());

    public static void register() {
        ITEMS.register();

        CreativeTabRegistry.modify(CreativeTabRegistry.defer(CreativeModeTabs.BUILDING_BLOCKS), (_, output, _) -> {
            output.acceptAfter(Items.STRIPPED_OAK_WOOD, STRIPPED_HOLLOW_OAK_LOG.get());
            output.acceptAfter(Items.STRIPPED_SPRUCE_WOOD, STRIPPED_HOLLOW_SPRUCE_LOG.get());
            output.acceptAfter(Items.STRIPPED_BIRCH_WOOD, STRIPPED_HOLLOW_BIRCH_LOG.get());
            output.acceptAfter(Items.STRIPPED_JUNGLE_WOOD, STRIPPED_HOLLOW_JUNGLE_LOG.get());
            output.acceptAfter(Items.STRIPPED_DARK_OAK_WOOD, STRIPPED_HOLLOW_DARK_OAK_LOG.get());
            output.acceptAfter(Items.STRIPPED_ACACIA_WOOD, STRIPPED_HOLLOW_ACACIA_LOG.get());
            output.acceptAfter(Items.STRIPPED_MANGROVE_WOOD, STRIPPED_HOLLOW_MANGROVE_LOG.get());
            output.acceptAfter(Items.STRIPPED_CHERRY_WOOD, STRIPPED_HOLLOW_CHERRY_LOG.get());
            output.acceptAfter(Items.STRIPPED_PALE_OAK_WOOD, STRIPPED_HOLLOW_PALE_OAK_LOG.get());
            output.acceptAfter(Items.STRIPPED_POPLAR_WOOD, STRIPPED_HOLLOW_POPLAR_LOG.get());
            output.acceptAfter(Items.STRIPPED_WARPED_HYPHAE, STRIPPED_HOLLOW_WARPED_STEM.get());
            output.acceptAfter(Items.STRIPPED_CRIMSON_HYPHAE, STRIPPED_HOLLOW_CRIMSON_STEM.get());
        });
        CreativeTabRegistry.modify(CreativeTabRegistry.defer(CreativeModeTabs.NATURAL_BLOCKS), (_, output, _) -> {
            output.acceptAfter(Items.OAK_LOG, HOLLOW_OAK_LOG.get());
            output.acceptAfter(Items.SPRUCE_LOG, HOLLOW_SPRUCE_LOG.get());
            output.acceptAfter(Items.BIRCH_LOG, HOLLOW_BIRCH_LOG.get());
            output.acceptAfter(Items.JUNGLE_LOG, HOLLOW_JUNGLE_LOG.get());
            output.acceptAfter(Items.DARK_OAK_LOG, HOLLOW_DARK_OAK_LOG.get());
            output.acceptAfter(Items.ACACIA_LOG, HOLLOW_ACACIA_LOG.get());
            output.acceptAfter(Items.MANGROVE_LOG, HOLLOW_MANGROVE_LOG.get());
            output.acceptAfter(Items.CHERRY_LOG, HOLLOW_CHERRY_LOG.get());
            output.acceptAfter(Items.PALE_OAK_LOG, HOLLOW_PALE_OAK_LOG.get());
            output.acceptAfter(Items.POPLAR_LOG, HOLLOW_POPLAR_LOG.get());
            output.acceptAfter(Items.WARPED_STEM, HOLLOW_WARPED_STEM.get());
            output.acceptAfter(Items.CRIMSON_STEM, HOLLOW_CRIMSON_STEM.get());
        });
    }

    public static RegistrySupplier<Item> registerItem(DeferredRegister<Item> register, Identifier id, Function<Item.Properties, Item> factory) {
        return register.register(id, () -> factory.apply(new Item.Properties().setId(ResourceKey.create(Registries.ITEM, id))));
    }

    public static RegistrySupplier<Item> registerBlockItem(DeferredRegister<Item> register, String name, Supplier<Block> block) {
        Identifier id = Identifier.fromNamespaceAndPath(HollowLogs.GBW, name);
        return registerItem(register, id, settings1 -> new BlockItem(block.get(), settings1.setId(ResourceKey.create(Registries.ITEM, id)).useBlockDescriptionPrefix()));
    }
}
