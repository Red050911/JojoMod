package net.redstoneboy0509.mod.gift.jojomod;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.CraftingTableBlock;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.WolfEntity;
import net.minecraft.inventory.CraftingInventory;
import net.minecraft.item.crafting.ShapedRecipe;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.redstoneboy0509.mod.gift.jojomod.items.ModSpawnEggItem;
import net.redstoneboy0509.mod.gift.jojomod.registry.DeferredHandler;
import net.redstoneboy0509.mod.gift.jojomod.registry.ModBiomes;
import net.redstoneboy0509.mod.gift.jojomod.registry.ModEntityTypes;
import net.redstoneboy0509.mod.gift.jojomod.world.biome.JojoHillsBiome;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.stream.Collectors;

@Mod.EventBusSubscriber(modid = "rjm", bus = Mod.EventBusSubscriber.Bus.MOD)
@Mod("rjm")
public class JojoMod {


    private static final Logger LOGGER = LogManager.getLogger();

    public JojoMod() {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::enqueueIMC);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::processIMC);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::doClientStuff);
        DeferredHandler.registerAll(FMLJavaModLoadingContext.get().getModEventBus());
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void setup(final FMLCommonSetupEvent event) {

    }

    private void doClientStuff(final FMLClientSetupEvent event) {

    }

    private void enqueueIMC(final InterModEnqueueEvent event) {

    }

    private void processIMC(final InterModProcessEvent event) {

    }

    @SubscribeEvent
    public static void onBiomesRegister(RegistryEvent.Register<Biome> event) {
        ModBiomes.registerBiomes();
    }

    @SubscribeEvent
    public static void onEntityTypesRegister(RegistryEvent.Register<EntityType<?>> event) {
        ((JojoHillsBiome) ModBiomes.JOJO_HILLS.get()).addSpawnEntity(EntityClassification.CREATURE, new Biome.SpawnListEntry(ModEntityTypes.JOJO.get(), 10, 2, 5));
        ModSpawnEggItem.initSpawnEggs();
    }

}
