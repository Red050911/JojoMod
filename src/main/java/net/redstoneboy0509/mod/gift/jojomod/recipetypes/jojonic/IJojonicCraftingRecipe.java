package net.redstoneboy0509.mod.gift.jojomod.recipetypes.jojonic;

import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.redstoneboy0509.mod.gift.jojomod.util.JojonicCraftingInventory;

public interface IJojonicCraftingRecipe extends IRecipe<JojonicCraftingInventory> {

    ResourceLocation RECIPE_TYPE_ID = new ResourceLocation("rjm", "jojonic_crafting");

    int getWidth();
    int getHeight();

    @Override
    default IRecipeType<?> getType() {
        return Registry.RECIPE_TYPE.getValue(RECIPE_TYPE_ID).get();
    }

}
