package net.redstoneboy0509.mod.gift.jojomod.registry;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.common.util.Lazy;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModSounds {

    public static DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, "rjm");

    public static Lazy<SoundEvent> LAZY_JOJOTUNE = Lazy.of(() -> new SoundEvent(new ResourceLocation("rjm", "disc.jojotune")));

    public static RegistryObject<SoundEvent> JOJOTUNE = SOUNDS.register("disc.jojotune", LAZY_JOJOTUNE);

}
