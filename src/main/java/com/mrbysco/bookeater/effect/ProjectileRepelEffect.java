package com.mrbysco.bookeater.effect;

import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

import java.util.List;

public class ProjectileRepelEffect extends CustomEffect {
	private final double defaultRange = 5.0D;

	public ProjectileRepelEffect(int color) {
		super(MobEffectCategory.BENEFICIAL, color);
	}

	@Override
	public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) {
		return true;
	}

	@Override
	public void applyEffectTick(LivingEntity livingEntity, int amplifier) {
		double x = livingEntity.getX();
		double y = livingEntity.getEyeY();
		double z = livingEntity.getZ();
		RandomSource random = livingEntity.getRandom();

		double range = defaultRange + (amplifier * 0.3F);
		List<Projectile> projectiles = livingEntity.getCommandSenderWorld()
				.getEntitiesOfClass(Projectile.class, new AABB(
						x - range, y - range, z - range,
						x + range, y + range, z + range)).stream()
				.filter(proj -> !proj.isAlive() && !proj.onGround() && proj.getOwner() != livingEntity &&
						(proj instanceof AbstractArrow abstractArrow && abstractArrow.inGround)).toList();
		for (Projectile projectile : projectiles) {
			double newX = (projectile.getX() < livingEntity.getX() ? 1 : 0) + (random.nextGaussian() * 0.02D);
			double newY = (projectile.getY() < livingEntity.getY() ? 1 : 0) + (random.nextGaussian() * 0.02D);
			double newZ = (projectile.getZ() < livingEntity.getZ() ? 1 : 0) + (random.nextGaussian() * 0.02D);
			float pushingForce = 0.14F + (float) (random.nextGaussian() * 0.02D);

			projectile.moveRelative(pushingForce, new Vec3(
					projectile.getX() + newX,
					projectile.getY() + newY + 1,
					projectile.getZ() + newZ));
		}

	}
}
