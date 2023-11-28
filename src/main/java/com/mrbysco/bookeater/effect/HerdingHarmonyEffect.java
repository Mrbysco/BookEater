package com.mrbysco.bookeater.effect;

import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.level.Level;

import java.util.List;

public class HerdingHarmonyEffect extends CustomEffect {

	public HerdingHarmonyEffect(int color) {
		super(MobEffectCategory.NEUTRAL, color);
	}

	@Override
	public boolean isDurationEffectTick(int duration, int amplifier) {
		return true;
	}

	@Override
	public void applyEffectTick(LivingEntity livingEntity, int amplifier) {
		if (livingEntity.isCrouching() && livingEntity.tickCount % 10 == 0) {
			final Level level = livingEntity.level();
			List<Animal> animals = level.getNearbyEntities(Animal.class, TargetingConditions.forCombat(), livingEntity,
					livingEntity.getBoundingBox().inflate(10D, 4D, 10D));
			for (Animal animal : animals) {
				animal.getLookControl().setLookAt(livingEntity, (float) (animal.getMaxHeadYRot() + 20), (float) animal.getMaxHeadXRot());
				if (animal.distanceToSqr(livingEntity) < 6.25D) {
					animal.getNavigation().stop();
				} else {
					animal.getNavigation().moveTo(livingEntity, 1.0D);
				}
			}
		}
	}
}
