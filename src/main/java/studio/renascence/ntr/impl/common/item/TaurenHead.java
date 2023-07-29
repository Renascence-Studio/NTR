package studio.renascence.ntr.impl.common.item;


import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

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

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> list, TooltipFlag flag) {
        list.add(Component.translatable("item.ntr.tauren_head.tip_1").withStyle(ChatFormatting.GOLD));
        list.add(Component.translatable("item.ntr.tauren_head.tip_2").withStyle(ChatFormatting.GOLD));
    }
}
