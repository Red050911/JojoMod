package net.redstoneboy0509.mod.gift.jojomod.registry;

import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.redstoneboy0509.mod.gift.jojomod.recipetypes.jojonic.JojonicCraftingRecipe;
import net.redstoneboy0509.mod.gift.jojomod.recipetypes.jojonic.JojonicCraftingSerializer;
import net.redstoneboy0509.mod.gift.jojomod.recipetypes.jojonic.JojonicSerializer;
import net.redstoneboy0509.mod.gift.jojomod.recipetypes.jojonic.JojonicSigilRecipe;

@Mod.EventBusSubscriber(modid = "rjm", bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModRecipes {

    public static IRecipeSerializer<JojonicSigilRecipe> JOJONIC;
    public static IRecipeType<JojonicSigilRecipe> JOJONIC_TYPE;
    public static IRecipeSerializer<JojonicCraftingRecipe> JOJONIC_CRAFTING;
    public static IRecipeType<JojonicCraftingRecipe> JOJONIC_CRAFTING_TYPE;

    @SubscribeEvent
    public static void registerRecipes(RegistryEvent.Register<IRecipeSerializer<?>> event) {
        JOJONIC = IRecipeSerializer.register("rjm:jojonic", new JojonicSerializer());
        JOJONIC_TYPE = IRecipeType.register("rjm:jojonic");
        JOJONIC_CRAFTING = IRecipeSerializer.register("rjm:jojonic_crafting", new JojonicCraftingSerializer());
        JOJONIC_CRAFTING_TYPE = IRecipeType.register("rjm:jojonic_crafting");
    }

}
