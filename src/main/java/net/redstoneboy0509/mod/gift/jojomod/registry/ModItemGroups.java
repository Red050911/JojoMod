package net.redstoneboy0509.mod.gift.jojomod.registry;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class ModItemGroups {

    public static final JojomodTab JOJOMOD_TAB = new JojomodTab();

    public static class JojomodTab extends ItemGroup {

        public JojomodTab() {
            super("rjm");
        }

        @Override
        public ItemStack createIcon() {
            return new ItemStack(ModBlocks.JOJONIC_TABLE_ITEM.get());
        }

    }

}
