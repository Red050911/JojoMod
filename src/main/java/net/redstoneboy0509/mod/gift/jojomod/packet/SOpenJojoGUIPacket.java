package net.redstoneboy0509.mod.gift.jojomod.packet;

import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.client.network.play.ClientPlayNetHandler;
import net.minecraft.entity.Entity;
import net.minecraft.network.IPacket;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.PacketThreadUtil;
import net.minecraft.network.play.server.SOpenHorseWindowPacket;
import net.redstoneboy0509.mod.gift.jojomod.client.gui.JojoScreen;
import net.redstoneboy0509.mod.gift.jojomod.container.JojoContainer;
import net.redstoneboy0509.mod.gift.jojomod.entities.JojoEntity;

import java.io.IOException;

public class SOpenJojoGUIPacket implements IPacket<ClientPlayNetHandler> {

    private int windowId;
    private int entityId;

    public SOpenJojoGUIPacket(int windowId, int entityId) {
        this.windowId = windowId;
        this.entityId = entityId;
    }

    @Override
    public void readPacketData(PacketBuffer buf) throws IOException {
        windowId = buf.readUnsignedByte();
        entityId = buf.readInt();
    }

    @Override
    public void writePacketData(PacketBuffer buf) throws IOException {
        buf.writeByte(windowId);
        buf.writeInt(entityId);
    }

    @Override
    public void processPacket(ClientPlayNetHandler handler) {
        PacketThreadUtil.checkThreadAndEnqueue(this, handler, handler.client);
        Entity entity = handler.world.getEntityByID(this.entityId);
        if(entity instanceof JojoEntity) {
            ClientPlayerEntity player = handler.client.player;
            JojoEntity jojo = (JojoEntity) entity;
            JojoContainer container = new JojoContainer(this.windowId, player.inventory, jojo);
            player.openContainer = container;
            handler.client.displayGuiScreen(new JojoScreen(container, player.inventory, jojo.getName().deepCopy().appendText("'s Bag")));
        }
    }

}
