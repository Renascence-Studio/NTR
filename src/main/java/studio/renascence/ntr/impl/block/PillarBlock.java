package studio.renascence.ntr.impl.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import studio.renascence.ntr.impl.tile.PillarTile;
import studio.renascence.ntr.init.NTRBlockEntityTypes;

public class PillarBlock extends StorageBlock {
    public static final BooleanProperty LIT = BlockStateProperties.LIT;

    public PillarBlock(Properties properties) {
        super(properties.lightLevel((state) -> state.getValue(LIT) ? 15 : 0), PillarTile::new);
        this.registerDefaultState(this.stateDefinition.any().setValue(LIT, Boolean.FALSE));
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
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(LIT);
    }

    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> entityType) {
        return level.isClientSide ? null : createTickerHelper(entityType, NTRBlockEntityTypes.SANGUINITE_PILLAR.get(), PillarTile::tick);
    }

    @Override
    public VoxelShape getShape(BlockState p_60555_, BlockGetter p_60556_, BlockPos p_60557_, CollisionContext p_60558_) {
        VoxelShape shape1 = Block.box(1, 0, 1, 15, 1, 15);
        VoxelShape shape2 = Block.box(2, 1, 2, 14, 2, 14);
        VoxelShape shape3 = Block.box(3, 2, 3, 13, 12, 13);
        VoxelShape shape4 = Block.box(2, 12, 2, 14, 13, 14);
        VoxelShape shape5 = Block.box(1, 13, 1, 15, 14, 15);
        VoxelShape shape6 = Block.box(2, 14, 2, 14, 15, 14);
        return Shapes.or(shape1, shape2, shape3, shape4, shape5, shape6);
    }

    @Override
    public float getShadeBrightness(BlockState state, BlockGetter getter, BlockPos pos) {
        return 0.0F;
    }
}
