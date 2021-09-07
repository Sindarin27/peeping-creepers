package sindarin.peepingcreepers;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.JanksonConfigSerializer;
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
