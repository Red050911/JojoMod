package net.redstoneboy0509.mod.gift.jojomod.registry;

import net.minecraftforge.eventbus.api.IEventBus;

public class DeferredHandler {

    public static void registerAll(IEventBus bus) {
        ModBlocks.BLOCKS.register(bus);
        ModBlocks.BLOCK_ITEMS.register(bus);
        ModSounds.SOUNDS.register(bus);
        ModItems.REGISTER.register(bus);
        ModEntityTypes.ENTITY_TYPES.register(bus);
        ModBiomes.BIOMES.register(bus);
        ModTileEntityTypes.TILE_ENTITY_TYPES.register(bus);
        ModContainerTypes.CONTAINER_TYPES.register(bus);
    }

}
