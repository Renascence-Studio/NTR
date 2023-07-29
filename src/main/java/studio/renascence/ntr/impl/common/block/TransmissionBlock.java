package studio.renascence.ntr.impl.common.block;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
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
        if (result.getDirection() == Direction.UP && player.getItemInHand(hand).getItem() == NTRItems.ENDER_BLAZE_FLINT_AND_TRANSMISSITE.get()) {
            PillarHelper.PillarResult flag = PillarHelper.find(level, pos);
            if (flag.flag()) {
                PillarHelper.clearTileItem(level, pos, flag.level());
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
            level.explode(null, pos.getX(), pos.getY()+0.2F, pos.getZ(), 2.5F, Level.ExplosionInteraction.NONE);
            level.explode(null, pos.getX() + 3, pos.getY()+0.2F, pos.getZ() + 3, 2.5F, Level.ExplosionInteraction.NONE);
            level.explode(null, pos.getX() + 3, pos.getY()+0.2F, pos.getZ() - 3, 2.5F, Level.ExplosionInteraction.NONE);
            level.explode(null, pos.getX() - 3, pos.getY()+0.2F, pos.getZ() + 3, 2.5F, Level.ExplosionInteraction.NONE);
            level.explode(null, pos.getX() - 3, pos.getY()+0.2F, pos.getZ() - 3, 2.5F, Level.ExplosionInteraction.NONE);
            CriteriaTriggers.PLACED_BLOCK.trigger((ServerPlayer)player, pos.above(), itemstack);
            itemstack.hurtAndBreak(1, player, (player1) -> player1.broadcastBreakEvent(hand));
        }
        level.setBlock(pos.above(), TransmissionFireBlock.MAP.get(this).defaultBlockState(), UPDATE_ALL);
        level.getBlockState(pos).setValue(ACT, true);
        level.setBlock(pos, defaultBlockState().setValue(ACT, Boolean.TRUE), UPDATE_ALL);
        return InteractionResult.sidedSuccess(level.isClientSide());
    }

    @Override
    public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource randomSource) {
        if (PillarHelper.find(level, pos).flag()) {
            double tick = System.currentTimeMillis() / 800.0D;
            Vec3 vec3 = pos.getCenter().yRot((float) (tick % (2 * Math.PI)));
            level.addParticle(ParticleTypes.PORTAL, vec3.x, vec3.y, vec3.z, 0, 0.2, 0);
        }
    }

    public ResourceKey<Level> getResourceKey() {
        return resourceKey;
    }
}
