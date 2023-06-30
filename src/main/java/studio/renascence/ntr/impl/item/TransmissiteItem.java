package studio.renascence.ntr.impl.item;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.ForgeEventFactory;
import net.minecraftforge.event.entity.EntityTeleportEvent;

import static studio.renascence.ntr.NTRConfig.TRANSMISSITE_ITEM_CHANCE;

public interface TransmissiteItem {
    default void attack(ItemStack stack, LivingEntity target, LivingEntity source) {
        if (source.getRandom().nextDouble() < TRANSMISSITE_ITEM_CHANCE.get()) {
            if (source.isAlive()) {
                double d0 = source.getX() + (source.getRandom().nextDouble() - 0.5D) * 24.0D;
                double d1 = source.getY() + (double)(source.getRandom().nextInt(32) - 16);
                double d2 = source.getZ() + (source.getRandom().nextDouble() - 0.5D) * 24.0D;
                this.teleport(source, d0, d1, d2);
                stack.hurtAndBreak(10, source, (p_186374_) -> p_186374_.broadcastBreakEvent(EquipmentSlot.MAINHAND));
            }
        }
    }

    default void teleport(LivingEntity entity, double d0, double d1, double d2) {
        BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos(d0, d1, d2);

        while(blockpos$mutableblockpos.getY() > entity.level().getMinBuildHeight() && !entity.level().getBlockState(blockpos$mutableblockpos).blocksMotion()) {
            blockpos$mutableblockpos.move(Direction.DOWN);
        }

        BlockState blockstate = entity.level().getBlockState(blockpos$mutableblockpos);
        boolean flag = blockstate.blocksMotion();
        boolean flag1 = blockstate.getFluidState().is(FluidTags.WATER);
        if (flag && !flag1) {
            EntityTeleportEvent event = ForgeEventFactory.onEnderTeleport(entity, d0, d1, d2);
            if (event.isCanceled())
                return;
            Vec3 vec3 = entity.position();
            boolean flag2 = entity.randomTeleport(event.getTargetX(), event.getTargetY(), event.getTargetZ(), true);
            if (flag2) {
                entity.level().gameEvent(GameEvent.TELEPORT, vec3, GameEvent.Context.of(entity));
                if (!entity.isSilent()) {
                    entity.level().playSound(null, entity.xo, entity.yo, entity.zo, SoundEvents.ENDERMAN_TELEPORT, entity.getSoundSource(), 1.0F, 1.0F);
                    entity.playSound(SoundEvents.ENDERMAN_TELEPORT, 1.0F, 1.0F);
                }
            }
        }
    }
}
