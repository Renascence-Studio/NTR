package studio.renascence.ntr.init;

import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import studio.renascence.ntr.NTR;
import studio.renascence.ntr.util.NTRProperties;
import studio.renascence.ntr.block.PillarBlock;
import studio.renascence.ntr.block.TransmissionBlock;
import studio.renascence.ntr.block.TransmissionFireBlock;

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

    public static final RegistryObject<Block> SANGUINITE_PILLAR = BLOCKS.register("sanguinite_pillar",
            () -> new PillarBlock(NTRProperties.SANGUINITE_BLOCK));

    public static final RegistryObject<Block> ENDER_TRANSMIT_BLOCK = BLOCKS.register("ender_transmission_block",
            () -> new TransmissionBlock(BlockBehaviour.Properties.copy(Blocks.OBSIDIAN), Level.END));
    public static final RegistryObject<Block> ENDER_TRANSMIT_FIRE = BLOCKS.register("ender_transmission_fire",
            () -> new TransmissionFireBlock(BlockBehaviour.Properties.copy(Blocks.SOUL_FIRE).explosionResistance(50000F), ENDER_TRANSMIT_BLOCK.get()));

    public static final RegistryObject<Block> ENDER_BLAZE_BRICKS = BLOCKS.register("ender_blaze_bricks",
            () -> new Block(NTRProperties.ENDER_BLAZE_BLOCK));
    public static final RegistryObject<Block> CHISELED_ENDER_BLAZE_BRICKS = BLOCKS.register("chiseled_ender_blaze_bricks",
            () -> new Block(NTRProperties.ENDER_BLAZE_BLOCK));
    public static final RegistryObject<Block> ENDER_BLAZE_PILLAR = BLOCKS.register("ender_blaze_pillar",
            () -> new RotatedPillarBlock(NTRProperties.ENDER_BLAZE_BLOCK));

    public static final RegistryObject<Block> ENDER_BLAZE_BRICK_STAIRS = BLOCKS.register("ender_blaze_brick_stairs",
            () -> new StairBlock(() -> ENDER_BLAZE_BRICKS.get().defaultBlockState(), NTRProperties.ENDER_BLAZE_BLOCK));
    public static final RegistryObject<Block> ENDER_BLAZE_BRICK_SLAB = BLOCKS.register("ender_blaze_brick_slab",
            () -> new SlabBlock(NTRProperties.ENDER_BLAZE_BLOCK));
}
