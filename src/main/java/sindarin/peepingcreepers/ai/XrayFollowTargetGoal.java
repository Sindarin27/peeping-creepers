package sindarin.peepingcreepers.ai;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.ActiveTargetGoal;
import net.minecraft.entity.mob.MobEntity;

import java.util.function.Predicate;

public class XrayFollowTargetGoal<T extends LivingEntity> extends ActiveTargetGoal<T> {
    public XrayFollowTargetGoal(MobEntity mob, Class<T> targetClass, boolean checkVisibility) {
        super(mob, targetClass, checkVisibility);
        this.targetPredicate.ignoreVisibility();
    }

    public XrayFollowTargetGoal(MobEntity mob, Class<T> targetClass, boolean checkVisibility, boolean checkCanNavigate) {
        super(mob, targetClass, checkVisibility, checkCanNavigate);
        this.targetPredicate.ignoreVisibility();
    }

    public XrayFollowTargetGoal(MobEntity mob, Class<T> targetClass, int reciprocalChance, boolean checkVisibility, boolean checkCanNavigate, Predicate<net.minecraft.entity.LivingEntity> targetPredicate) {
        super(mob, targetClass, reciprocalChance, checkVisibility, checkCanNavigate, targetPredicate);
        this.targetPredicate.ignoreVisibility();
    }
}
