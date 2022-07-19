package studio.renascence.ntr.block;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.FlintAndSteelItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;

public class TransmissionBlock extends Block {
    private final ResourceKey<Level> resourceKey;

    public TransmissionBlock(Properties properties, ResourceKey<Level> resourceKey) {
        super(properties);
        this.resourceKey = resourceKey;
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult result) {
        if (result.getDirection() == Direction.UP && player.getItemInHand(hand).getItem() instanceof FlintAndSteelItem) {
            level.playSound(player, pos.above(), SoundEvents.FLINTANDSTEEL_USE, SoundSource.BLOCKS, 1.0F, level.getRandom().nextFloat() * 0.4F + 0.8F);
            level.setBlock(pos.above(), TransmissionFireBlock.MAP.get(this).defaultBlockState(), 11);
            level.gameEvent(player, GameEvent.BLOCK_PLACE, pos.above());
            ItemStack itemstack = player.getItemInHand(hand);
            if (player instanceof ServerPlayer) {
                CriteriaTriggers.PLACED_BLOCK.trigger((ServerPlayer)player, pos.above(), itemstack);
                itemstack.hurtAndBreak(1, player, (player1) -> player1.broadcastBreakEvent(hand));
            }

            return InteractionResult.sidedSuccess(level.isClientSide());
        }
        return InteractionResult.PASS;
    }

    public ResourceKey<Level> getResourceKey() {
        return resourceKey;
    }
}
