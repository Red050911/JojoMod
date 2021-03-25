package net.redstoneboy0509.mod.gift.jojomod.client.entity.renderer;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.redstoneboy0509.mod.gift.jojomod.client.entity.model.Jojo;
import net.redstoneboy0509.mod.gift.jojomod.entities.JojoEntity;

public class JojoRenderer extends MobRenderer<JojoEntity, Jojo> {

    protected static final ResourceLocation TEXTURE = new ResourceLocation("rjm", "textures/entity/jojo.png");

    public JojoRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new Jojo(), 0.5f);
    }

    @Override
    public ResourceLocation getEntityTexture(JojoEntity entity) {
        return TEXTURE;
    }

}
