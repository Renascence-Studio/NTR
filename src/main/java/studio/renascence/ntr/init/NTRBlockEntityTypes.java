package studio.renascence.ntr.init;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import studio.renascence.ntr.NTRMod;
import studio.renascence.ntr.impl.tile.PillarTile;

import java.util.Set;
import java.util.function.Supplier;

public class NTRBlockEntityTypes {
    public static final DeferredRegister<BlockEntityType<?>> TILES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, NTRMod.MODID);

    public static final RegistryObject<BlockEntityType<PillarTile>> SANGUINITE_PILLAR = TILES.register("sanguinite_pillar",
            create(PillarTile::new, Set.copyOf(NTRBlocks.PILLAR)));

    @SuppressWarnings("ConstantConditions")
    private static <T extends BlockEntity> Supplier<BlockEntityType<T>> create(BlockEntityType.BlockEntitySupplier<T> create, Set<Block> set) {
        return () -> new BlockEntityType<>(create, set, null);
    }

    public static void register(IEventBus bus) {
        TILES.register(bus);
    }
}
