package studio.renascence.ntr.init;

import net.minecraft.world.level.block.*;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import studio.renascence.ntr.NTR;
import studio.renascence.ntr.block.NTRProperties;

public class NTRBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, NTR.MODID);

    public static final RegistryObject<Block> SANGUINITE_BRICKS = BLOCKS.register("sanguinite_bricks",
            () -> new Block(NTRProperties.SANGUINITE_BLOCK));
    public static final RegistryObject<Block> CHISELED_SANGUINITE_BRICKS = BLOCKS.register("chiseled_sanguinite_bricks",
            () -> new Block(NTRProperties.SANGUINITE_BLOCK));
    public static final RegistryObject<Block> CRACKED_SANGUINITE_BRICKS = BLOCKS.register("cracked_sanguinite_bricks",
            () -> new MagmaBlock(NTRProperties.SANGUINITE_BLOCK));

    public static final RegistryObject<Block> SANGUINITE_BRICK_STAIRS = BLOCKS.register("sanguinite_brick_stairs",
            () -> new StairBlock(() -> SANGUINITE_BRICKS.get().defaultBlockState(), NTRProperties.SANGUINITE_BLOCK));
    public static final RegistryObject<Block> SANGUINITE_BRICK_SLAB = BLOCKS.register("sanguinite_brick_slab",
            () -> new SlabBlock(NTRProperties.SANGUINITE_BLOCK));
    public static final RegistryObject<Block> SANGUINITE_BRICK_FENCE = BLOCKS.register("sanguinite_brick_fence",
            () -> new FenceBlock(NTRProperties.SANGUINITE_BLOCK));
}
