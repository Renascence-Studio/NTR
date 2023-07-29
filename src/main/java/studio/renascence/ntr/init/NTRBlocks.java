package studio.renascence.ntr.init;

import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import studio.renascence.ntr.NTRMod;
import studio.renascence.ntr.impl.block.*;
import studio.renascence.ntr.util.NTRProperties;

import java.util.Stack;

public class NTRBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, NTRMod.MODID);
    public static final Stack<Block> PILLAR = new Stack<>();

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
    public static final RegistryObject<Block> ENDER_BLAZE_BRICK_FENCE = BLOCKS.register("ender_blaze_brick_fence",
            () -> new FenceBlock(NTRProperties.ENDER_BLAZE_BLOCK));

    public static final RegistryObject<Block> TRANSMISSITE_ORE = BLOCKS.register("transmissite_ore",
            () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.COAL_ORE), UniformInt.of(0, 2)));

    public static final RegistryObject<Block> TRANSMISSITE_DUST_BLOCK = BLOCKS.register("transmissite_dust_block",
            () -> new GlitterableBlock(BlockBehaviour.Properties.of().instrument(NoteBlockInstrument.SNARE).mapColor(DyeColor.PURPLE).strength(0.5F).sound(SoundType.SAND)));
    public static final RegistryObject<Block> TRANSMISSITE_INGOT_BLOCK = BLOCKS.register("transmissite_ingot_block",
            () -> new GlitterableBlock(BlockBehaviour.Properties.of().instrument(NoteBlockInstrument.IRON_XYLOPHONE).requiresCorrectToolForDrops().strength(5.0F, 6.0F).sound(SoundType.METAL)));
    public static final RegistryObject<Block> SANGUINITE_PILLAR = BLOCKS.register("sanguinite_pillar",
            () -> {
                PILLAR.add(new PillarBlock(NTRProperties.SANGUINITE_BLOCK));
                return PILLAR.peek();
            });

    public static void register(IEventBus bus) {
        BLOCKS.register(bus);
    }

}
