package studio.renascence.ntr.block;

import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;

public class NTRProperties {
    public static final BlockBehaviour.Properties SANGUINITE_BLOCK = BlockBehaviour.Properties.of(Material.STONE, MaterialColor.COLOR_RED).requiresCorrectToolForDrops().strength(2.5F, 5.0F).sound(SoundType.NETHER_BRICKS);
}
