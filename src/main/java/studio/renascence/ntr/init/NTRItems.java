package studio.renascence.ntr.init;

import net.minecraft.world.item.*;
import net.minecraftforge.registries.RegistryObject;
import studio.renascence.ntr.impl.item.TransmissiteArmor;
import studio.renascence.ntr.impl.item.TransmissiteAxe;
import studio.renascence.ntr.impl.item.TransmissiteSword;
import studio.renascence.ntr.util.NTRArmorMaterials;
import studio.renascence.ntr.util.NTRItemTiers;

import static studio.renascence.ntr.NTRUtil.ITEMS;

public class NTRItems {
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

    public static final RegistryObject<Item> ENDER_TRANSMIT_BLOCK = ITEMS.register("ender_transmission_block",
            () -> new BlockItem(NTRBlocks.ENDER_TRANSMIT_BLOCK.get(), itemPro()));
    public static final RegistryObject<Item> ENDER_TRANSMIT_FIRE = ITEMS.register("ender_transmission_fire",
            () -> new BlockItem(NTRBlocks.ENDER_TRANSMIT_FIRE.get(), itemPro()));
    public static final RegistryObject<Item> DECO_ENDER_TRANSMISSION_FIRE = ITEMS.register("deco_ender_transmission_fire",
            () -> new BlockItem(NTRBlocks.DECO_ENDER_TRANSMISSION_FIRE.get(), itemPro()));

    public static final RegistryObject<Item> ENDER_BLAZE_BRICKS = ITEMS.register("ender_blaze_bricks",
            () -> new BlockItem(NTRBlocks.ENDER_BLAZE_BRICKS.get(), itemPro()));
    public static final RegistryObject<Item> CHISELED_ENDER_BLAZE_BRICKS = ITEMS.register("chiseled_ender_blaze_bricks",
            () -> new BlockItem(NTRBlocks.CHISELED_ENDER_BLAZE_BRICKS.get(), itemPro()));
    public static final RegistryObject<Item> ENDER_BLAZE_PILLAR = ITEMS.register("ender_blaze_pillar",
            () -> new BlockItem(NTRBlocks.ENDER_BLAZE_PILLAR.get(), itemPro()));

    public static final RegistryObject<Item> ENDER_BLAZE_BRICK_STAIRS = ITEMS.register("ender_blaze_brick_stairs",
            () -> new BlockItem(NTRBlocks.ENDER_BLAZE_BRICK_STAIRS.get(), itemPro()));
    public static final RegistryObject<Item> ENDER_BLAZE_BRICK_SLAB = ITEMS.register("ender_blaze_brick_slab",
            () -> new BlockItem(NTRBlocks.ENDER_BLAZE_BRICK_SLAB.get(), itemPro()));
    public static final RegistryObject<Item> ENDER_BLAZE_FLINT_AND_TRANSMISSITE = ITEMS.register("ender_blaze_flint_and_transmissite",
            () -> new FlintAndSteelItem(itemProX().durability(1140)));

    public static final RegistryObject<Item> ENDER_GEMSTONE = ITEMS.register("ender_gemstone",
            () -> new Item(itemProX()));
    public static final RegistryObject<Item> TRANSMISSITE_DUST = ITEMS.register("transmissite_dust",
            () -> new Item(itemProX()));
    public static final RegistryObject<Item> TRANSMISSITE_INGOT = ITEMS.register("transmissite_ingot",
            () -> new Item(itemProX()));

    public static final RegistryObject<Item> TRANSMISSITE_STICK = ITEMS.register("transmissite_stick",
            () -> new Item(itemProX()));
    public static final RegistryObject<Item> TRANSMISSITE_SWORD = ITEMS.register("transmissite_sword",
            () -> new TransmissiteSword(NTRItemTiers.TRANSMISSITE, 3, -2.4F, itemProX()));
    public static final RegistryObject<Item> TRANSMISSITE_SHOVEL = ITEMS.register("transmissite_shovel",
            () -> new ShovelItem(NTRItemTiers.TRANSMISSITE, 1.5F, -3.0F, itemProX()));
    public static final RegistryObject<Item> TRANSMISSITE_PICKAXE = ITEMS.register("transmissite_pickaxe",
            () -> new PickaxeItem(NTRItemTiers.TRANSMISSITE, 1, -2.8F, itemProX()));
    public static final RegistryObject<Item> TRANSMISSITE_AXE = ITEMS.register("transmissite_axe",
            () -> new TransmissiteAxe(NTRItemTiers.TRANSMISSITE, 6.0F, -3.1F, itemProX()));
    public static final RegistryObject<Item> TRANSMISSITE_HOE = ITEMS.register("transmissite_hoe",
            () -> new HoeItem(NTRItemTiers.TRANSMISSITE, -2, -1.0F, itemProX()));
    public static final RegistryObject<Item> TRANSMISSITE_SHEARS = ITEMS.register("transmissite_shears",
            () -> new ShearsItem(itemProX().durability(514)));

    public static final RegistryObject<Item> TRANSMISSITE_HELMET = ITEMS.register("transmissite_helmet",
            () -> new TransmissiteArmor(NTRArmorMaterials.TRANSMISSITE, ArmorItem.Type.HELMET, itemProX()));
    public static final RegistryObject<Item> TRANSMISSITE_CHESTPLATE = ITEMS.register("transmissite_chestplate",
            () -> new TransmissiteArmor(NTRArmorMaterials.TRANSMISSITE, ArmorItem.Type.CHESTPLATE, itemProX()));
    public static final RegistryObject<Item> TRANSMISSITE_LEGGINGS = ITEMS.register("transmissite_leggings",
            () -> new TransmissiteArmor(NTRArmorMaterials.TRANSMISSITE, ArmorItem.Type.LEGGINGS, itemProX()));
    public static final RegistryObject<Item> TRANSMISSITE_BOOTS = ITEMS.register("transmissite_boots",
            () -> new TransmissiteArmor(NTRArmorMaterials.TRANSMISSITE, ArmorItem.Type.BOOTS, itemProX()));

    public static final RegistryObject<Item> TRANSMISSITE_ORE = ITEMS.register("transmissite_ore",
            () -> new BlockItem(NTRBlocks.TRANSMISSITE_ORE.get(), itemPro()));
    public static final RegistryObject<Item> TRANSMISSITE_DUST_BLOCK = ITEMS.register("transmissite_dust_block",
            () -> new BlockItem(NTRBlocks.TRANSMISSITE_DUST_BLOCK.get(), itemPro()));
    public static final RegistryObject<Item> TRANSMISSITE_INGOT_BLOCK = ITEMS.register("transmissite_ingot_block",
            () -> new BlockItem(NTRBlocks.TRANSMISSITE_INGOT_BLOCK.get(), itemPro()));

    public static Item.Properties itemPro() {
        return new Item.Properties();
    }
    public static Item.Properties itemProX() {
        return new Item.Properties().fireResistant();
    }

    public static void load() {}
}
