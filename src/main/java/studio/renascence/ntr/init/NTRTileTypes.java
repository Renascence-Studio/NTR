package studio.renascence.ntr.init;

import com.mojang.datafixers.DSL;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import studio.renascence.ntr.NTR;
import studio.renascence.ntr.tile.PillarTile;

public class NTRTileTypes {
    public static final DeferredRegister<BlockEntityType<?>> TYPES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITIES, NTR.MODID);
    public static final RegistryObject<BlockEntityType<PillarTile>> PILLAR_TILE = TYPES.register("ntr_pillar",
            () -> BlockEntityType.Builder.of(PillarTile::new, NTRBlocks.SANGUINITE_PILLAR.get()).build(DSL.remainderType()));;
}
