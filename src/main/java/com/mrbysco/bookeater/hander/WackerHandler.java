package com.mrbysco.bookeater.hander;

import com.mrbysco.bookeater.registry.ModRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.EntitySelector;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class WackerHandler {

	@SubscribeEvent
	public void onLeftClick(PlayerInteractEvent.LeftClickBlock event) {
		Player player = event.getEntity();
		if (player != null && !player.getActiveEffects().isEmpty()) {
			if (player.hasEffect(ModRegistry.WEED_WACKER.get())) {
				final Level level = event.getLevel();
				final BlockPos pos = event.getPos();
				BlockState state = level.getBlockState(pos);
				if (!state.getCollisionShape(level, pos).isEmpty()) {
					return;
				}

				EntityHitResult hitResult = traceEntity(level, player);
				if (hitResult != null) {
					if (!event.getLevel().isClientSide) {
						player.attack(hitResult.getEntity());
						player.resetAttackStrengthTicker();
					}
				}
			}
		}
	}

	private EntityHitResult traceEntity(Level level, Player player) {
		final double reach = player.getEntityReach();
		final Vec3 eyePos = player.getEyePosition(1.0F);
		final Vec3 viewVec = player.getViewVector(1.0F);
		Vec3 reached = eyePos.add(viewVec.x * reach, viewVec.y * reach, viewVec.z * reach);

		HitResult result = level.clip(new ClipContext(eyePos, viewVec, ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE, player));
		if (result.getType() != HitResult.Type.MISS)
			reached = result.getLocation();

		return ProjectileUtil.getEntityHitResult(
				level, player, eyePos, reached, new AABB(eyePos, reached),
				EntitySelector.NO_CREATIVE_OR_SPECTATOR
						.and(entity -> entity != null
								&& entity.isPickable()
								&& entity instanceof LivingEntity
								&& !(entity instanceof FakePlayer)
						)
		);
	}

}
