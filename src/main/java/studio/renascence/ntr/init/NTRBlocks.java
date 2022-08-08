package studio.renascence.ntr.init;

import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import studio.renascence.ntr.NTR;
import studio.renascence.ntr.block.*;
import studio.renascence.ntr.util.NTRProperties;

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
    public static final RegistryObject<Block> DECO_ENDER_TRANSMISSION_FIRE = BLOCKS.register("deco_ender_transmission_fire",
            () -> new DecoTransmissionFireBlock(BlockBehaviour.Properties.copy(ENDER_TRANSMIT_FIRE.get())));

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

    public static final RegistryObject<Block> TRANSMISSION_ORE = BLOCKS.register("transmission_ore",
            () -> new OreBlock(BlockBehaviour.Properties.copy(Blocks.COAL_ORE), UniformInt.of(0, 2)));

    public static final RegistryObject<Block> TRANSMISSION_DUST_BLOCK = BLOCKS.register("transmission_dust_block",
            () -> new GlitterableBlock(BlockBehaviour.Properties.of(Material.SAND, DyeColor.PURPLE).strength(0.5F).sound(SoundType.SAND)));
    public static final RegistryObject<Block> TRANSMISSION_INGOT_BLOCK = BLOCKS.register("transmission_ingot_block",
            () -> new GlitterableBlock(BlockBehaviour.Properties.of(Material.METAL, DyeColor.PURPLE).requiresCorrectToolForDrops().strength(5.0F, 6.0F).sound(SoundType.METAL)));
}
