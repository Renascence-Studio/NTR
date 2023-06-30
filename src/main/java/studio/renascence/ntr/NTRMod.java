package studio.renascence.ntr;

import com.mojang.logging.LogUtils;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;
import studio.renascence.ntr.init.NTRBlocks;

@Mod(NTRMod.MODID)
public class NTRMod {
    public static final String MODID = "ntr";
    public static final String MODNAME = "Nether Transition Remains";

    private static final Logger LOGGER = LogUtils.getLogger();

    public NTRMod() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();

        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, NTRConfig.COMMON_CONFIG);

        NTRUtil.load();
        NTRUtil.register(bus);

        bus.addListener(this::clientSetup);

        MinecraftForge.EVENT_BUS.register(this);

        LOGGER.info(MODNAME + " loaded!");
    }

    private void clientSetup(FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            ItemBlockRenderTypes.setRenderLayer(NTRBlocks.DECO_ENDER_TRANSMISSION_FIRE.get(), RenderType.cutout());
            ItemBlockRenderTypes.setRenderLayer(NTRBlocks.ENDER_TRANSMIT_FIRE.get(), RenderType.cutout());
        });
    }
}
