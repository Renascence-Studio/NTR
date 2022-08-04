package studio.renascence.ntr.event;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import studio.renascence.ntr.init.NTRItems;

import java.util.Random;

@Mod.EventBusSubscriber
public class DropNTRHeadEvent {
    @SubscribeEvent
    public static void dead(LivingDeathEvent event) {
        Level level = event.getEntity().getLevel();
        Entity entity = event.getEntity();
        if (!level.isClientSide) {
            if (entity.getType() == EntityType.MOOSHROOM) {
                Random random = new Random();
                if (random.nextFloat() < 0.001F)
                    level.addFreshEntity(new ItemEntity(level, entity.getX(), entity.getY(), entity.getZ(), NTRItems.REAL_NTR_HEAD.get().getDefaultInstance()));
            }
        }
    }
}
