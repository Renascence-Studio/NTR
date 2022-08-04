package studio.renascence.ntr.item;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LiquidBlockContainer;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.fluids.capability.wrappers.FluidBucketWrapper;
import org.jetbrains.annotations.Nullable;
import studio.renascence.ntr.init.NTRItems;

import java.util.function.Supplier;

import static net.minecraft.world.level.block.Block.UPDATE_ALL;

public class TranBucketItem extends Item implements DispensibleContainerItem {
    private final Fluid fluid;

    public TranBucketItem(Supplier<? extends Fluid> supplier, Properties properties) {
        super(properties);
        this.fluid = supplier.get();
    }

    @Override
    public boolean emptyContents(@Nullable Player player, Level level, BlockPos pos, @Nullable BlockHitResult p_150824_) {
        return false;
    }

    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack itemstack = player.getItemInHand(hand);
        BlockHitResult result = getPlayerPOVHitResult(level, player, this.fluid == Fluids.EMPTY ? ClipContext.Fluid.SOURCE_ONLY : ClipContext.Fluid.NONE);
        if (result.getType() == HitResult.Type.MISS) {
            return InteractionResultHolder.pass(itemstack);
        }
        BlockPos blockpos = result.getBlockPos();
        Direction direction = result.getDirection();
        BlockPos blockpos1 = blockpos.relative(direction);
        if (fluid == Fluids.EMPTY && level.mayInteract(player, blockpos) && player.mayUseItemAt(blockpos1, direction, itemstack)) {
            ItemStack itemstack1 = ItemStack.EMPTY;
            if (level.getBlockState(result.getBlockPos()).is(Blocks.WATER)) {
                itemstack1 = NTRItems.WATER_TRANSMISSION_BUCKET.get().getDefaultInstance();
            }else if (level.getBlockState(result.getBlockPos()).is(Blocks.LAVA)) {
                itemstack1 = NTRItems.LAVA_TRANSMISSION_BUCKET.get().getDefaultInstance();
            }
            if (!itemstack1.isEmpty()) {
                player.awardStat(Stats.ITEM_USED.get(this));
                level.gameEvent(player, GameEvent.FLUID_PICKUP, blockpos);
                ItemStack itemstack2 = ItemUtils.createFilledResult(itemstack, player, itemstack1);
                if (!level.isClientSide) {
                    CriteriaTriggers.FILLED_BUCKET.trigger((ServerPlayer)player, itemstack1);
                }
                level.setBlock(blockpos, Blocks.AIR.defaultBlockState(), UPDATE_ALL);

                return InteractionResultHolder.sidedSuccess(itemstack2, level.isClientSide());
            }
        }else {
            BlockState blockstate = level.getBlockState(blockpos);
            BlockPos blockpos2 = canBlockContainFluid(level, blockpos, blockstate) ? blockpos : blockpos1;
            if (this.nEmptyContents(player, level, blockpos2, result)) {
                this.checkExtraContent(player, level, itemstack, blockpos2);
                if (player instanceof ServerPlayer) {
                    CriteriaTriggers.PLACED_BLOCK.trigger((ServerPlayer)player, blockpos2, itemstack);
                }
                player.awardStat(Stats.ITEM_USED.get(this));
                return InteractionResultHolder.sidedSuccess(getEmptySuccessItem(itemstack, player), level.isClientSide());
            }
            return InteractionResultHolder.fail(itemstack);
        }
        return InteractionResultHolder.pass(itemstack);
    }

    public static ItemStack getEmptySuccessItem(ItemStack stack, Player player) {
        return !player.getAbilities().instabuild ? NTRItems.TRANSMISSION_BUCKET.get().getDefaultInstance() : stack;
    }

    public void checkExtraContent(@Nullable Player player, Level level, ItemStack stack, BlockPos pos) {
    }

    protected void playEmptySound(@Nullable Player player, LevelAccessor accessor, BlockPos pos) {
        SoundEvent soundevent = this.fluid.getAttributes().getEmptySound();
        if(soundevent == null) soundevent = this.fluid.is(FluidTags.LAVA) ? SoundEvents.BUCKET_EMPTY_LAVA : SoundEvents.BUCKET_EMPTY;
        accessor.playSound(player, pos, soundevent, SoundSource.BLOCKS, 1.0F, 1.0F);
        accessor.gameEvent(player, GameEvent.FLUID_PLACE, pos);
    }

    @Override
    public ICapabilityProvider initCapabilities(ItemStack stack, @Nullable CompoundTag nbt) {
        if (this.getClass() == TranBucketItem.class)
            return new FluidBucketWrapper(stack);
        else
            return super.initCapabilities(stack, nbt);
    }

    public boolean nEmptyContents(@Nullable Player player, Level level, BlockPos pos, @Nullable BlockHitResult result) {
        if (!(this.fluid instanceof FlowingFluid)) {
            return false;
        } else {
            BlockState blockstate = level.getBlockState(pos);
            Block block = blockstate.getBlock();
            Material material = blockstate.getMaterial();
            boolean flag = blockstate.canBeReplaced(this.fluid);
            boolean flag1 = blockstate.isAir() || flag || block instanceof LiquidBlockContainer && ((LiquidBlockContainer)block).canPlaceLiquid(level, pos, blockstate, this.fluid);
            if (!flag1) {
                return result != null && this.nEmptyContents(player, level, result.getBlockPos().relative(result.getDirection()), null);
            } else if (level.dimensionType().ultraWarm() && this.fluid.is(FluidTags.WATER)) {
                int i = pos.getX();
                int j = pos.getY();
                int k = pos.getZ();
                level.playSound(player, pos, SoundEvents.FIRE_EXTINGUISH, SoundSource.BLOCKS, 0.5F, 2.6F + (level.random.nextFloat() - level.random.nextFloat()) * 0.8F);

                for(int l = 0; l < 8; ++l) {
                    level.addParticle(ParticleTypes.LARGE_SMOKE, (double)i + Math.random(), (double)j + Math.random(), (double)k + Math.random(), 0.0D, 0.0D, 0.0D);
                }

                return true;
            } else if (block instanceof LiquidBlockContainer && ((LiquidBlockContainer)block).canPlaceLiquid(level,pos,blockstate,fluid)) {
                ((LiquidBlockContainer)block).placeLiquid(level, pos, blockstate, ((FlowingFluid)this.fluid).getSource(false));
                this.playEmptySound(player, level, pos);
                return true;
            } else {
                if (!level.isClientSide && flag && !material.isLiquid()) {
                    level.destroyBlock(pos, true);
                }

                if (!level.setBlock(pos, this.fluid.defaultFluidState().createLegacyBlock(), 11) && !blockstate.getFluidState().isSource()) {
                    return false;
                } else {
                    this.playEmptySound(player, level, pos);
                    return true;
                }
            }
        }
    }

    private boolean canBlockContainFluid(Level worldIn, BlockPos posIn, BlockState blockstate) {
        return blockstate.getBlock() instanceof LiquidBlockContainer && ((LiquidBlockContainer)blockstate.getBlock()).canPlaceLiquid(worldIn, posIn, blockstate, this.fluid);
    }
}
