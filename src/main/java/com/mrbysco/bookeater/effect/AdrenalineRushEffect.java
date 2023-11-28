package com.mrbysco.bookeater.effect;

import net.minecraft.util.Mth;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeMap;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;

import java.util.UUID;

public class AdrenalineRushEffect extends CustomEffect {
	private final UUID attributeUUID = UUID.fromString("2b524416-62bc-4e9d-88b4-0296b64423c5");

	public AdrenalineRushEffect(int color) {
		super(MobEffectCategory.NEUTRAL, color);
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
		float maxHealth = livingEntity.getMaxHealth();
		float currentHealth = livingEntity.getHealth();
		double value;
		if (currentHealth <= maxHealth) {
			int difference = (int) (maxHealth - currentHealth);
			if (currentHealth >= 1.0F) {
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
