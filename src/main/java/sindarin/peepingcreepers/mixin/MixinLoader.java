package sindarin.peepingcreepers.mixin;

import org.spongepowered.asm.mixin.Mixins;
import org.spongepowered.asm.mixin.connect.IMixinConnector;

public class MixinLoader implements IMixinConnector {
    @Override
    public void connect() {
        Mixins.addConfiguration("peepingcreepers.mixins.json");
    }
}
