package studio.renascence.ntr;

import net.minecraftforge.common.ForgeConfigSpec;

public class NTRConfig {
    public static ForgeConfigSpec COMMON_CONFIG;
    public static ForgeConfigSpec.DoubleValue TRANSMISSION_HEALTH;
    public static ForgeConfigSpec.DoubleValue TRANSMISSITE_ITEM_CHANCE;
    public static ForgeConfigSpec.IntValue TRANSMISSITE_ITEM_DAMAGE;
    public static ForgeConfigSpec.IntValue TRANSMISSITE_ARMOR_PROTECTION_DISTANCE;

    static {
        ForgeConfigSpec.Builder builder = new ForgeConfigSpec.Builder();

        builder.comment("Fire settings").push("fire");
        TRANSMISSION_HEALTH = builder.comment("The percent health that can transmit").defineInRange("transmission_health", 1.0D, 0.0D, 1.0D);
        builder.pop();

        builder.comment("Transmissite items settings").push("transmission");
        TRANSMISSITE_ITEM_CHANCE = builder.comment("The probability of transmission when attacking").defineInRange("transmission_probability", 0.75D, 0.0D, 1.0D);
        TRANSMISSITE_ITEM_DAMAGE = builder.comment("The item`s damage of transmission when attacking").defineInRange("damage", 5, 0, Integer.MAX_VALUE);
        TRANSMISSITE_ARMOR_PROTECTION_DISTANCE = builder.comment("The protection distance when being attacking").defineInRange("transmission_probability", 5, 1, Integer.MAX_VALUE);
        builder.pop();

        COMMON_CONFIG = builder.build();
    }
}
