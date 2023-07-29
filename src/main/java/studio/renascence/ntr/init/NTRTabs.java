package studio.renascence.ntr.init;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import studio.renascence.ntr.NTRMod;

public class NTRTabs {
    public static final DeferredRegister<CreativeModeTab> TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, NTRMod.MODID);

    public static final RegistryObject<CreativeModeTab> MAIN = TABS.register("ntr", () -> CreativeModeTab.builder()
            .title(Component.translatable("itemGroup.ntr.main"))
            .icon(() -> NTRItems.DECO_ENDER_TRANSMISSION_FIRE.get().getDefaultInstance())
            .displayItems((parameters, output) -> NTRItems.ITEMS.getEntries().forEach(item -> output.accept(item.get())))
            .backgroundSuffix("ntr.png")
            .hideTitle()
            .build());

    public static void register(IEventBus bus) {
        TABS.register(bus);
    }
}
