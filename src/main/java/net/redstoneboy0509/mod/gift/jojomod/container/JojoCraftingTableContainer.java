package net.redstoneboy0509.mod.gift.jojomod.container;

import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.CraftResultInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.inventory.container.RecipeBookContainer;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.RecipeItemHelper;
import net.minecraft.network.play.server.SSetSlotPacket;
import net.minecraft.util.IWorldPosCallable;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.redstoneboy0509.mod.gift.jojomod.recipetypes.jojonic.JojonicCraftingRecipe;
import net.redstoneboy0509.mod.gift.jojomod.registry.ModBlocks;
import net.redstoneboy0509.mod.gift.jojomod.registry.ModRecipes;
import net.redstoneboy0509.mod.gift.jojomod.util.JojonicCraftingInventory;
import net.redstoneboy0509.mod.gift.jojomod.util.JojonicCraftingResultSlot;

import java.util.Optional;

public class JojoCraftingTableContainer extends RecipeBookContainer<JojonicCraftingInventory> {

    private final JojonicCraftingInventory field_75162_e = new JojonicCraftingInventory(this, 3, 3);
    private final CraftResultInventory field_75160_f = new CraftResultInventory();
    private final IWorldPosCallable field_217070_e;
    private final PlayerEntity player;

    public JojoCraftingTableContainer(int p_i50089_1_, PlayerInventory p_i50089_2_) {
        this(p_i50089_1_, p_i50089_2_, IWorldPosCallable.DUMMY);
    }

    public JojoCraftingTableContainer(int p_i50090_1_, PlayerInventory p_i50090_2_, IWorldPosCallable p_i50090_3_) {
        super(ContainerType.CRAFTING, p_i50090_1_);
        this.field_217070_e = p_i50090_3_;
        this.player = p_i50090_2_.player;
        this.addSlot(new JojonicCraftingResultSlot(p_i50090_2_.player, this.field_75162_e, this.field_75160_f, 0, 124, 35));

        for(int i = 0; i < 3; ++i) {
            for(int j = 0; j < 3; ++j) {
                this.addSlot(new Slot(this.field_75162_e, j + i * 3, 30 + j * 18, 17 + i * 18));
            }
        }

        for(int k = 0; k < 3; ++k) {
            for(int i1 = 0; i1 < 9; ++i1) {
                this.addSlot(new Slot(p_i50090_2_, i1 + k * 9 + 9, 8 + i1 * 18, 84 + k * 18));
            }
        }

        for(int l = 0; l < 9; ++l) {
            this.addSlot(new Slot(p_i50090_2_, l, 8 + l * 18, 142));
        }

    }

    protected static void func_217066_a(int p_217066_0_, World p_217066_1_, PlayerEntity p_217066_2_, JojonicCraftingInventory p_217066_3_, CraftResultInventory p_217066_4_) {
        if (!p_217066_1_.isRemote) {
            ServerPlayerEntity serverplayerentity = (ServerPlayerEntity)p_217066_2_;
            ItemStack itemstack = ItemStack.EMPTY;
            Optional<JojonicCraftingRecipe> optional = p_217066_1_.getServer().getRecipeManager().getRecipe(ModRecipes.JOJONIC_CRAFTING_TYPE, p_217066_3_, p_217066_1_);
            if (optional.isPresent()) {
                JojonicCraftingRecipe icraftingrecipe = optional.get();
                if (p_217066_4_.canUseRecipe(p_217066_1_, serverplayerentity, icraftingrecipe)) {
                    itemstack = icraftingrecipe.getCraftingResult(p_217066_3_);
                }
            }

            p_217066_4_.setInventorySlotContents(0, itemstack);
            serverplayerentity.connection.sendPacket(new SSetSlotPacket(p_217066_0_, 0, itemstack));
        }
    }

    public void onCraftMatrixChanged(IInventory inventoryIn) {
        this.field_217070_e.consume((p_217069_1_, p_217069_2_) -> {
            func_217066_a(this.windowId, p_217069_1_, this.player, this.field_75162_e, this.field_75160_f);
        });
    }

    public void func_201771_a(RecipeItemHelper p_201771_1_) {
        this.field_75162_e.fillStackedContents(p_201771_1_);
    }

    public void clear() {
        this.field_75162_e.clear();
        this.field_75160_f.clear();
    }

    public boolean matches(IRecipe<? super JojonicCraftingInventory> recipeIn) {
        return recipeIn.matches(this.field_75162_e, this.player.world);
    }

    public void onContainerClosed(PlayerEntity playerIn) {
        super.onContainerClosed(playerIn);
        this.field_217070_e.consume((p_217068_2_, p_217068_3_) -> {
            this.clearContainer(playerIn, p_217068_2_, this.field_75162_e);
        });
    }

    public boolean canInteractWith(PlayerEntity playerIn) {
        return true;
    }

    public ItemStack transferStackInSlot(PlayerEntity playerIn, int index) {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.inventorySlots.get(index);
        if (slot != null && slot.getHasStack()) {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();
            if (index == 0) {
                this.field_217070_e.consume((p_217067_2_, p_217067_3_) -> {
                    itemstack1.getItem().onCreated(itemstack1, p_217067_2_, playerIn);
                });
                if (!this.mergeItemStack(itemstack1, 10, 46, true)) {
                    return ItemStack.EMPTY;
                }

                slot.onSlotChange(itemstack1, itemstack);
            } else if (index >= 10 && index < 46) {
                if (!this.mergeItemStack(itemstack1, 1, 10, false)) {
                    if (index < 37) {
                        if (!this.mergeItemStack(itemstack1, 37, 46, false)) {
                            return ItemStack.EMPTY;
                        }
                    } else if (!this.mergeItemStack(itemstack1, 10, 37, false)) {
                        return ItemStack.EMPTY;
                    }
                }
            } else if (!this.mergeItemStack(itemstack1, 10, 46, false)) {
                return ItemStack.EMPTY;
            }

            if (itemstack1.isEmpty()) {
                slot.putStack(ItemStack.EMPTY);
            } else {
                slot.onSlotChanged();
            }

            if (itemstack1.getCount() == itemstack.getCount()) {
                return ItemStack.EMPTY;
            }

            ItemStack itemstack2 = slot.onTake(playerIn, itemstack1);
            if (index == 0) {
                playerIn.dropItem(itemstack2, false);
            }
        }

        return itemstack;
    }

    public boolean canMergeSlot(ItemStack stack, Slot slotIn) {
        return slotIn.inventory != this.field_75160_f && super.canMergeSlot(stack, slotIn);
    }

    public int getOutputSlot() {
        return 0;
    }

    public int getWidth() {
        return this.field_75162_e.getWidth();
    }

    public int getHeight() {
        return this.field_75162_e.getHeight();
    }

    @OnlyIn(Dist.CLIENT)
    public int getSize() {
        return 10;
    }

}
