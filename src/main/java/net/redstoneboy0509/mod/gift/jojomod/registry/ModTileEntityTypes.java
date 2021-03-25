package net.redstoneboy0509.mod.gift.jojomod.registry;

import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.redstoneboy0509.mod.gift.jojomod.tileentity.JojonicSigilTableTileEntity;

public class ModTileEntityTypes {

    public static DeferredRegister<TileEntityType<?>> TILE_ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, "rjm");

    public static RegistryObject<TileEntityType<JojonicSigilTableTileEntity>> JOJONIC_TABLE = TILE_ENTITY_TYPES.register("jojonic_table_te", () -> TileEntityType.Builder.create(JojonicSigilTableTileEntity::new, ModBlocks.JOJONIC_TABLE.get()).build(null));

}
