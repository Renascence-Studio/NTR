package studio.renascence.ntr.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.Containers;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;
import studio.renascence.ntr.tile.PillarTile;

import javax.annotation.Nonnull;
import java.util.Random;

public class PillarBlock extends BaseEntityBlock {
    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;

    public PillarBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH));
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter getter, BlockPos pos, CollisionContext context) {
        VoxelShape shape1 = Block.box(1, 0, 1, 15, 1, 15);
        VoxelShape shape2 = Block.box(2, 1, 2, 14, 2, 14);
        VoxelShape shape3 = Block.box(3, 2, 3, 13, 12, 13);
        VoxelShape shape4 = Block.box(2, 12, 2, 14, 13, 14);
        VoxelShape shape5 = Block.box(1, 13, 1, 15, 14, 15);
        VoxelShape shape6 = Block.box(2, 14, 2, 14, 15, 14);
        return Shapes.or(shape1, shape2, shape3, shape4, shape5, shape6);
    }

    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite());
    }

    public boolean hasAnalogOutputSignal(BlockState state) {
        return true;
    }

    public int getAnalogOutputSignal(BlockState state, Level level, BlockPos pos) {
        return AbstractContainerMenu.getRedstoneSignalFromBlockEntity(level.getBlockEntity(pos));
    }

    public BlockState rotate(BlockState state, Rotation rotation) {
        return state.setValue(FACING, rotation.rotate(state.getValue(FACING)));
    }

    public BlockState mirror(BlockState state, Mirror mirror) {
        return state.rotate(mirror.getRotation(state.getValue(FACING)));
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }

    @Override
    public int getLightEmission(BlockState state, BlockGetter level, BlockPos pos) {
        return 5;
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult result) {
        BlockEntity blockentity = level.getBlockEntity(pos);
        if (blockentity instanceof PillarTile tile) {
            ItemStack handStack = player.getItemInHand(hand);
            if (tile.isEmpty() && !handStack.isEmpty()) {
                tile.setItem(getCountedItem(1, handStack));
                handStack.shrink(1);
                return InteractionResult.SUCCESS;
            } else if (!tile.isEmpty() && handStack.isEmpty()) {
                if (!player.addItem(tile.getItem())) {
                    player.drop(tile.getItem(), false);
                }
                tile.setItem(ItemStack.EMPTY);
                return InteractionResult.SUCCESS;
            }
        }
        return InteractionResult.PASS;
    }

    public static ItemStack getCountedItem(int count, ItemStack stack) {
        ItemStack stack1 = stack.copy();
        stack1.setCount(count);
        return stack1;
    }

    @Override
    public void animateTick(BlockState state, Level level, BlockPos pos, Random random) {
        BlockEntity blockentity = level.getBlockEntity(pos);
        if (blockentity instanceof PillarTile) {
            if (((PillarTile) blockentity).getItem(0).is(Items.ENDER_EYE)) {
                for (int j = 0; j < 5; ++j) {
                    double d0 = (double) pos.getX() + random.nextDouble();
                    double d1 = (double) pos.getY() + 0.5D + random.nextDouble();
                    double d2 = (double) pos.getZ() + random.nextDouble();
                    double d3 = (random.nextDouble() - 0.5D) * 0.5D;
                    double d4 = (random.nextDouble() - 0.5D) * 0.5D;
                    double d5 = (random.nextDouble() - 0.5D) * 0.5D;
                    int k = random.nextInt(2) * 2 - 1;
                    if (random.nextBoolean()) {
                        d2 = (double) pos.getZ() + 0.5D + 0.25D * (double) k;
                        d5 = random.nextFloat() * 2.0F * (float) k;
                    } else {
                        d0 = (double) pos.getX() + 0.5D + 0.25D * (double) k;
                        d3 = random.nextFloat() * 2.0F * (float) k;
                    }

                    level.addParticle(ParticleTypes.PORTAL, d0, d1, d2, d3, d4, d5);
                }
            }
        }
    }

    public void onRemove(BlockState state, Level level, BlockPos pos, BlockState state1, boolean b) {
        if (!state.is(state1.getBlock())) {
            BlockEntity blockentity = level.getBlockEntity(pos);
            if (blockentity instanceof PillarTile) {
                if (level instanceof ServerLevel) {
                    Containers.dropContents(level, pos, (PillarTile) blockentity);
                }

                level.updateNeighbourForOutputSignal(pos, this);
            }

            super.onRemove(state, level, pos, state1, b);
        }
    }

    @Nonnull
    @Override
    public RenderShape getRenderShape(@Nonnull BlockState state) {
        return RenderShape.MODEL;
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new PillarTile(pos, state);
    }
}
