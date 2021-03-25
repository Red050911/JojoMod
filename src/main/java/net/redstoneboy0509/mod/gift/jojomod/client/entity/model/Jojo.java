package net.redstoneboy0509.mod.gift.jojomod.client.entity.model;
// Made with Blockbench 3.7.4
// Exported for Minecraft version 1.15
// Paste this class into your mod and generate all required imports


import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.redstoneboy0509.mod.gift.jojomod.entities.*;

public class Jojo extends EntityModel<JojoEntity> {
	private final ModelRenderer Body;
	private final ModelRenderer Legs;
	private final ModelRenderer Main;
	private final ModelRenderer Tail;
	private final ModelRenderer Head;

	public Jojo() {
		textureWidth = 64;
		textureHeight = 64;

		Body = new ModelRenderer(this);
		Body.setRotationPoint(-3.0F, 24.0F, -2.0F);
		

		Legs = new ModelRenderer(this);
		Legs.setRotationPoint(0.0F, 0.0F, 0.0F);
		Body.func_78792_a(Legs);
		Legs.func_78784_a(0, 22).func_228303_a_(-1.0F, -7.0F, 0.0F, 2.0F, 7.0F, 1.0F, 0.0F, false);
		Legs.func_78784_a(6, 22).func_228303_a_(6.0F, -7.0F, 0.0F, 2.0F, 7.0F, 1.0F, 0.0F, false);

		Main = new ModelRenderer(this);
		Main.setRotationPoint(0.0F, 0.0F, 0.0F);
		Body.func_78792_a(Main);
		Main.func_78784_a(0, 0).func_228303_a_(-2.0F, -14.0F, -1.0F, 11.0F, 7.0F, 3.0F, 0.0F, false);
		Main.func_78784_a(0, 16).func_228303_a_(-1.0F, -13.0F, -2.0F, 9.0F, 5.0F, 1.0F, 0.0F, false);
		Main.func_78784_a(0, 10).func_228303_a_(-1.0F, -13.0F, 2.0F, 9.0F, 5.0F, 1.0F, 0.0F, false);

		Tail = new ModelRenderer(this);
		Tail.setRotationPoint(0.0F, 0.0F, 0.0F);
		Body.func_78792_a(Tail);
		Tail.func_78784_a(19, 11).func_228303_a_(2.0F, -10.0F, 3.0F, 1.0F, 1.0F, 5.0F, 0.0F, false);
		Tail.func_78784_a(12, 22).func_228303_a_(2.0F, -15.0F, 7.0F, 1.0F, 6.0F, 1.0F, 0.0F, false);

		Head = new ModelRenderer(this);
		Head.setRotationPoint(0.0F, 0.0F, 0.0F);
		Body.func_78792_a(Head);
		Head.func_78784_a(17, 19).func_228303_a_(1.0F, -19.0F, -1.0F, 5.0F, 5.0F, 3.0F, 0.0F, false);
	}

	@Override
	public void func_225597_a_(JojoEntity p_225597_1_, float p_225597_2_, float p_225597_3_, float p_225597_4_, float p_225597_5_, float p_225597_6_) {

	}

	@Override
	public void func_225598_a_(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
		Body.func_228308_a_(matrixStack, buffer, packedLight, packedOverlay);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}