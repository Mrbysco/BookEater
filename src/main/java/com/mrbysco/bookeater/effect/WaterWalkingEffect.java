package com.mrbysco.bookeater.effect;

import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.phys.Vec3;

public class WaterWalkingEffect extends CustomEffect {
	public WaterWalkingEffect(int color) {
		super(MobEffectCategory.BENEFICIAL, color);
	}

	@Override
	public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) {
		return true;
	}

	@Override
	public void applyEffectTick(LivingEntity livingEntity, int amplifier) {
		if (livingEntity.isInWater() && !livingEntity.isCrouching()) {
			Vec3 delta = livingEntity.getDeltaMovement();
			livingEntity.setDeltaMovement(delta.x, 0.4D, delta.y);
		}
	}
}
