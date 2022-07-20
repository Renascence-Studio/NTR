package studio.renascence.ntr;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import studio.renascence.ntr.compat.NTRSimmer;

@Mod(NTR.MODID)
public class NTR {
    public static final String MODID = "ntr";

    public NTR() {
        final IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();

        if (ModList.get().isLoaded("shimmer")) {
            FMLJavaModLoadingContext.get().getModEventBus().addListener(NTRSimmer::setup);
        }

        NTRUtil.register(bus);
        MinecraftForge.EVENT_BUS.register(this);
    }
}
