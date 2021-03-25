package net.redstoneboy0509.mod.gift.jojomod.container;

import net.minecraft.client.gui.screen.inventory.CraftingScreen;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.*;
import net.redstoneboy0509.mod.gift.jojomod.entities.JojoEntity;

import javax.annotation.Nullable;

public class JojoContainer extends Container {

    public JojoContainer(int id, PlayerInventory playerInv, JojoEntity entity) {
        super(null, id);

        int startX = 8;
        int slotSizePlus2 = 18;

        int mainInvY = 18;
        for(int column = 0; column < 9; column++) {
            this.addSlot(new Slot(entity, column, startX + (column * slotSizePlus2), mainInvY));
        }

        int hotbarY = 108;
        for(int column = 0; column < 9; column++) {
            this.addSlot(new Slot(playerInv, column, startX + (column * slotSizePlus2), hotbarY));
        }

        int playerMainInvY = 50;
        for(int row = 0; row < 3; row++) {
            for(int column = 0; column < 9; column++) {
                this.addSlot(new Slot(playerInv, 9 + (row * 9) + column, startX + (column * slotSizePlus2), playerMainInvY + (row * slotSizePlus2)));
            }
        }
    }

    @Override
    public boolean canInteractWith(PlayerEntity playerIn) {
        return true;
    }

}
