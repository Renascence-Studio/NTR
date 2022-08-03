package studio.renascence.ntr.event;

import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import studio.renascence.ntr.world.OreGen;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class CommonEvent {
    @SubscribeEvent
    public static void setup(final FMLCommonSetupEvent event) {
        event.enqueueWork(OreGen::registerOreFeatures);
    }
}
