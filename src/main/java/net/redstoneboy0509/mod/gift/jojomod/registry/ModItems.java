package net.redstoneboy0509.mod.gift.jojomod.registry;

import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Rarity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.redstoneboy0509.mod.gift.jojomod.items.JojoFoodItem;
import net.redstoneboy0509.mod.gift.jojomod.items.ModMusicDiscItem;
import net.redstoneboy0509.mod.gift.jojomod.items.ModSpawnEggItem;

public class ModItems {

    public static final DeferredRegister<Item> REGISTER = DeferredRegister.create(ForgeRegistries.ITEMS, "rjm");

    public static final RegistryObject<Item> JOJO_FOOD = REGISTER.register("jojo_food", JojoFoodItem::new);

    public static final RegistryObject<Item> JOJOTUNE_DISC = REGISTER.register("disc_jojotune", () -> new ModMusicDiscItem(15, ModSounds.LAZY_JOJOTUNE, new Item.Properties().maxStackSize(1).rarity(Rarity.RARE).group(ItemGroup.MISC)));

    public static final RegistryObject<Item> JOJONIC_SIGIL = REGISTER.register("jojonic_sigil", () -> new Item(new Item.Properties().group(ModItemGroups.JOJOMOD_TAB)));

    public static final RegistryObject<Item> ACTIVE_JOJONIC_SIGIL = REGISTER.register("active_jojonic_sigil", () -> new Item(new Item.Properties().group(ModItemGroups.JOJOMOD_TAB)));

    public static final RegistryObject<Item> JOJO_SPAWN_EGG = REGISTER.register("jojo_spawn_egg", () -> new ModSpawnEggItem(ModEntityTypes.JOJO, 0x802b00, 0x993300, new Item.Properties().group(ModItemGroups.JOJOMOD_TAB)));
}
