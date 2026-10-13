package dev.creoii.greatbigworld.hollowlogs.registry;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import dev.creoii.greatbigworld.hollowlogs.HollowLogs;
import dev.creoii.greatbigworld.hollowlogs.block.HollowLogBlock;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;

import java.util.function.Function;

import static dev.creoii.greatbigworld.hollowlogs.HollowLogs.BLOCKS;

public final class HollowLogsBlocks {
    public static RegistrySupplier<Block> HOLLOW_OAK_LOG = registerBlock(BLOCKS, "hollow_oak_log", HollowLogBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LOG));
    public static RegistrySupplier<Block> HOLLOW_SPRUCE_LOG = registerBlock(BLOCKS, "hollow_spruce_log", HollowLogBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.SPRUCE_LOG));
    public static RegistrySupplier<Block> HOLLOW_BIRCH_LOG = registerBlock(BLOCKS, "hollow_birch_log", HollowLogBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.BIRCH_LOG));
    public static RegistrySupplier<Block> HOLLOW_JUNGLE_LOG = registerBlock(BLOCKS, "hollow_jungle_log", HollowLogBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.JUNGLE_LOG));
    public static RegistrySupplier<Block> HOLLOW_DARK_OAK_LOG = registerBlock(BLOCKS, "hollow_dark_oak_log", HollowLogBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.DARK_OAK_LOG));
    public static RegistrySupplier<Block> HOLLOW_ACACIA_LOG = registerBlock(BLOCKS, "hollow_acacia_log", HollowLogBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.ACACIA_LOG));
    public static RegistrySupplier<Block> HOLLOW_MANGROVE_LOG = registerBlock(BLOCKS, "hollow_mangrove_log", HollowLogBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.MANGROVE_LOG));
    public static RegistrySupplier<Block> HOLLOW_CHERRY_LOG = registerBlock(BLOCKS, "hollow_cherry_log", HollowLogBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.CHERRY_LOG));
    public static RegistrySupplier<Block> HOLLOW_PALE_OAK_LOG = registerBlock(BLOCKS, "hollow_pale_oak_log", HollowLogBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.PALE_OAK_LOG));
    public static RegistrySupplier<Block> HOLLOW_POPLAR_LOG = registerBlock(BLOCKS, "hollow_poplar_log", HollowLogBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.POPLAR_LOG));
    public static RegistrySupplier<Block> HOLLOW_WARPED_STEM = registerBlock(BLOCKS, "hollow_warped_stem", HollowLogBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.WARPED_STEM));
    public static RegistrySupplier<Block> HOLLOW_CRIMSON_STEM = registerBlock(BLOCKS, "hollow_crimson_stem", HollowLogBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.CRIMSON_STEM));

    public static RegistrySupplier<Block> STRIPPED_HOLLOW_OAK_LOG = registerBlock(BLOCKS, "stripped_hollow_oak_log", HollowLogBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_LOG));
    public static RegistrySupplier<Block> STRIPPED_HOLLOW_SPRUCE_LOG = registerBlock(BLOCKS, "stripped_hollow_spruce_log", HollowLogBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_SPRUCE_LOG));
    public static RegistrySupplier<Block> STRIPPED_HOLLOW_BIRCH_LOG = registerBlock(BLOCKS, "stripped_hollow_birch_log", HollowLogBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_BIRCH_LOG));
    public static RegistrySupplier<Block> STRIPPED_HOLLOW_JUNGLE_LOG = registerBlock(BLOCKS, "stripped_hollow_jungle_log", HollowLogBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_JUNGLE_LOG));
    public static RegistrySupplier<Block> STRIPPED_HOLLOW_DARK_OAK_LOG = registerBlock(BLOCKS, "stripped_hollow_dark_oak_log", HollowLogBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_DARK_OAK_LOG));
    public static RegistrySupplier<Block> STRIPPED_HOLLOW_ACACIA_LOG = registerBlock(BLOCKS, "stripped_hollow_acacia_log", HollowLogBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_ACACIA_LOG));
    public static RegistrySupplier<Block> STRIPPED_HOLLOW_MANGROVE_LOG = registerBlock(BLOCKS, "stripped_hollow_mangrove_log", HollowLogBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_MANGROVE_LOG));
    public static RegistrySupplier<Block> STRIPPED_HOLLOW_CHERRY_LOG = registerBlock(BLOCKS, "stripped_hollow_cherry_log", HollowLogBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_CHERRY_LOG));
    public static RegistrySupplier<Block> STRIPPED_HOLLOW_PALE_OAK_LOG = registerBlock(BLOCKS, "stripped_hollow_pale_oak_log", HollowLogBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_PALE_OAK_LOG));
    public static RegistrySupplier<Block> STRIPPED_HOLLOW_POPLAR_LOG = registerBlock(BLOCKS, "stripped_hollow_poplar_log", HollowLogBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_POPLAR_LOG));
    public static RegistrySupplier<Block> STRIPPED_HOLLOW_WARPED_STEM = registerBlock(BLOCKS, "stripped_hollow_warped_stem", HollowLogBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_WARPED_STEM));
    public static RegistrySupplier<Block> STRIPPED_HOLLOW_CRIMSON_STEM = registerBlock(BLOCKS, "stripped_hollow_crimson_stem", HollowLogBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_CRIMSON_STEM));

    public static void register() {
        BLOCKS.register();
    }

    public static RegistrySupplier<Block> registerBlock(DeferredRegister<Block> register, String name, Function<BlockBehaviour.Properties, Block> factory, BlockBehaviour.Properties settings) {
        Identifier id = Identifier.fromNamespaceAndPath(HollowLogs.GBW, name);
        return register.register(id, () -> factory.apply(settings.setId(ResourceKey.create(Registries.BLOCK, id))));
    }
}
