package sindarin.peepingcreepers.ai;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.CreeperEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import sindarin.peepingcreepers.Peepingcreepers;

public class CreeperDecisionHelper {
    public static boolean canSee(LivingEntity seer, LivingEntity peeper) {
        Vec3d peeperPos = peeper.getPos();
        Vec3d vecLook = seer.getRotationVector();
        Vec3d subtractedReverse = peeperPos.relativize(seer.getPos()).normalize();
        subtractedReverse = new Vec3d(subtractedReverse.x, 0, subtractedReverse.z);
        double dot = subtractedReverse.dotProduct(vecLook);
        return dot < 0.0;
    }

    public static boolean shouldBreach(LivingEntity seer, CreeperEntity breacher) {
        return breacher.age > 60 && !breacher.isNavigating() && breacher.squaredDistanceTo(seer) < Peepingcreepers.CONFIG.explode_range * Peepingcreepers.CONFIG.explode_range;
    }

    public static boolean isInSpawnProtection(ServerWorld world, BlockPos pos) {
        if (world.getRegistryKey() != World.OVERWORLD) {
            return false;
        } else if (world.getServer().getSpawnProtectionRadius() <= 0) {
            return false;
        } else {
            BlockPos blockPos = world.getSpawnPos();
            int i = MathHelper.abs(pos.getX() - blockPos.getX());
            int j = MathHelper.abs(pos.getZ() - blockPos.getZ());
            int k = Math.max(i, j);
            return k <= world.getServer().getSpawnProtectionRadius();
        }
    }
}
