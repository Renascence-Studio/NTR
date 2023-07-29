package studio.renascence.ntr.impl.tile.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import studio.renascence.ntr.impl.tile.PillarTile;

@OnlyIn(Dist.CLIENT)
public class PillarTileRenderer implements BlockEntityRenderer<PillarTile> {
    private final ItemRenderer itemRenderer;

    public PillarTileRenderer(BlockEntityRendererProvider.Context context) {
        this.itemRenderer = context.getItemRenderer();
    }

    @Override
    public void render(PillarTile tile, float v, PoseStack pose, MultiBufferSource source, int i1, int i2) {
        ItemStack stack = tile.getItem(1);

        if (!stack.isEmpty()) {

            pose.pushPose();
            pose.scale(0.5f,0.5f,0.5f);
            pose.translate(1, 2.4, 1);
            double tick = System.currentTimeMillis() / 800.0D;
            pose.translate(0.0D, Math.sin(tick % (2 * Math.PI)) * 0.065D, 0.0D);
            pose.mulPose(Axis.YP.rotationDegrees((float) (tick * 40.0D) % 360));
            itemRenderer.renderStatic(stack, ItemDisplayContext.FIXED, i1, i2, pose, source, tile.getLevel(), (int) tile.getBlockPos().asLong());

            pose.popPose();
        }
    }
}
