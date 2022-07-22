package studio.renascence.ntr.tile;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.WorldlyContainer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.StackedContents;
import net.minecraft.world.inventory.StackedContentsCompatible;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import studio.renascence.ntr.init.NTRTileTypes;

import javax.annotation.Nullable;
import java.util.Objects;

public class PillarTile extends BaseTileEntity implements StackedContentsCompatible, WorldlyContainer {
    private ItemStackHandler stack = new ItemStackHandler(1);

    public PillarTile(BlockPos pos, BlockState state) {
        super(NTRTileTypes.PILLAR_TILE.get(), pos, state);
    }

    @Override
    public void load(CompoundTag tag) {
        super.load(tag);
        stack.deserializeNBT(tag.getCompound("PilItem"));
    }

    @Override
    protected void saveAdditional(CompoundTag tag) {
        super.saveAdditional(tag);
        tag.put("PilItem", stack.serializeNBT());
    }

    @Override
    public int[] getSlotsForFace(Direction direction) {
        return direction == Direction.UP ? new int[]{0} : null;
    }

    @Override
    public boolean canPlaceItemThroughFace(int slot, ItemStack stack, @Nullable Direction direction) {
        return direction == Direction.UP;
    }

    @Override
    public boolean canTakeItemThroughFace(int slot, ItemStack stack, Direction direction) {
        return direction != Direction.UP;
    }


    @Override
    public int getContainerSize() {
        return this.stack.getSlots();
    }

    @Override
    public boolean isEmpty() {
        return stack.getStackInSlot(0).isEmpty();
    }

    @Override
    public ItemStack getItem(int slot) {
        return stack.getStackInSlot(slot);
    }

    @Override
    public ItemStack removeItem(int slot, int i) {
        return this.stack.extractItem(slot, i, false);
    }

    @Override
    public ItemStack removeItemNoUpdate(int slot) {
        return this.stack.extractItem(slot, Math.min(stack.getSlotLimit(slot), stack.getStackInSlot(slot).getMaxStackSize()), false);
    }

    public void setItem(ItemStack stack) {
        this.setItem(0, stack);
    }

    @Override
    public void setItem(int slot, ItemStack stack) {
        this.stack.setStackInSlot(slot, stack);

        // From org.teacon.xkdeco.blockentity.ItemDisplayBlockEntity.java
        this.setChanged();
        var blockState = this.getBlockState();
        Objects.requireNonNull(this.level).sendBlockUpdated(this.getBlockPos(), blockState, blockState, Block.UPDATE_ALL);
    }

    @Override
    public boolean stillValid(Player player) {
        return true;
    }

    public ItemStack getItem() {
        return stack.getStackInSlot(0);
    }

    @Override
    public void fillStackedContents(StackedContents contents) {
        for (int i = 0; i < this.stack.getSlots(); i++) {
            contents.accountStack(getItem(i));
        }
    }

    @Override
    public void clearContent() {
        setItem(0, ItemStack.EMPTY);
    }

    LazyOptional<? extends IItemHandler>[] handlers =
            net.minecraftforge.items.wrapper.SidedInvWrapper.create(this, Direction.UP, Direction.DOWN, Direction.NORTH);

    @Override
    public <T> LazyOptional<T> getCapability(Capability<T> capability, @Nullable Direction facing) {
        if (!this.remove && facing != null && capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            return handlers[0].cast();
        }
        return super.getCapability(capability, facing);
    }

    @Override
    public void invalidateCaps() {
        super.invalidateCaps();
        for (LazyOptional<? extends IItemHandler> handler : handlers) handler.invalidate();
    }

    @Override
    public void reviveCaps() {
        super.reviveCaps();
        this.handlers = net.minecraftforge.items.wrapper.SidedInvWrapper.create(this, Direction.UP, Direction.DOWN, Direction.NORTH);
    }
}
