package net.redstoneboy0509.mod.gift.jojomod.registry;

import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.redstoneboy0509.mod.gift.jojomod.blocks.JojonicCraftingTableBlock;
import net.redstoneboy0509.mod.gift.jojomod.blocks.JojonicTableBlock;

public class ModBlocks {

    public static DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, "rjm");
    public static DeferredRegister<Item> BLOCK_ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, "rjm");

    public static RegistryObject<Block> JOJONIC_TABLE = BLOCKS.register("jojonic_table", JojonicTableBlock::new);
    public static RegistryObject<Item> JOJONIC_TABLE_ITEM = BLOCK_ITEMS.register("jojonic_table", () -> new BlockItem(JOJONIC_TABLE.get(), new Item.Properties().group(ModItemGroups.JOJOMOD_TAB)));

    public static RegistryObject<Block> JOJONIC_CRAFTING_TABLE = BLOCKS.register("jojonic_crafting_table", JojonicCraftingTableBlock::new);
    public static RegistryObject<Item> JOJONIC_CRAFTING_TABLE_ITEM = BLOCK_ITEMS.register("jojonic_crafting_table", () -> new BlockItem(JOJONIC_CRAFTING_TABLE.get(), new Item.Properties().group(ModItemGroups.JOJOMOD_TAB)));


}
