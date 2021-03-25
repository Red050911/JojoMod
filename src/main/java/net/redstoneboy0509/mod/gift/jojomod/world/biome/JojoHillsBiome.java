package net.redstoneboy0509.mod.gift.jojomod.world.biome;

import net.minecraft.entity.EntityClassification;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.DefaultBiomeFeatures;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;
import net.redstoneboy0509.mod.gift.jojomod.registry.ModEntityTypes;

public class JojoHillsBiome extends Biome {

    public JojoHillsBiome() {
        super(new Builder().waterColor(0x675320).precipitation(RainType.NONE).waterFogColor(0x675320).scale(1.2f).temperature(0.5f).category(Category.PLAINS).downfall(0.5f).depth(0.12f).surfaceBuilder(SurfaceBuilder.DEFAULT, new SurfaceBuilderConfig(SurfaceBuilder.GRASS_BLOCK, SurfaceBuilder.DIRT, SurfaceBuilder.PODZOL)).parent(null));
        DefaultBiomeFeatures.addOakTreesFlowersGrass(this);
        DefaultBiomeFeatures.addLakes(this);
    }

    @Override
    public int func_225528_a_(double p_225528_1_, double p_225528_3_) {
        return 0x675320;
    }

    public void addSpawnEntity(EntityClassification type, SpawnListEntry spawnListEntry) {
        super.addSpawn(type, spawnListEntry);
    }

}
