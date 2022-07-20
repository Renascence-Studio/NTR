package studio.renascence.ntr;

import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.eventbus.api.IEventBus;
import studio.renascence.ntr.init.NTRBlocks;
import studio.renascence.ntr.init.NTRItems;
import studio.renascence.ntr.init.NTRTileTypes;

public class NTRUtil {

    public static void register(IEventBus bus) {
        NTRItems.ITEMS.register(bus);
        NTRBlocks.BLOCKS.register(bus);
        NTRTileTypes.TYPES.register(bus);
    }

    public static ResourceLocation modRL(String path) {
        return new ResourceLocation(NTR.MODID, path);
    }
}
