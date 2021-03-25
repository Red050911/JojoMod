package net.redstoneboy0509.mod.gift.jojomod.items;

import net.minecraft.item.MusicDiscItem;
import net.minecraft.util.SoundEvent;

import java.util.function.Supplier;

public class ModMusicDiscItem extends MusicDiscItem {

    public ModMusicDiscItem(int comparatorValue, Supplier<SoundEvent> soundSupplier, Properties builder) {
        super(comparatorValue, soundSupplier, builder);
    }

}
