package com.mrbysco.bookeater.effect;

import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeMap;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.enchantment.EnchantmentHelper;

import java.util.UUID;

public class DrizzleEffect extends CustomEffect {
	private final UUID attributeUUID = UUID.fromString("fdf3a7a4-76ad-48a0-9f52-f2941ae5e4ef");
	private boolean isWet = false;
	private boolean changed = false;

	public DrizzleEffect(int color) {
		super(MobEffectCategory.BENEFICIAL, color);
	}

	@Override
	public boolean isDurationEffectTick(int duration, int amplifier) {
		return true;
	}

	@Override
	public void applyEffectTick(LivingEntity livingEntity, int amplifier) {
		AttributeMap attributeMap = livingEntity.getAttributes();
		AttributeInstance attributeinstance = attributeMap.getInstance(Attributes.MOVEMENT_SPEED);
		AttributeModifier attributeModifier = getSpeedModifier(amplifier);
		if (!EnchantmentHelper.hasAquaAffinity(livingEntity)) {
			if (livingEntity.isInWaterOrRain()) {
				if (isWet != true) {
					changed = true;
					isWet = true;
				}
			} else {
				if (isWet != false) {
					changed = true;
					isWet = false;
				}
			}

			if (changed) {
				if (attributeinstance != null) {
					attributeinstance.removeModifier(attributeUUID);
					attributeinstance.addPermanentModifier(attributeModifier);
				}
			}
		} else {
			if (attributeinstance != null && attributeinstance.hasModifier(attributeModifier)) {
				attributeinstance.removeModifier(attributeUUID);
			}
		}
	}

	private AttributeModifier getSpeedModifier(int amplifier) {
		return new AttributeModifier(attributeUUID,
				this::getDescriptionId, (double) 0.2F + (0.025F * amplifier), AttributeModifier.Operation.ADDITION);
	}
}
