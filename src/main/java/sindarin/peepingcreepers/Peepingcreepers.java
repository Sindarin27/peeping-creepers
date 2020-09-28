package sindarin.peepingcreepers;

import me.sargunvohra.mcmods.autoconfig1u.AutoConfig;
import me.sargunvohra.mcmods.autoconfig1u.serializer.JanksonConfigSerializer;
import net.fabricmc.api.ModInitializer;
import sindarin.peepingcreepers.config.ModConfig;

public class Peepingcreepers implements ModInitializer {
    public static ModConfig CONFIG = new ModConfig();
    @Override
    public void onInitialize() {
        AutoConfig.register(ModConfig.class, JanksonConfigSerializer::new);
        CONFIG = AutoConfig.getConfigHolder(ModConfig.class).getConfig();
    }
}
