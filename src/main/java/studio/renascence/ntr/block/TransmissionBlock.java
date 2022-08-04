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
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;
import studio.renascence.ntr.init.NTRItems;
import studio.renascence.ntr.util.PillarHelper;

public class TransmissionBlock extends Block {
    public static final BooleanProperty ACT = BooleanProperty.create("active_state");
    private final ResourceKey<Level> resourceKey;

    public TransmissionBlock(Properties properties, ResourceKey<Level> resourceKey) {
        super(properties);
        this.resourceKey = resourceKey;
        this.registerDefaultState(this.stateDefinition.any().setValue(ACT, Boolean.FALSE));
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(ACT);
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult result) {
        if (result.getDirection() == Direction.UP && player.getItemInHand(hand).getItem() == NTRItems.ENDER_BLAZE_FLINT_AND_STEEL.get()) {
            if (PillarHelper.find(level, pos)) {
                PillarHelper.clearTileItem(level, pos);
                return createFire(level, pos, player, hand);
            }
            return InteractionResult.FAIL;
        }
        return InteractionResult.PASS;
    }

    private InteractionResult createFire(Level level, BlockPos pos, Player player, InteractionHand hand) {
        level.playSound(player, pos.above(), SoundEvents.FLINTANDSTEEL_USE, SoundSource.BLOCKS, 1.0F, level.getRandom().nextFloat() * 0.4F + 0.8F);
        level.gameEvent(player, GameEvent.BLOCK_PLACE, pos.above());
        ItemStack itemstack = player.getItemInHand(hand);
        if (player instanceof ServerPlayer) {
            level.explode(null, pos.getX(), pos.getY()+0.2F, pos.getZ(), 2.5F, Explosion.BlockInteraction.NONE);
            CriteriaTriggers.PLACED_BLOCK.trigger((ServerPlayer)player, pos.above(), itemstack);
            itemstack.hurtAndBreak(1, player, (player1) -> player1.broadcastBreakEvent(hand));
        }
        level.setBlock(pos.above(), TransmissionFireBlock.MAP.get(this).defaultBlockState(), UPDATE_ALL);
        level.getBlockState(pos).setValue(ACT, true);
        level.setBlock(pos, defaultBlockState().setValue(ACT, Boolean.TRUE), UPDATE_ALL);
        return InteractionResult.sidedSuccess(level.isClientSide());
    }

    public ResourceKey<Level> getResourceKey() {
        return resourceKey;
    }
}
