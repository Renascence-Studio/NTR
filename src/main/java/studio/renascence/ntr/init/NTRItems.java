package studio.renascence.ntr.init;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import studio.renascence.ntr.NTR;
import studio.renascence.ntr.util.Tabs;

public class NTRItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, NTR.MODID);

    public static final RegistryObject<Item> SANGUINITE_BRICKS = ITEMS.register("sanguinite_bricks",
            () -> new BlockItem(NTRBlocks.SANGUINITE_BRICKS.get(), itemPro()));
    public static final RegistryObject<Item> CHISELED_SANGUINITE_BRICKS = ITEMS.register("chiseled_sanguinite_bricks",
            () -> new BlockItem(NTRBlocks.CHISELED_SANGUINITE_BRICKS.get(), itemPro()));
    public static final RegistryObject<Item> CRACKED_SANGUINITE_BRICKS = ITEMS.register("cracked_sanguinite_bricks",
            () -> new BlockItem(NTRBlocks.CRACKED_SANGUINITE_BRICKS.get(), itemPro()));

    public static final RegistryObject<Item> SANGUINITE_BRICK_STAIRS = ITEMS.register("sanguinite_brick_stairs",
            () -> new BlockItem(NTRBlocks.SANGUINITE_BRICK_STAIRS.get(), itemPro()));
    public static final RegistryObject<Item> SANGUINITE_BRICK_SLAB = ITEMS.register("sanguinite_brick_slab",
            () -> new BlockItem(NTRBlocks.SANGUINITE_BRICK_SLAB.get(), itemPro()));
    public static final RegistryObject<Item> SANGUINITE_BRICK_FENCE = ITEMS.register("sanguinite_brick_fence",
            () -> new BlockItem(NTRBlocks.SANGUINITE_BRICK_FENCE.get(), itemPro()));

    public static Item.Properties itemPro() {
        return new Item.Properties().tab(Tabs.MAIN).fireResistant();
    }
}
