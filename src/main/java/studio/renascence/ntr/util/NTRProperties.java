package studio.renascence.ntr.util;

import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;

public class NTRProperties {
    public static final BlockBehaviour.Properties SANGUINITE_BLOCK = BlockBehaviour.Properties.of().instrument(NoteBlockInstrument.BASEDRUM).mapColor(MapColor.COLOR_PURPLE).requiresCorrectToolForDrops().strength(3.0F, 6.0F).explosionResistance(9).sound(SoundType.NETHER_BRICKS);
    public static final BlockBehaviour.Properties ENDER_BLAZE_BLOCK = BlockBehaviour.Properties.of().instrument(NoteBlockInstrument.BASEDRUM).mapColor(MapColor.COLOR_PURPLE).requiresCorrectToolForDrops().explosionResistance(9).strength(3.0F, 9.0F);
}
