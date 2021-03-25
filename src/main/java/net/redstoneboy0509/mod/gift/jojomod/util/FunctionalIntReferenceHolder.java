package net.redstoneboy0509.mod.gift.jojomod.util;

import net.minecraft.util.IntReferenceHolder;

import java.util.function.IntConsumer;
import java.util.function.IntSupplier;

public class FunctionalIntReferenceHolder extends IntReferenceHolder {

    private IntSupplier getter;
    private IntConsumer setter;

    public FunctionalIntReferenceHolder(final IntSupplier getter, final IntConsumer setter) {
        this.getter = getter;
        this.setter = setter;
    }

    @Override
    public int get() {
        return getter.getAsInt();
    }

    @Override
    public void set(final int val) {
        setter.accept(val);
    }

}
