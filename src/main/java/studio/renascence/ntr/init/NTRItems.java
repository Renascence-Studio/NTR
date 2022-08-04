package studio.renascence.ntr.init;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.*;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import studio.renascence.ntr.NTR;
import studio.renascence.ntr.item.NTRHeadItem;
import studio.renascence.ntr.item.TranBucketItem;
import studio.renascence.ntr.util.NTRArmorMaterials;
import studio.renascence.ntr.util.NTRItemTiers;
import studio.renascence.ntr.util.NTRTabs;

public class NTRItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, NTR.MODID);

    public static final RegistryObject<Item> REAL_NTR_HEAD = ITEMS.register("real_ntr_head",
            () -> new NTRHeadItem(itemPro().durability(5140)));

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

    public static final RegistryObject<Item> SANGUINITE_PILLAR = ITEMS.register("sanguinite_pillar",
            () -> new BlockItem(NTRBlocks.SANGUINITE_PILLAR.get(), itemPro()));

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
    public static final RegistryObject<Item> ENDER_BLAZE_FLINT_AND_STEEL = ITEMS.register("ender_blaze_flint_and_steel",
            () -> new FlintAndSteelItem(itemPro().durability(1140)));

    public static final RegistryObject<Item> ENDER_GEMSTONE = ITEMS.register("ender_gemstone",
            () -> new Item(itemProX()));
    public static final RegistryObject<Item> TRANSMISSION_DUST = ITEMS.register("transmission_dust",
            () -> new Item(itemProX()));
    public static final RegistryObject<Item> TRANSMISSION_INGOT = ITEMS.register("transmission_ingot",
            () -> new Item(itemPro()));

    public static final RegistryObject<Item> TRANSMISSION_STICK = ITEMS.register("transmission_stick",
            () -> new Item(itemPro()));
    public static final RegistryObject<Item> TRANSMISSION_SWORD = ITEMS.register("transmission_sword",
            () -> new SwordItem(NTRItemTiers.TRANSMISSION, 3, -2.4F, itemPro()));
    public static final RegistryObject<Item> TRANSMISSION_SHOVEL = ITEMS.register("transmission_shovel",
            () -> new ShovelItem(NTRItemTiers.TRANSMISSION, 1.5F, -3.0F, itemPro()));
    public static final RegistryObject<Item> TRANSMISSION_PICKAXE = ITEMS.register("transmission_pickaxe",
            () -> new PickaxeItem(NTRItemTiers.TRANSMISSION, 1, -2.8F, itemPro()));
    public static final RegistryObject<Item> TRANSMISSION_AXE = ITEMS.register("transmission_axe",
            () -> new AxeItem(NTRItemTiers.TRANSMISSION, 6.0F, -3.1F, itemPro()));
    public static final RegistryObject<Item> TRANSMISSION_HOE = ITEMS.register("transmission_hoe",
            () -> new HoeItem(NTRItemTiers.TRANSMISSION, -2, -1.0F, itemPro()));
    public static final RegistryObject<Item> TRANSMISSION_SHEARS = ITEMS.register("transmission_shears",
            () -> new ShearsItem(itemPro().durability(514)));

    public static final RegistryObject<Item> TRANSMISSION_HELMET = ITEMS.register("transmission_helmet",
            () -> new ArmorItem(NTRArmorMaterials.TRANSMISSION, EquipmentSlot.HEAD, itemPro()));
    public static final RegistryObject<Item> TRANSMISSION_CHESTPLATE = ITEMS.register("transmission_chestplate",
            () -> new ArmorItem(NTRArmorMaterials.TRANSMISSION, EquipmentSlot.CHEST, itemPro()));
    public static final RegistryObject<Item> TRANSMISSION_LEGGINGS = ITEMS.register("transmission_leggings",
            () -> new ArmorItem(NTRArmorMaterials.TRANSMISSION, EquipmentSlot.LEGS, itemPro()));
    public static final RegistryObject<Item> TRANSMISSION_BOOTS = ITEMS.register("transmission_boots",
            () -> new ArmorItem(NTRArmorMaterials.TRANSMISSION, EquipmentSlot.FEET, itemPro()));

    public static final RegistryObject<Item> TRANSMISSION_BUCKET = ITEMS.register("transmission_bucket",
            () -> new TranBucketItem(() -> Fluids.EMPTY, itemPro().stacksTo(16)));
    public static final RegistryObject<Item> WATER_TRANSMISSION_BUCKET = ITEMS.register("water_transmission_bucket",
            () -> new TranBucketItem(() -> Fluids.WATER, itemPro().stacksTo(1)));
    public static final RegistryObject<Item> LAVA_TRANSMISSION_BUCKET = ITEMS.register("lava_transmission_bucket",
            () -> new TranBucketItem(() -> Fluids.LAVA, itemPro().stacksTo(1)));

    public static final RegistryObject<Item> TRANSMISSION_ORE = ITEMS.register("transmission_ore",
            () -> new BlockItem(NTRBlocks.TRANSMISSION_ORE.get(), itemPro()));

    public static Item.Properties itemPro() {
        return new Item.Properties().tab(NTRTabs.MAIN).fireResistant();
    }
    public static Item.Properties itemProX() {
        return new Item.Properties().tab(NTRTabs.MAIN);
    }
}
