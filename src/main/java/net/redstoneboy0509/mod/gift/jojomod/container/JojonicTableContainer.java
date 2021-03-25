package net.redstoneboy0509.mod.gift.jojomod.container;

import net.minecraft.client.gui.screen.inventory.HorseInventoryScreen;
import net.minecraft.entity.passive.horse.HorseEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IWorldPosCallable;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.items.SlotItemHandler;
import net.minecraftforge.registries.ForgeRegistry;
import net.redstoneboy0509.mod.gift.jojomod.registry.ModBlocks;
import net.redstoneboy0509.mod.gift.jojomod.registry.ModContainerTypes;
import net.redstoneboy0509.mod.gift.jojomod.tileentity.JojonicSigilTableTileEntity;
import net.redstoneboy0509.mod.gift.jojomod.util.FunctionalIntReferenceHolder;

import javax.annotation.Nonnull;
import java.util.Objects;

public class JojonicTableContainer extends Container {

    private JojonicSigilTableTileEntity tileEntity;
    private IWorldPosCallable canInteractWithCallable;
    private FunctionalIntReferenceHolder workTime;

    public JojonicTableContainer(final int windowId, final PlayerInventory playerInv, final JojonicSigilTableTileEntity tile) {
        super(ModContainerTypes.JOJONIC.get(), windowId);

        this.tileEntity = tile;
        this.canInteractWithCallable = IWorldPosCallable.of(tile.getWorld(), tile.getPos());

        final int slotSizePlus2 = 18;
        final int startX = 8;

        final int hotbarY = 142;
        for(int column = 0; column < 9; column++) {
            this.addSlot(new Slot(playerInv, column, startX + (column * slotSizePlus2), hotbarY));
        }

        final int startY = 84;

        for(int row = 0; row < 3; row++) {
            for(int column = 0; column < 9; column++) {
                this.addSlot(new Slot(playerInv, 9 + (row * 9) + column, startX + (column * slotSizePlus2), startY + (row * slotSizePlus2)));
            }
        }

        this.addSlot(new SlotItemHandler(tileEntity.getInventory(), 0, 41, 35));
        this.addSlot(new SlotItemHandler(tileEntity.getInventory(), 1, 102, 35));
        this.addSlot(new SlotItemHandler(tileEntity.getInventory(), 2, 128, 36));

        this.trackInt(workTime = new FunctionalIntReferenceHolder(() -> this.tileEntity.workTime, value -> this.tileEntity.workTime = value));
    }

    public JojonicTableContainer(final int windowId, final PlayerInventory playerInv, final PacketBuffer data) {
        this(windowId, playerInv, getTileEntity(playerInv, data));
    }

    @Override
    public boolean canInteractWith(PlayerEntity playerIn) {
        return isWithinUsableDistance(canInteractWithCallable, playerIn, ModBlocks.JOJONIC_TABLE.get());
    }

    private static JojonicSigilTableTileEntity getTileEntity(final PlayerInventory playerInv, final PacketBuffer data) {
        Objects.requireNonNull(playerInv, "playerInv cannot be null");
        Objects.requireNonNull(data, "data cannot be null");
        final TileEntity tileAtPos = playerInv.player.world.getTileEntity(data.readBlockPos());
        if (tileAtPos instanceof JojonicSigilTableTileEntity) {
            return (JojonicSigilTableTileEntity) tileAtPos;
        }
        throw new IllegalStateException("TileEntity is not correct " + tileAtPos);
    }

    @Nonnull
    @Override
    public ItemStack transferStackInSlot(final PlayerEntity player, final int index) {
        ItemStack returnStack = ItemStack.EMPTY;
        final Slot slot = this.inventorySlots.get(index);
        if (slot != null && slot.getHasStack()) {
            final ItemStack slotStack = slot.getStack();
            returnStack = slotStack.copy();

            final int containerSlots = this.inventorySlots.size() - player.inventory.mainInventory.size();
            if (index < containerSlots) {
                if (!mergeItemStack(slotStack, containerSlots, this.inventorySlots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            } else if (!mergeItemStack(slotStack, 0, containerSlots, false)) {
                return ItemStack.EMPTY;
            }
            if (slotStack.getCount() == 0) {
                slot.putStack(ItemStack.EMPTY);
            } else {
                slot.onSlotChanged();
            }
            if (slotStack.getCount() == returnStack.getCount()) {
                return ItemStack.EMPTY;
            }
            slot.onTake(player, slotStack);
        }
        return returnStack;
    }

    @OnlyIn(Dist.CLIENT)
    public int getSmeltProgressionScaled() {
        return this.workTime.get() != 0 && JojonicSigilTableTileEntity.MAX_WORK_TIME != 0
                ? this.workTime.get() * 24 / JojonicSigilTableTileEntity.MAX_WORK_TIME
                : 0;
    }

}
