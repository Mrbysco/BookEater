package com.mrbysco.bookeater.effect;

import net.minecraft.util.Mth;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeMap;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.level.Level;

import java.util.UUID;

public class DepthStriderEffect extends CustomEffect {
	private final UUID attributeUUID = UUID.fromString("d8dc7af8-1708-49fa-a439-ca03161cb4c7");

	public DepthStriderEffect(int color) {
		super(MobEffectCategory.BENEFICIAL, color);
	}

	@Override
	public boolean isDurationEffectTick(int duration, int amplifier) {
		return true;
	}

	@Override
	public void applyEffectTick(LivingEntity livingEntity, int amplifier) {
		if (livingEntity.tickCount % 20 == 0) {
			AttributeMap attributeMap = livingEntity.getAttributes();
			AttributeInstance attributeinstance = attributeMap.getInstance(Attributes.MOVEMENT_SPEED);
			AttributeModifier attributeModifier = getSpeedModifier(livingEntity, amplifier);
			if (attributeinstance != null) {
				attributeinstance.removeModifier(attributeUUID);
				attributeinstance.addPermanentModifier(attributeModifier);
			}
		}
	}

	private AttributeModifier getSpeedModifier(LivingEntity livingEntity, int amplifier) {
		final Level level = livingEntity.level();
		int maxY = level.getSeaLevel();
		double playerY = livingEntity.getY();
		double value;
		if (playerY <= maxY) {
			int difference = (int) (maxY - playerY);
			if (playerY >= level.getMinBuildHeight()) {
				value = difference * (0.00075 * (amplifier + 1));
			} else {
				value = 0.0D;
			}
		} else {
			value = 0.0D;
		}

		return new AttributeModifier(attributeUUID,
				this::getDescriptionId, (double) Mth.clamp(value, 0, 0.1D), AttributeModifier.Operation.ADDITION);
	}
}
