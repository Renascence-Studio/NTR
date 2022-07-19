package studio.renascence.ntr.event;

import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import studio.renascence.ntr.init.NTRBlocks;
import studio.renascence.ntr.init.NTRTileTypes;
import studio.renascence.ntr.tile.render.PillarTileRenderer;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientEvent {
    @SubscribeEvent
    public static void onRenderTypeSetup(FMLClientSetupEvent event) {
        event.enqueueWork(() -> ItemBlockRenderTypes.setRenderLayer(NTRBlocks.ENDER_TRANSMIT_FIRE.get(), RenderType.cutout()));
    }

    @SubscribeEvent
    public static void onTileRenderSetup(FMLClientSetupEvent event) {
        event.enqueueWork(() -> BlockEntityRenderers.register(NTRTileTypes.PILLAR_TILE.get(), PillarTileRenderer::new));
    }
}
