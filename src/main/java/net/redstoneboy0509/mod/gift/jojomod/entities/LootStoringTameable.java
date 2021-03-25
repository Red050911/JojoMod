package net.redstoneboy0509.mod.gift.jojomod.entities;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.LockableLootTileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

@SuppressWarnings("deprecation")
public abstract class LootStoringTameable extends TameableEntity implements IInventory, INamedContainerProvider {

    protected LootStoringTameable(EntityType<? extends TameableEntity> type, World worldIn) {
        super(type, worldIn);
    }

    @Override
    public abstract int getSizeInventory();

    @Override
    public boolean isEmpty() {
        return this.getItems().stream().allMatch(ItemStack::isEmpty);
    }

    @Override
    public ItemStack getStackInSlot(int index) {
        return this.getItems().get(index);
    }

    @Override
    public ItemStack decrStackSize(int index, int count) {
        ItemStack itemstack = ItemStackHelper.getAndSplit(this.getItems(), index, count);
        if (!itemstack.isEmpty()) {
            this.markDirty();
        }

        return itemstack;
    }

    @Override
    public ItemStack removeStackFromSlot(int index) {
        return ItemStackHelper.getAndRemove(this.getItems(), index);
    }

    @Override
    public void setInventorySlotContents(int index, ItemStack stack) {
        this.getItems().set(index, stack);
        if (stack.getCount() > this.getInventoryStackLimit()) {
            stack.setCount(this.getInventoryStackLimit());
        }

        this.markDirty();
    }

    @Override
    public void markDirty() {
        if (this.world.isBlockLoaded(this.getPosition())) {
            this.world.getChunkAt(this.getPosition()).markDirty();
        }
    }

    @Override
    public boolean isUsableByPlayer(PlayerEntity player) {
        return !(player.getDistanceSq((double)this.getPosition().getX() + 0.5D, (double)this.getPosition().getY() + 0.5D, (double)this.getPosition().getZ() + 0.5D) > 64.0D);
    }
    
    public abstract NonNullList<ItemStack> getItems();

    @Nullable
    @Override
    public abstract Container createMenu(int windowId, PlayerInventory playerInv, PlayerEntity playerIn);

    @Override
    public void clear() {
        this.getItems().clear();
    }

}
