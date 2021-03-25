package net.redstoneboy0509.mod.gift.jojomod.registry;

import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.redstoneboy0509.mod.gift.jojomod.entities.JojoEntity;

public class ModEntityTypes {

    public static DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITIES, "rjm");

    public static RegistryObject<EntityType<?>> JOJO = ENTITY_TYPES.register("jojo", () -> EntityType.Builder.<JojoEntity>create(JojoEntity::new, EntityClassification.CREATURE).size(0.9f, 1.4f).build(new ResourceLocation("rjm", "jojo").toString()));

}
