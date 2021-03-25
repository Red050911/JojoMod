package net.redstoneboy0509.mod.gift.jojomod.registry;

import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.redstoneboy0509.mod.gift.jojomod.world.biome.JojoHillsBiome;

public class ModBiomes {

    public static DeferredRegister<Biome> BIOMES = DeferredRegister.create(ForgeRegistries.BIOMES, "rjm");

    public static RegistryObject<Biome> JOJO_HILLS = BIOMES.register("jojo_hills", JojoHillsBiome::new);

    public static void registerBiomes() {
        registerBiome(JOJO_HILLS.get(), BiomeDictionary.Type.PLAINS, BiomeDictionary.Type.OVERWORLD);
    }

    private static void registerBiome(Biome biome, BiomeDictionary.Type... types) {
        BiomeManager.addBiome(BiomeManager.BiomeType.WARM, new BiomeManager.BiomeEntry(biome, 100));
        BiomeDictionary.addTypes(biome, types);
        BiomeManager.addSpawnBiome(biome);
    }

}
