package studio.renascence.ntr.impl.tile;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import studio.renascence.ntr.impl.block.PillarBlock;

public class PillarTile extends StorageTile {
    public PillarTile(BlockPos pos, BlockState state) {
        super(pos, state, 1);
    }

    @Override
    public void read(CompoundTag nbt, boolean isClient) {
        if (!isClient) {
            list.set(0, ItemStack.of(nbt.getCompound("list")));
        }
    }

    @Override
    public void write(CompoundTag nbt, boolean isClient) {
        if (!isClient) {
            nbt.put("list", list.get(0).serializeNBT());
        }
    }

    public static void tick(Level level, BlockPos pos, BlockState state, PillarTile tile) {
        if (tile.getItem(0).getItem() instanceof BlockItem blockItem) {
            Block innerBlock = blockItem.getBlock();
            if (innerBlock.getLightEmission(innerBlock.defaultBlockState(), level, pos) > 0) {
                state.trySetValue(PillarBlock.LIT,Boolean.TRUE);
            }
        }else {
            state.trySetValue(PillarBlock.LIT, Boolean.FALSE);
        }
    }
}
