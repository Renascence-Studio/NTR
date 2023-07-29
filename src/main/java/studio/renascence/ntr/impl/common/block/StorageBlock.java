package studio.renascence.ntr.impl.common.block;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.Containers;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;
import studio.renascence.ntr.impl.common.tile.StorageTile;

import java.util.function.BiFunction;

public class StorageBlock extends BaseEntityBlock {
    private final BiFunction<BlockPos, BlockState, BlockEntity> tile;

    public StorageBlock(Properties properties, BiFunction<BlockPos, BlockState, BlockEntity> tile) {
        super(properties);
        this.tile = tile;
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return tile.apply(pos, state);
    }

    public void onRemove(BlockState state, Level level, BlockPos pos, BlockState state1, boolean flag) {
        if (!state.is(state1.getBlock())) {
            BlockEntity blockentity = level.getBlockEntity(pos);
            if (blockentity instanceof StorageTile) {
                if (level instanceof ServerLevel) {
                    Containers.dropContents(level, pos, (StorageTile)blockentity);
                }
                level.updateNeighbourForOutputSignal(pos, this);
            }
            super.onRemove(state, level, pos, state1, flag);
        }
    }

    @Override
    public RenderShape getRenderShape(BlockState p_48727_) {
        return RenderShape.MODEL;
    }

}
