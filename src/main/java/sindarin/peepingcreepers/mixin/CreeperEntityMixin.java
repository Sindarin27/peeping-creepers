package sindarin.peepingcreepers.mixin;

import net.minecraft.entity.ai.goal.ActiveTargetGoal;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.CreeperEntity;
import net.minecraft.entity.mob.MobEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import sindarin.peepingcreepers.Peepingcreepers;
import sindarin.peepingcreepers.ai.XrayFollowTargetGoal;

@Mixin(CreeperEntity.class)
public class CreeperEntityMixin {
    @Redirect(at=@At(value = "NEW", target="net/minecraft/entity/ai/goal/ActiveTargetGoal"), method= "initGoals()V")
    private ActiveTargetGoal createFollowGoal(MobEntity creeper, Class targetClass, boolean checkVisibility) {
        return Peepingcreepers.CONFIG.xray ? new XrayFollowTargetGoal(creeper, targetClass, false) : new ActiveTargetGoal(creeper, targetClass, true);
    }

    @Inject(method="createCreeperAttributes", at=@At(value="RETURN"))
    private static void createAttributes(CallbackInfoReturnable<DefaultAttributeContainer.Builder> ci) {
        ci.getReturnValue().add(EntityAttributes.GENERIC_FOLLOW_RANGE, Peepingcreepers.CONFIG.follow_range);
    }
}
