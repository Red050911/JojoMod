package net.redstoneboy0509.mod.gift.jojomod.recipetypes.jojonic;

import com.google.gson.JsonObject;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.registries.ForgeRegistryEntry;

import javax.annotation.Nullable;

public class JojonicSerializer extends ForgeRegistryEntry<IRecipeSerializer<?>> implements IRecipeSerializer<JojonicSigilRecipe> {

    @Override
    public JojonicSigilRecipe read(ResourceLocation recipeId, JsonObject json) {
        ItemStack output = CraftingHelper.getItemStack(JSONUtils.getJsonObject(json, "output"), true);
        ItemStack byproduct = CraftingHelper.getItemStack(JSONUtils.getJsonObject(json, "byproduct"), true);
        Ingredient input = Ingredient.deserialize(JSONUtils.getJsonObject(json, "input"));

        return new JojonicSigilRecipe(recipeId, input, output, byproduct);
    }

    @Nullable
    @Override
    public JojonicSigilRecipe read(ResourceLocation recipeId, PacketBuffer buffer) {
        ItemStack output = buffer.readItemStack();
        ItemStack byproduct = buffer.readItemStack();
        Ingredient input = Ingredient.read(buffer);

        return new JojonicSigilRecipe(recipeId, input, output, byproduct);
    }

    @Override
    public void write(PacketBuffer buffer, JojonicSigilRecipe recipe) {
        buffer.writeItemStack(recipe.getRecipeOutput(), false);
        buffer.writeItemStack(recipe.getByproduct(), false);
        recipe.getInput().write(buffer);
    }

}
