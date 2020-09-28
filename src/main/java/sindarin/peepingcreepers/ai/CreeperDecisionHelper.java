package sindarin.peepingcreepers.ai;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.CreeperEntity;
import net.minecraft.util.math.Vec3d;
import sindarin.peepingcreepers.Peepingcreepers;

public class CreeperDecisionHelper {
    public static boolean canSee(LivingEntity seer, LivingEntity peeper) {
        Vec3d peeperPos = peeper.getPos();
        Vec3d vecLook = seer.getRotationVector();
        Vec3d subtractedReverse = peeperPos.reverseSubtract(seer.getPos()).normalize();
        subtractedReverse = new Vec3d(subtractedReverse.x, 0, subtractedReverse.z);
        double dot = subtractedReverse.dotProduct(vecLook);
        return dot < 0.0;
    }

    public static boolean shouldBreach(LivingEntity seer, CreeperEntity breacher) {
        return breacher.age > 60 && !breacher.isNavigating() && breacher.squaredDistanceTo(seer) < Peepingcreepers.CONFIG.explode_range * Peepingcreepers.CONFIG.explode_range;
    }
}
