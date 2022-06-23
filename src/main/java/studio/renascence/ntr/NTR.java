package studio.renascence.ntr;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(NTR.MODID)
public class NTR {
    public static final String MODID = "ntr";

    public NTR() {
        final IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();

        NTRUtil.register(bus);
        MinecraftForge.EVENT_BUS.register(this);
    }
}
