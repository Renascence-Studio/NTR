package studio.renascence.ntr.tile.render;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import studio.renascence.ntr.tile.PillarTile;

@OnlyIn(Dist.CLIENT)
public class PillarTileRenderer implements BlockEntityRenderer<PillarTile> {

    public PillarTileRenderer(BlockEntityRendererProvider.Context context) {
    }

    @Override
    public void render(PillarTile tile, float time, PoseStack pose, MultiBufferSource source, int light, int overlay) {
        if (!tile.getItem(0).isEmpty()) {
            ItemStack stack = tile.getItem(0);
            ItemRenderer itemRenderer = Minecraft.getInstance().getItemRenderer();
            BakedModel bakedModel = itemRenderer.getModel(stack, tile.getLevel(), null, 0);
            pose.pushPose();
            pose.scale(0.5f,0.5f,0.5f);
            pose.translate(1, 2.4, 1);
            double tick = System.currentTimeMillis() / 800.0D;
            pose.translate(0.0D, Math.sin(tick % (2 * Math.PI)) * 0.065D, 0.0D);
            pose.mulPose(Vector3f.YP.rotationDegrees((float) ((tick * 40.0D) % 360)));
            itemRenderer.render(stack, ItemTransforms.TransformType.FIXED, true, pose, source, light, overlay, bakedModel);
            pose.popPose();
        }
    }
}
