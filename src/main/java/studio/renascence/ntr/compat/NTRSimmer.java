package studio.renascence.ntr.compat;

import com.lowdragmc.shimmer.client.light.ColorPointLight;
import com.lowdragmc.shimmer.client.light.LightManager;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import studio.renascence.ntr.init.NTRBlocks;

public class NTRSimmer {
    public static void setup(final FMLClientSetupEvent event) {
        event.enqueueWork(() -> LightManager.INSTANCE.registerBlockLight(NTRBlocks.ENDER_TRANSMIT_FIRE.get(),
                (state, pos) -> new ColorPointLight.Template(8, 0xff45D1BE)));
        event.enqueueWork(() -> LightManager.INSTANCE.registerBlockLight(NTRBlocks.DECO_ENDER_TRANSMISSION_FIRE.get(),
                (state, pos) -> new ColorPointLight.Template(8, 0xff45D1BE)));
    }
}
