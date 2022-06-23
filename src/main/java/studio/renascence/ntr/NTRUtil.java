package studio.renascence.ntr;

import net.minecraftforge.eventbus.api.IEventBus;
import studio.renascence.ntr.init.NTRBlocks;
import studio.renascence.ntr.init.NTRItems;

public class NTRUtil {

    public static void register(IEventBus bus) {
        NTRItems.ITEMS.register(bus);
        NTRBlocks.BLOCKS.register(bus);
    }
}
