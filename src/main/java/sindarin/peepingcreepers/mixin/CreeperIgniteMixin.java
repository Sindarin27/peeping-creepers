package sindarin.peepingcreepers.mixin;

import net.minecraft.client.util.math.Vector3d;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.CreeperIgniteGoal;
import net.minecraft.entity.mob.CreeperEntity;
import net.minecraft.util.math.Vec3d;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(CreeperIgniteGoal.class)
public class CreeperIgniteMixin {
    @Shadow @Final private CreeperEntity creeper;

    @Shadow private LivingEntity target;

    @Inject(at=@At("RETURN"), method="canStart", cancellable = true)
    private void onCanStart(CallbackInfoReturnable<Boolean> cir) {
        //If method thinks creeper can start, think again
        if (cir.getReturnValue()) {
            CreeperEntity peeper = this.creeper;
            if (peeper.getFuseSpeed() > 0) {cir.setReturnValue(true); return; }
            LivingEntity seer = peeper.getTarget();
            if (seer == null) {cir.setReturnValue(false); return; }
            cir.setReturnValue(canSee(seer, peeper));
        }
    }

    private boolean canSee(LivingEntity seer, LivingEntity peeper) {
        Vec3d peeperPos = peeper.getPos();
        Vec3d vecLook = seer.getRotationVector();
        Vec3d subtractedReverse = peeperPos.reverseSubtract(seer.getPos()).normalize();
        subtractedReverse = new Vec3d(subtractedReverse.x, 0, subtractedReverse.z);
        double dot = subtractedReverse.dotProduct(vecLook);
        return dot < 0.0;
    }
}
