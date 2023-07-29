package studio.renascence.ntr.impl.item;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import studio.renascence.ntr.NTRConfig;

public class TransmissiteArmor extends ArmorItem {
    public TransmissiteArmor(ArmorMaterial p_40386_, Type p_266831_, Properties p_40388_) {
        super(p_40386_, p_266831_, p_40388_);
    }

    @Override
    public boolean canBeHurtBy(DamageSource damageSource) {
        if (damageSource.getEntity() != null && damageSource.getSourcePosition() != null) {
            return damageSource.getEntity().position().distanceTo(damageSource.getSourcePosition()) < NTRConfig.TRANSMISSITE_ARMOR_PROTECTION_DISTANCE.get();
        }
        return super.canBeHurtBy(damageSource);
    }

    @Override
    public void onArmorTick(ItemStack stack, Level level, Player player) {
        super.onArmorTick(stack, level, player);
        if (player.level().isClientSide && isFullyEquipped(player)) {
            for (int i = 0; i < 2; ++i) {
                player.level().addParticle(ParticleTypes.PORTAL, player.getRandomX(0.5D), player.getRandomY() - 0.25D, player.getRandomZ(0.5D), (player.getRandom().nextDouble() - 0.5D) * 2.0D, -player.getRandom().nextDouble(), (player.getRandom().nextDouble() - 0.5D) * 2.0D);
            }
        }
    }

    public boolean isFullyEquipped(LivingEntity player) {
        boolean flag1 = player.getItemBySlot(EquipmentSlot.HEAD).getItem() instanceof TransmissiteArmor;
        boolean flag2 = player.getItemBySlot(EquipmentSlot.CHEST).getItem() instanceof TransmissiteArmor;
        boolean flag3 = player.getItemBySlot(EquipmentSlot.LEGS).getItem() instanceof TransmissiteArmor;
        boolean flag4 = player.getItemBySlot(EquipmentSlot.FEET).getItem() instanceof TransmissiteArmor;
        return flag1 && flag4 && flag3 && flag2;
    }
}
