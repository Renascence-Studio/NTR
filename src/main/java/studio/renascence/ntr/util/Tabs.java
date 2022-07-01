package studio.renascence.ntr.util;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import studio.renascence.ntr.NTR;
import studio.renascence.ntr.init.NTRItems;

import javax.annotation.Nonnull;

public class Tabs {
    public static final CreativeModeTab MAIN = new CreativeModeTab(NTR.MODID) {
        @Nonnull
        @Override
        public ItemStack makeIcon() {
            return NTRItems.SANGUINITE_BRICKS.get().getDefaultInstance();
        }
    };
}
