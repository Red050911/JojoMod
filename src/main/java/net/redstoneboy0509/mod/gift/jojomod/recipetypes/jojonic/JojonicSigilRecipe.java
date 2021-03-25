package net.redstoneboy0509.mod.gift.jojomod.recipetypes.jojonic;

import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.items.wrapper.RecipeWrapper;

public class JojonicSigilRecipe implements IJojonicSigilRecipe {

    private final ResourceLocation id;
    private Ingredient input;
    private final ItemStack output;
    private final ItemStack byproduct;

    public JojonicSigilRecipe(ResourceLocation id, Ingredient input, ItemStack output, ItemStack byproduct) {
        this.id = id;
        this.input = input;
        this.output = output;
        this.byproduct = byproduct;
    }

    @Override
    public Ingredient getInput() {
        return this.input;
    }

    @Override
    public ItemStack getByproduct() {
        return this.byproduct;
    }

    @Override
    public boolean matches(RecipeWrapper inv, World worldIn) {
        return this.input.test(inv.getStackInSlot(0));
    }

    @Override
    public ItemStack getCraftingResult(RecipeWrapper inv) {
        return this.output;
    }

    @Override
    public ItemStack getRecipeOutput() {
        return this.output;
    }

    @Override
    public ResourceLocation getId() {
        return this.id;
    }

    @Override
    public IRecipeSerializer<?> getSerializer() {
        return null;
    }

    @Override
    public NonNullList<Ingredient> getIngredients() {
        return NonNullList.from(null, this.input);
    }
}
