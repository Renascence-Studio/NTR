package studio.renascence.ntr.init;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import studio.renascence.ntr.NTRMod;
import studio.renascence.ntr.impl.tile.PillarTile;

public class NTRBlockEntityTypes {
    public static final DeferredRegister<BlockEntityType<?>> TILES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, NTRMod.MODID);

    public static final RegistryObject<BlockEntityType<PillarTile>> SANGUINITE_PILLAR =
            TILES.register(
                    "sanguinite_pillar",
                    () -> BlockEntityType.Builder.of(PillarTile::new, new Block[] {NTRBlocks.SANGUINITE_PILLAR.get()}).build(null)
            );

    public static void register(IEventBus bus) {
        TILES.register(bus);
    }
}
