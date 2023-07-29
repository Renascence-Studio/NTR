package studio.renascence.ntr.impl.common.tile;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;

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
}
