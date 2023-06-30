package studio.renascence.ntr.init;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraftforge.registries.RegistryObject;
import studio.renascence.ntr.NTRUtil;

import static studio.renascence.ntr.NTRUtil.CREATIVE_MODE_TABS;

public class NTRTabs {
    public static final RegistryObject<CreativeModeTab> MAIN = CREATIVE_MODE_TABS.register("ntr", () -> CreativeModeTab.builder()
            .title(Component.translatable("itemGroup.ntr.main"))
            .icon(() -> NTRItems.DECO_ENDER_TRANSMISSION_FIRE.get().getDefaultInstance())
            .displayItems((parameters, output) -> NTRUtil.ITEMS.getEntries().forEach(item -> output.accept(item.get()))).build());

    public static void load() {}
}
