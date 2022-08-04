package studio.renascence.ntr;

import net.minecraftforge.common.ForgeConfigSpec;

public class NTRConfig {
    public static ForgeConfigSpec COMMON_CONFIG;
    public static ForgeConfigSpec.DoubleValue TRANSMISSION_HEALTH;

    static {
        ForgeConfigSpec.Builder builder = new ForgeConfigSpec.Builder();

        builder.comment("Fire settings").push("fire");
        TRANSMISSION_HEALTH = builder.comment("The percent health that can transmit").defineInRange("transmission_health", 1.0D, 0.0D, 1.0D);
        builder.pop();
        COMMON_CONFIG = builder.build();
    }

}
