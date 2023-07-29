package studio.renascence.ntr.impl.common.block;

import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.block.BaseFireBlock;
import net.minecraft.world.level.block.state.BlockState;

public class DecoTransmissionFireBlock extends BaseFireBlock {
    public DecoTransmissionFireBlock(Properties properties) {
        super(properties, 2.0F);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return defaultBlockState();
    }

    @Override
    protected boolean canBurn(BlockState state) {
        return true;
    }
}
