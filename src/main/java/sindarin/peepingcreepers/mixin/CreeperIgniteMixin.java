package sindarin.peepingcreepers.mixin;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.CreeperSwellGoal;
import net.minecraft.entity.monster.CreeperEntity;
import net.minecraft.util.math.vector.Vector3d;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = CreeperSwellGoal.class)
public class CreeperIgniteMixin {
    @Shadow @Final private CreeperEntity swellingCreeper;

    @Inject(at=@At("RETURN"), method= "shouldExecute()Z", cancellable = true)
    private void onCanStart(CallbackInfoReturnable<Boolean> cir) {
        //If method thinks creeper can start, think again
        if (cir.getReturnValue()) {
            CreeperEntity peeper = this.swellingCreeper;
            if (peeper.getCreeperState() > 0) {cir.setReturnValue(true); return; }
            LivingEntity seer = peeper.getAttackTarget();
            if (seer == null) {cir.setReturnValue(false); return; }
            cir.setReturnValue(canSee(seer, peeper));
        }
    }

    private boolean canSee(LivingEntity seer, LivingEntity peeper) {
        Vector3d peeperPos = peeper.getPositionVec();
        Vector3d vecLook = seer.getLookVec();
        Vector3d subtractedReverse = peeperPos.subtractReverse(seer.getPositionVec()).normalize();
        subtractedReverse = new Vector3d(subtractedReverse.x, 0, subtractedReverse.z);
        double dot = subtractedReverse.dotProduct(vecLook);
        return dot < 0.0;
    }
}
