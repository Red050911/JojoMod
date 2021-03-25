package net.redstoneboy0509.mod.gift.jojomod.client.util;

import net.minecraft.client.gui.ScreenManager;
import net.minecraft.entity.EntityType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.redstoneboy0509.mod.gift.jojomod.client.entity.renderer.JojoRenderer;
import net.redstoneboy0509.mod.gift.jojomod.client.gui.JojonicCraftingTableScreen;
import net.redstoneboy0509.mod.gift.jojomod.client.gui.JojonicTableScreen;
import net.redstoneboy0509.mod.gift.jojomod.container.JojoCraftingTableContainer;
import net.redstoneboy0509.mod.gift.jojomod.entities.JojoEntity;
import net.redstoneboy0509.mod.gift.jojomod.registry.ModContainerTypes;
import net.redstoneboy0509.mod.gift.jojomod.registry.ModEntityTypes;

@Mod.EventBusSubscriber(modid = "rjm", bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientSetup {

    @SubscribeEvent
    public static void setupClient(FMLClientSetupEvent event) {
        RenderingRegistry.registerEntityRenderingHandler((EntityType<JojoEntity>) ModEntityTypes.JOJO.get(), JojoRenderer::new);
        ScreenManager.registerFactory(ModContainerTypes.JOJONIC.get(), JojonicTableScreen::new);
        ScreenManager.registerFactory(ModContainerTypes.JOJONIC_CRAFTING.get(), JojonicCraftingTableScreen::new);
    }

}
