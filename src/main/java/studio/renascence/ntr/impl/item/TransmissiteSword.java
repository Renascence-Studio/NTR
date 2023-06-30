package studio.renascence.ntr.impl.item;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;

public class TransmissiteSword extends SwordItem implements TransmissiteItem {
    public TransmissiteSword(Tier p_43269_, int p_43270_, float p_43271_, Properties p_43272_) {
        super(p_43269_, p_43270_, p_43271_, p_43272_);
    }

    @Override
    public boolean hurtEnemy(ItemStack p_43278_, LivingEntity p_43279_, LivingEntity p_43280_) {
        this.attack(p_43278_, p_43279_, p_43280_);
        return super.hurtEnemy(p_43278_, p_43279_, p_43280_);
    }

    @Override
    public boolean isFoil(ItemStack stack) {
        return true;
    }
}
