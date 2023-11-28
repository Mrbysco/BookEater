package com.mrbysco.bookeater.hander;

import com.mrbysco.bookeater.registry.ModRegistry;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.damagesource.CombatRules;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobType;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class DamageHandler {
	/**
	 * Reduces damage based on resistance effects
	 */
	@SubscribeEvent
	public void onDamage(LivingDamageEvent event) {
		LivingEntity livingEntity = event.getEntity();
		if (livingEntity != null && !livingEntity.getActiveEffects().isEmpty()) {
			final DamageSource damageSource = event.getSource();
			if (!damageSource.is(DamageTypeTags.BYPASSES_INVULNERABILITY)) {
				int protection = 0;
				if (livingEntity.hasEffect(ModRegistry.BLAST_RESISTANCE.get()) && damageSource.is(DamageTypeTags.IS_EXPLOSION)) {
					MobEffectInstance effectInstance = livingEntity.getEffect(ModRegistry.BLAST_RESISTANCE.get());
					if (effectInstance != null) {
						protection = (effectInstance.getAmplifier() + 1) * 2;
					}
				} else if (livingEntity.hasEffect(ModRegistry.PROJECTILE_RESISTANCE.get()) && damageSource.is(DamageTypeTags.IS_PROJECTILE)) {
					MobEffectInstance effectInstance = livingEntity.getEffect(ModRegistry.PROJECTILE_RESISTANCE.get());
					if (effectInstance != null) {
						protection = (effectInstance.getAmplifier() + 1) * 2;
					}
				}

				if (protection > 0)
					event.setAmount(CombatRules.getDamageAfterMagicAbsorb(event.getAmount(), protection));
			}
		}
	}

	/**
	 * Increases damage based on custom effects
	 */
	@SubscribeEvent
	public void onAttackEntity(LivingDamageEvent event) {
		final DamageSource damageSource = event.getSource();
		Entity attacking = damageSource.getDirectEntity();
		if (attacking instanceof LivingEntity attacker && !attacker.getActiveEffects().isEmpty()) {
			float damageAmount = event.getAmount();
			float newDamage = damageAmount;
			LivingEntity target = event.getEntity();
			if (attacker.hasEffect(ModRegistry.SMITE.get()) && target.getMobType() == MobType.UNDEAD) {
				MobEffectInstance effectInstance = attacker.getEffect(ModRegistry.SMITE.get());
				if (effectInstance != null) {
					newDamage *= 1.0F + ((effectInstance.getAmplifier() + 1) * 0.125F);
				}
			}
			if (attacker.hasEffect(ModRegistry.BACKSTABBING.get()) && !event.getEntity().hasLineOfSight(attacker)) {
				MobEffectInstance effectInstance = attacker.getEffect(ModRegistry.BACKSTABBING.get());
				newDamage *= 1.0F + ((effectInstance.getAmplifier() + 1) * 0.125F);
			}
			if (newDamage > damageAmount)
				event.setAmount(newDamage);
		}
	}

	/**
	 * Target the attacker
	 */
	@SubscribeEvent
	public void onAttack(LivingDamageEvent event) {
		final LivingEntity attacked = event.getEntity();
		final DamageSource damageSource = event.getSource();
		Entity attacking = damageSource.getDirectEntity();
		if (attacking instanceof LivingEntity livingEntity && !livingEntity.getActiveEffects().isEmpty()) {
			if (attacked.hasEffect(ModRegistry.BLAZE_AURA.get())) {
				livingEntity.setSecondsOnFire(5 + (attacked.getEffect(ModRegistry.BLAZE_AURA.get()).getAmplifier()));
			}
		}
	}
}
