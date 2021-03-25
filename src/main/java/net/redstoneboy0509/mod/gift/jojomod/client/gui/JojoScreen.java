package net.redstoneboy0509.mod.gift.jojomod.client.gui;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.redstoneboy0509.mod.gift.jojomod.container.JojoContainer;
import net.redstoneboy0509.mod.gift.jojomod.container.JojonicTableContainer;

public class JojoScreen extends ContainerScreen<JojoContainer> {

    public static final ResourceLocation TEXTURE = new ResourceLocation("rjm", "textures/gui/jojo.png");

    public JojoScreen(JojoContainer screenContainer, PlayerInventory inv, ITextComponent titleIn) {
        super(screenContainer, inv, titleIn);

        this.guiTop = 0;
        this.guiLeft = 0;
        this.xSize = 176;
        this.ySize = 132;
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        RenderSystem.color4f(1.0f, 1.0f, 1.0f, 1.0f);
        this.minecraft.getTextureManager().bindTexture(TEXTURE);
        this.blit(this.guiLeft, this.guiTop, 0, 0, this.xSize, this.ySize);
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        super.drawGuiContainerForegroundLayer(mouseX, mouseY);
        this.font.drawString(this.title.getFormattedText(), 8.0f, 8.0f, 0x404040);
        this.font.drawString(this.playerInventory.getDisplayName().getFormattedText(), 8.0f, 38.0f, 0x404040);
    }

    @Override
    public void render(int mouseX, int mouseY, float partialTicks) {
        this.renderBackground();
        super.render(mouseX, mouseY, partialTicks);
        this.renderHoveredToolTip(mouseX, mouseY);
    }

}
