package com.mrbysco.bookeater.effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;

public class CustomEffect extends MobEffect {
	public CustomEffect(MobEffectCategory category, int color) {
		super(category, color);
	}

	@Override
	public boolean isDurationEffectTick(int duration, int amplifier) {
		return false;
	}

	@Override
	public void applyEffectTick(LivingEntity livingEntity, int amplifier) {

	}
}
