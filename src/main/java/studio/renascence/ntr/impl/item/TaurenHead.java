package studio.renascence.ntr.impl.item;


import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class TaurenHead extends Item {

    public TaurenHead(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        if (player.getItemBySlot(EquipmentSlot.HEAD).isEmpty()) {
            player.setItemSlot(EquipmentSlot.HEAD, player.getItemInHand(hand).split(1));
            if (!player.isSilent()) {
                level.playSound(null, player.xo, player.yo, player.zo, SoundEvents.COW_AMBIENT, player.getSoundSource(), 1.0F, 1.0F);
                player.playSound(SoundEvents.COW_AMBIENT, 1.0F, 1.0F);
            }
            return InteractionResultHolder.success(player.getItemInHand(hand));
        }
        return super.use(level, player, hand);
    }

    @Override
    public void onArmorTick(ItemStack stack, Level level, Player player) {
        super.onArmorTick(stack, level, player);
        if (!player.hasEffect(MobEffects.DAMAGE_BOOST))
            player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 100, 1));
    }
}
