package studio.renascence.ntr.impl.item;

import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;

public class TransmissiteArmor extends ArmorItem {
    public TransmissiteArmor(ArmorMaterial p_40386_, Type p_266831_, Properties p_40388_) {
        super(p_40386_, p_266831_, p_40388_);
    }

    @Override
    public boolean canBeHurtBy(DamageSource damageSource) {
        if (damageSource.getEntity() != null && damageSource.getDirectEntity() != null)
            return damageSource.getEntity().position().distanceTo(damageSource.getDirectEntity().position()) < 16f;
        return super.canBeHurtBy(damageSource);
    }
}
