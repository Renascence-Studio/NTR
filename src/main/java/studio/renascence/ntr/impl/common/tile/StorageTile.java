package studio.renascence.ntr.impl.common.tile;

import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Connection;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.Container;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;
import studio.renascence.ntr.init.NTRBlockEntityTypes;

public abstract class StorageTile extends BlockEntity implements Container {
    protected NonNullList<ItemStack> list;

    public StorageTile( BlockPos pos, BlockState state, int size) {
        super(NTRBlockEntityTypes.SANGUINITE_PILLAR.get(), pos, state);
        list = NonNullList.withSize(size, ItemStack.EMPTY);
    }

    public abstract void read(CompoundTag nbt, boolean isClient);

    public abstract void write(CompoundTag nbt, boolean isClient);

    @Override
    public void onDataPacket(Connection net, ClientboundBlockEntityDataPacket pkt) {
        this.read(pkt.getTag(), true);
    }

    @Override
    public void load(CompoundTag nbt) {
        this.read(nbt, false);
        super.load(nbt);
    }

    @Override
    protected void saveAdditional(CompoundTag compound) {
        this.write(compound, false);
        super.saveAdditional(compound);
    }

    @Override
    public CompoundTag getUpdateTag() {
        CompoundTag nbt = super.getUpdateTag();
        write(nbt, true);
        return nbt;
    }

    @Nullable
    @Override
    public Packet<ClientGamePacketListener> getUpdatePacket() {
        CompoundTag nbt = new CompoundTag();
        write(nbt, true);
        return ClientboundBlockEntityDataPacket.create(this, i -> nbt);
    }

    @Override
    public int getContainerSize() {
        return list.size();
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public ItemStack getItem(int id) {
        return list.get(id);
    }

    @Override
    public ItemStack removeItem(int index, int count) {
        return ContainerHelper.removeItem(list, index, count);
    }

    @Override
    public ItemStack removeItemNoUpdate(int id) {
        return ContainerHelper.takeItem(list, id);
    }

    @Override
    public void setItem(int id, ItemStack stack) {
        this.list.set(id, stack);
        if (stack.getCount() > this.getMaxStackSize()) {
            stack.setCount(this.getMaxStackSize());
        }
    }

    @Override
    public boolean stillValid(Player player) {
        return true;
    }

    @Override
    public void clearContent() {
        list.clear();
    }

}
