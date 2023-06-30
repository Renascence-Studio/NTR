package studio.renascence.ntr;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import studio.renascence.ntr.init.NTRBlocks;
import studio.renascence.ntr.init.NTRItems;
import studio.renascence.ntr.init.NTRTabs;

public class NTRUtil {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, NTRMod.MODID);
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, NTRMod.MODID);
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, NTRMod.MODID);

    public static void load() {
        NTRItems.load();
        NTRBlocks.load();
        NTRTabs.load();
    }

    public static void register(IEventBus bus) {
        ITEMS.register(bus);
        BLOCKS.register(bus);
        CREATIVE_MODE_TABS.register(bus);
    }

    public static ResourceLocation rl(String path) {
        return new ResourceLocation(NTRMod.MODID, path);
    }
}
