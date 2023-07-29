package studio.renascence.ntr.impl.common.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import studio.renascence.ntr.impl.common.tile.PillarTile;

public class PillarBlock extends StorageBlock {

    public PillarBlock(Properties properties) {
        super(properties, PillarTile::new);
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult result) {
        if (level.getBlockEntity(pos) != null && level.getBlockEntity(pos) instanceof PillarTile tile) {
            ItemStack handStack = player.getItemInHand(hand);
            ItemStack tileStack = tile.getItem(0);
            if (tile.isEmpty() && handStack.isEmpty())
                return super.use(state, level, pos, player, hand, result);
            else if (tile.isEmpty()) {
                tile.setItem(0, handStack.split(1));
                return InteractionResult.sidedSuccess(level.isClientSide);
            } else if (handStack.isEmpty()) {
                ItemStack stack = tileStack.split(1);
                if (!player.addItem(stack)) {
                    player.drop(stack, false);
                }
                return InteractionResult.sidedSuccess(level.isClientSide);
            } else {
                ItemStack stack = tileStack.split(1);
                if (!handStack.is(tileStack.getItem())) {
                    if (!player.addItem(stack)) {
                        player.drop(stack, false);
                    }
                    tile.setItem(0, handStack.split(1));
                    return InteractionResult.sidedSuccess(level.isClientSide);
                }
            }
        }
        return super.use(state, level, pos, player, hand, result);
    }

    @Override
    public VoxelShape getShape(BlockState p_60555_, BlockGetter p_60556_, BlockPos p_60557_, CollisionContext p_60558_) {
        VoxelShape shape1 = box(1, 0, 1, 15, 1, 15);
        VoxelShape shape2 = box(2, 1, 2, 14, 2, 14);
        VoxelShape shape3 = box(3, 2, 3, 13, 12, 13);
        VoxelShape shape4 = box(2, 12, 2, 14, 13, 14);
        VoxelShape shape5 = box(1, 13, 1, 15, 14, 15);
        VoxelShape shape6 = box(2, 14, 2, 14, 15, 14);
        return Shapes.or(shape1, shape2, shape3, shape4, shape5, shape6);
    }

    @Override
    public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource randomSource) {
        if (level.getBlockEntity(pos) != null && level.getBlockEntity(pos) instanceof PillarTile tile) {
            if (tile.getItem(0).is(Items.ENDER_EYE)) {
                double d0 = pos.getX() + 0.5;
                double d1 = pos.getY() + 0.5;
                double d2 = pos.getZ() + 0.5;
                level.addParticle(ParticleTypes.PORTAL, d0 + 0.2, d1, d2 + 0.2, 0, 0.35, 0.0);
                level.addParticle(ParticleTypes.PORTAL, d0 + 0.2, d1, d2 - 0.2, 0, 0.35, 0.0);
                level.addParticle(ParticleTypes.PORTAL, d0 - 0.2, d1, d2 + 0.2, 0, 0.35, 0.0);
                level.addParticle(ParticleTypes.PORTAL, d0 - 0.2, d1, d2 - 0.2, 0, 0.35, 0.0);
            }
        }
    }

    @Override
    public float getShadeBrightness(BlockState state, BlockGetter getter, BlockPos pos) {
        return 1.0F;
    }
}
