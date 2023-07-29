package studio.renascence.ntr;

import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.eventbus.api.IEventBus;
import studio.renascence.ntr.init.NTRBlocks;
import studio.renascence.ntr.init.NTRItems;
import studio.renascence.ntr.init.NTRTabs;
import studio.renascence.ntr.init.NTRBlockEntityTypes;

public class NTRUtil {

    public static void register(IEventBus bus) {
        NTRItems.register(bus);
        NTRBlocks.register(bus);
        NTRTabs.register(bus);
        NTRBlockEntityTypes.register(bus);
    }

    public static ResourceLocation rl(String path) {
        return new ResourceLocation(NTRMod.MODID, path);
    }
}
