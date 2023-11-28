package com.mrbysco.bookeater.hander;

import com.mrbysco.bookeater.registry.ModRegistry;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import net.minecraftforge.event.entity.living.LivingKnockBackEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class KnockHandler {

	@SubscribeEvent
	public void onKnockBack(LivingKnockBackEvent event) {
		LivingEntity livingEntity = event.getEntity();

		if (livingEntity.hasEffect(ModRegistry.KNOCKING.get()) &&
				livingEntity.tickCount - livingEntity.getLastHurtByMobTimestamp() < 50) {
			float strength = event.getStrength();
			LivingEntity hurtBy = livingEntity.getLastHurtByMob();
			if (hurtBy != null && !hurtBy.hasEffect(ModRegistry.KNOCKING.get())) {
				hurtBy.knockback(strength * 0.8f, -event.getOriginalRatioX(), -event.getOriginalRatioZ());
				event.setStrength(strength * 0.2f);
			}
		}
	}


	/**
	 * Code partly inspired by the Bouncy Modifier code from Tinkers Construct
	 * https://github.com/SlimeKnights/TinkersConstruct/blob/1.18.2/src/main/java/slimeknights/tconstruct/tools/modifiers/ability/armor/BouncyModifier.java
	 * Which is licensed MIT as of writing this (2020-11-29, 5:36PM GMT+1)
	 */
	@SubscribeEvent
	public void onFall(LivingFallEvent event) {
		LivingEntity livingEntity = event.getEntity();

		if (livingEntity == null || (livingEntity.fallDistance < 3 && livingEntity.getDeltaMovement().y > -0.3)) {
			return;
		}

		if (!livingEntity.hasEffect(ModRegistry.BOUNCY.get())) {
			return;
		}

		if (livingEntity.isSuppressingBounce()) {
			event.setDamageMultiplier(0.5f);
			return;
		} else {
			event.setDamageMultiplier(0.0f);
		}

		Vec3 motion = livingEntity.getDeltaMovement();
		if (!livingEntity.isSuppressingBounce()) {
			double gravity = livingEntity.getAttributeValue(ForgeMod.ENTITY_GRAVITY.get());
			double time = Math.sqrt(livingEntity.fallDistance / gravity);
			double velocity = gravity * time;
			livingEntity.setDeltaMovement(motion.x / 0.975f, velocity, motion.z / 0.975f);
			livingEntity.hurtMarked = true;
			bouncyMap.put(livingEntity.getUUID(), new Info(livingEntity.getDeltaMovement(), livingEntity.tickCount + 1));
		}

		livingEntity.playSound(SoundEvents.SLIME_BLOCK_FALL, 1f, 1f);

		event.setDistance(0.0F);
		event.setDamageMultiplier(0);
		if (!livingEntity.level().isClientSide) {
			livingEntity.hasImpulse = true;
			event.setCanceled(true);
			livingEntity.setOnGround(false); // need to be on ground for server to process this event
		}
	}

	private final Map<UUID, Info> bouncyMap = new HashMap<>();

	@SubscribeEvent
	public void onLivingTick(LivingEvent.LivingTickEvent event) {
		LivingEntity livingEntity = event.getEntity();
		if (!bouncyMap.isEmpty()) {
			bouncyMap.entrySet().removeIf(e -> {
				boolean flag = livingEntity.getUUID().equals(e.getKey());
				if (flag) {
					if (livingEntity.tickCount == e.getValue().nextTick()) {
						Vec3 jumpVec = e.getValue().vec3();
						livingEntity.setDeltaMovement(jumpVec);
						livingEntity.hasImpulse = true;
						return true;
					}
				}
				return false;
			});
		}
	}

	private record Info(Vec3 vec3, int nextTick) {

	}
}
