package studio.renascence.ntr.util;

import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.util.LazyLoadedValue;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.NotNull;
import studio.renascence.ntr.init.NTRItems;

import java.util.function.Supplier;

public enum NTRItemTiers implements Tier {
    TRANSMISSITE(2, 514, 6.0F, 2.0F, 8, () -> Ingredient.of(NTRItems.TRANSMISSITE_DUST.get()));

    private final int level;
    private final int uses;
    private final float speed;
    private final float damage;
    private final int enchantmentValue;
    private final LazyLoadedValue<Ingredient> repairIngredient;

    NTRItemTiers(int level, int uses, float speed, float damage, int ev, Supplier<Ingredient> ingredient) {
        this.level = level;
        this.uses = uses;
        this.speed = speed;
        this.damage = damage;
        this.enchantmentValue = ev;
        this.repairIngredient = new LazyLoadedValue<>(ingredient);
    }

    public int getUses() {
        return this.uses;
    }

    public float getSpeed() {
        return this.speed;
    }

    public float getAttackDamageBonus() {
        return this.damage;
    }

    public int getLevel() {
        return this.level;
    }

    public int getEnchantmentValue() {
        return this.enchantmentValue;
    }

    public @NotNull Ingredient getRepairIngredient() {
        return this.repairIngredient.get();
    }

    public @NotNull TagKey<Block> getTag() {
        return BlockTags.NEEDS_IRON_TOOL;
    }
}
