package com.mrbysco.bookeater.effect;

import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import java.util.List;

public class LootingEffect extends CustomEffect {

	public LootingEffect(int color) {
		super(MobEffectCategory.BENEFICIAL, color);
	}

	@Override
	public boolean isDurationEffectTick(int duration, int amplifier) {
		return true;
	}

	@Override
	public void applyEffectTick(LivingEntity livingEntity, int amplifier) {
		if (livingEntity.isCrouching() && livingEntity.tickCount % 20 == 0) {
			final Level level = livingEntity.level();
			final RandomSource randomSource = livingEntity.getRandom();
			List<LivingEntity> entityList = level.getNearbyEntities(LivingEntity.class, TargetingConditions.forCombat(), livingEntity,
					livingEntity.getBoundingBox().inflate(0.5D, 0, 0.5D));
			for (LivingEntity target : entityList) {
				if (target instanceof Player player && livingEntity.getLastHurtByMob() != player) {
					continue;
				}

				if (randomSource.nextDouble() <= 0.15D) {
					EquipmentSlot slot = EquipmentSlot.values()[randomSource.nextInt(EquipmentSlot.values().length)];
					if (!target.getItemBySlot(slot).isEmpty()) {
						ItemEntity itementity = new ItemEntity(level, target.getX(), target.getEyeY(), target.getZ(), target.getItemBySlot(slot).copy());
						itementity.setPickUpDelay(40);
						target.setItemSlot(slot, ItemStack.EMPTY);
					}
				}
			}
		}
	}
}
