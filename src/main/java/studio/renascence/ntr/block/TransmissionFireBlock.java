package studio.renascence.ntr.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.BaseFireBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.Shapes;

import java.util.HashMap;
import java.util.Map;

public class TransmissionFireBlock extends BaseFireBlock {
    public static Map<Block, TransmissionFireBlock> MAP = new HashMap<>();
    private final Block baseBlock;

    public TransmissionFireBlock(Properties properties, Block block) {
        super(properties, 1.5F);
        baseBlock = block;
        MAP.put(block, this);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        BlockPos blockpos = context.getClickedPos().below();
        BlockState blockstate = context.getLevel().getBlockState(blockpos);
        return canSurviveOnBlock(blockstate) ? defaultBlockState() : Blocks.AIR.defaultBlockState();
    }

    @Override
    public void entityInside(BlockState state, Level level, BlockPos pos, Entity entity) {
        if (entity.isAlive() && entity instanceof LivingEntity) {
            ResourceKey<Level> key = level.dimension() == ((TransmissionBlock) baseBlock).getResourceKey() ? Level.OVERWORLD : ((TransmissionBlock) baseBlock).getResourceKey();
            if (entity instanceof Player) {
                if (canChangeDimension((LivingEntity) entity) || ((Player) entity).isCreative()) {
                    changeDimension(state, level, pos, entity, key);
                }
            }else {
                if (canChangeDimension((LivingEntity) entity)) {
                    changeDimension(state, level, pos, entity, key);
                }
            }
        }
        super.entityInside(state, level, pos, entity);
    }

    public BlockState updateShape(BlockState state, Direction direction, BlockState blockState, LevelAccessor accessor, BlockPos pos, BlockPos pos1) {
        return this.canSurvive(state, accessor, pos) ? this.defaultBlockState() : Blocks.AIR.defaultBlockState();
    }

    private static void changeDimension(BlockState state, Level level, BlockPos pos, Entity entity, ResourceKey<Level> key) {
        if (level instanceof ServerLevel && !entity.isPassenger() && !entity.isVehicle() && entity.canChangeDimensions() && Shapes.joinIsNotEmpty(Shapes.create(entity.getBoundingBox().move(-entity.getX(), -entity.getY(), -entity.getZ())), state.getShape(level, pos), BooleanOp.AND)) {
            ServerLevel serverlevel = ((ServerLevel) level).getServer().getLevel(key);
            if (serverlevel == null) {
                return;
            }
            entity.changeDimension(serverlevel);
        }
    }

    private static boolean canChangeDimension(LivingEntity entity) {
        return entity.getHealth() <= entity.getMaxHealth() * 0.2;
    }

    @Override
    protected boolean canBurn(BlockState state) {
        return true;
    }

    public boolean canSurvive(BlockState state, LevelReader reader, BlockPos pos) {
        return canSurviveOnBlock(reader.getBlockState(pos.below()));
    }

    public boolean canSurviveOnBlock(BlockState state) {
        return state.is(baseBlock);
    }

}
