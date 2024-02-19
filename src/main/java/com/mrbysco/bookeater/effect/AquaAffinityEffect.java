package com.mrbysco.bookeater.effect;

import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeMap;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.neoforged.neoforge.common.NeoForgeMod;

import java.util.UUID;

public class AquaAffinityEffect extends CustomEffect {
	private final UUID digUUID = UUID.fromString("a22ea9e8-1f37-4164-915b-8952c29c65ea");
	private final AttributeModifier digModifier = new AttributeModifier(digUUID,
			AquaAffinityEffect.this.getDescriptionId(), (double) 5F, AttributeModifier.Operation.MULTIPLY_TOTAL);
	private boolean inWater = false;
	private boolean changed = false;

	public AquaAffinityEffect(int color) {
		super(MobEffectCategory.BENEFICIAL, color);
	}

	@Override
	public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) {
		return true;
	}

	@Override
	public void applyEffectTick(LivingEntity livingEntity, int amplifier) {
		AttributeMap attributeMap = livingEntity.getAttributes();
		AttributeInstance attributeinstance = attributeMap.getInstance(Attributes.ATTACK_SPEED);
		if (!EnchantmentHelper.hasAquaAffinity(livingEntity)) {
			if (livingEntity.isEyeInFluidType(NeoForgeMod.WATER_TYPE.value())) {
				if (inWater != true) {
					changed = true;
					inWater = true;
				}
			} else {
				if (inWater != false) {
					changed = true;
					inWater = false;
				}
			}


			if (changed) {
				if (attributeinstance != null) {
					attributeinstance.removeModifier(digUUID);
					attributeinstance.addPermanentModifier(digModifier);
				}
			}
		} else {
			if (attributeinstance != null && attributeinstance.hasModifier(digModifier)) {
				attributeinstance.removeModifier(digUUID);
			}
		}
	}
}
