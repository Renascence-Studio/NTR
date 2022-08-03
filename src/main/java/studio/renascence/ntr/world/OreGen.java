package studio.renascence.ntr.world;

import net.minecraft.core.Holder;
import net.minecraft.data.worldgen.features.OreFeatures;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import studio.renascence.ntr.init.NTRBlocks;

import java.util.List;

@Mod.EventBusSubscriber
public class OreGen {
    public static Holder<PlacedFeature> ORE_TRANSMISSION;

    public static void registerOreFeatures() {
        List<OreConfiguration.TargetBlockState> transmissionOre = List.of(OreConfiguration.target(OreFeatures.NETHERRACK, NTRBlocks.TRANSMISSION_ORE.get().defaultBlockState()));

        ORE_TRANSMISSION = registerPlacedOreFeature("transmission_ore", new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(transmissionOre, 14)), PlacementUtils.RANGE_10_10);
    }

    @SubscribeEvent
    public static void onBiomeLoading(BiomeLoadingEvent event) {
        if (event.getCategory() == Biome.BiomeCategory.NETHER)
            event.getGeneration().addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, ORE_TRANSMISSION);
    }

    private static <C extends FeatureConfiguration, F extends Feature<C>> Holder<PlacedFeature> registerPlacedOreFeature(String regName, ConfiguredFeature<C, F> feature, PlacementModifier ... placementModifiers) {
        return PlacementUtils.register(regName, Holder.direct(feature), placementModifiers);
    }
}
