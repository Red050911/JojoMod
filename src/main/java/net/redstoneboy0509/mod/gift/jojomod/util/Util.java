package net.redstoneboy0509.mod.gift.jojomod.util;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.PlayerContainerEvent;
import net.redstoneboy0509.mod.gift.jojomod.container.JojoContainer;
import net.redstoneboy0509.mod.gift.jojomod.entities.JojoEntity;
import net.redstoneboy0509.mod.gift.jojomod.packet.SOpenJojoGUIPacket;

public class Util {

    public static void playerEntityOpenJojoInventory(PlayerEntity player, JojoEntity jojo) {
        if(!(player instanceof ServerPlayerEntity)) {
            return;
        }
        ServerPlayerEntity sPlayer = (ServerPlayerEntity) player;
        if(sPlayer.openContainer != sPlayer.container) {
            sPlayer.closeScreen();
        }
        sPlayer.getNextWindowId();
        sPlayer.connection.sendPacket(new SOpenJojoGUIPacket(sPlayer.currentWindowId, jojo.getEntityId()));
        sPlayer.openContainer = new JojoContainer(sPlayer.currentWindowId, sPlayer.inventory, jojo);
        sPlayer.openContainer.addListener(sPlayer);
        MinecraftForge.EVENT_BUS.post(new PlayerContainerEvent.Open(sPlayer, sPlayer.openContainer));
    }

}
