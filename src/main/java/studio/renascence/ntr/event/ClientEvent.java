package studio.renascence.ntr.event;

import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.registries.RegistryObject;
import studio.renascence.ntr.init.NTRBlocks;
import studio.renascence.ntr.init.NTRTileTypes;
import studio.renascence.ntr.tile.render.PillarTileRenderer;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientEvent {
    @SubscribeEvent
    public static void onRenderTypeSetup(FMLClientSetupEvent event) {
        for (RegistryObject<Block> block : NTRBlocks.BLOCKS.getEntries()){
            event.enqueueWork(() -> ItemBlockRenderTypes.setRenderLayer(block.get(), RenderType.cutout()));
        }
    }

    @SubscribeEvent
    public static void onTileRenderSetup(FMLClientSetupEvent event) {
        event.enqueueWork(() -> BlockEntityRenderers.register(NTRTileTypes.PILLAR_TILE.get(), PillarTileRenderer::new));
    }
}
