package studio.renascence.ntr.util;

import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import studio.renascence.ntr.impl.common.tile.PillarTile;
import studio.renascence.ntr.init.NTRBlocks;

import java.util.Objects;

public class PillarHelper {
    public record PillarResult(boolean flag, int level) {}

    public static PillarResult find(Level level, BlockPos pos) {
        for (int i = 0; i < 3; i++) {
            BlockPos pos1 = pos.east(2).north(2).above(i);
            BlockPos pos2 = pos.east(2).south(2).above(i);
            BlockPos pos3 = pos.west(2).north(2).above(i);
            BlockPos pos4 = pos.west(2).south(2).above(i);
            boolean b1 = matchBlock(level, pos1) && matchTileItem(level, pos1);
            boolean b2 = matchBlock(level, pos2) && matchTileItem(level, pos2);
            boolean b3 = matchBlock(level, pos3) && matchTileItem(level, pos3);
            boolean b4 = matchBlock(level, pos4) && matchTileItem(level, pos4);
            if ((b1 && b2 && b3 && b4)) {
                return new PillarResult(true, i);
            }
        }
        return new PillarResult(false, 0);
    }

    private static boolean matchBlock(Level level, BlockPos pos) {
        return level.getBlockState(pos).is(NTRBlocks.SANGUINITE_PILLAR.get());
    }

    private static boolean matchTileItem(Level level, BlockPos pos) {
        BlockEntity tile = level.getBlockEntity(pos);
        if (tile instanceof PillarTile pillarTile) {
            return pillarTile.getItem(0).is(Items.ENDER_EYE);
        }
        return false;
    }

    public static void clearTileItem(Level level, BlockPos pos, int i) {
        BlockPos pos1 = pos.east(2).north(2).above(i);
        BlockPos pos2 = pos.east(2).south(2).above(i);
        BlockPos pos3 = pos.west(2).north(2).above(i);
        BlockPos pos4 = pos.west(2).south(2).above(i);
        BlockPos[] poses = new BlockPos[]{pos1, pos2, pos3, pos4};
        for (BlockPos posV : poses) {
            if (matchTileItem(level, posV)) {
                if (level.getBlockEntity(posV) instanceof PillarTile && level.getBlockEntity(posV) != null)
                    ((PillarTile) Objects.requireNonNull(level.getBlockEntity(posV))).setItem(0, ItemStack.EMPTY);
            }
        }
    }
}
