package studio.renascence.ntr.impl.item;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;

public class TransmissiteAxe extends AxeItem implements TransmissiteItem {
    public TransmissiteAxe(Tier p_40521_, float p_40522_, float p_40523_, Properties p_40524_) {
        super(p_40521_, p_40522_, p_40523_, p_40524_);
    }

    @Override
    public boolean hurtEnemy(ItemStack p_43278_, LivingEntity p_43279_, LivingEntity p_43280_) {
        this.attack(p_43278_, p_43279_, p_43280_);
        return super.hurtEnemy(p_43278_, p_43279_, p_43280_);
    }
}
