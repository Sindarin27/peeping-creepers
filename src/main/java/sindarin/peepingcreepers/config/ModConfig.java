package sindarin.peepingcreepers.config;

import me.sargunvohra.mcmods.autoconfig1u.ConfigData;
import me.sargunvohra.mcmods.autoconfig1u.annotation.Config;
import me.sargunvohra.mcmods.autoconfig1u.annotation.ConfigEntry;
import me.sargunvohra.mcmods.autoconfig1u.shadowed.blue.endless.jankson.Comment;

@Config(name="peepingcreepers")
public class ModConfig implements ConfigData {
    @ConfigEntry.BoundedDiscrete(min = 0, max=2048)
    @Comment("The range from which creepers will spot you and start approaching you. Vanilla=16, default=32")
    public int follow_range = 32;
    @ConfigEntry.BoundedDiscrete(min = 0, max=2048)
    @Comment("The maximum distance at which creepers breach. Best kept far below follow_range. Default=8")
    public int explode_range = 8;
    @Comment("Whether creepers should stalk you when they can get close enough")
    public boolean stalking = true;
    @Comment("Whether creepers should breach when they can't get close enough")
    public boolean breaching = true;
    @Comment("Whether creepers should have xray vision (recommended to keep on true for breaching)")
    public boolean xray = true;
}
