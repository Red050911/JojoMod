package net.redstoneboy0509.mod.gift.jojomod.registry;

import net.minecraft.inventory.container.ContainerType;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.redstoneboy0509.mod.gift.jojomod.container.JojoCraftingTableContainer;
import net.redstoneboy0509.mod.gift.jojomod.container.JojonicTableContainer;

public class ModContainerTypes {

    public static DeferredRegister<ContainerType<?>> CONTAINER_TYPES = DeferredRegister.create(ForgeRegistries.CONTAINERS, "rjm");

    public static RegistryObject<ContainerType<JojonicTableContainer>> JOJONIC = CONTAINER_TYPES.register("jojonic_table_container", () -> IForgeContainerType.create(JojonicTableContainer::new));

    public static RegistryObject<ContainerType<JojoCraftingTableContainer>> JOJONIC_CRAFTING = CONTAINER_TYPES.register("jojonic_crafting_container", () -> new ContainerType<>(JojoCraftingTableContainer::new));

}
