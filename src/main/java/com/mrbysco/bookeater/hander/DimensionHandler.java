package com.mrbysco.bookeater.hander;

import com.mrbysco.bookeater.registry.ModRegistry;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.entity.EntityTravelToDimensionEvent;

public class DimensionHandler {

	@SubscribeEvent
	public void onMovementUpdate(EntityTravelToDimensionEvent event) {
		if (event.getEntity() instanceof LivingEntity livingEntity && livingEntity.hasEffect(ModRegistry.BOUND.get())) {
			if (livingEntity instanceof Player player)
				player.sendSystemMessage(Component.translatable("bookeater.bound.message"));
			event.setCanceled(true);
		}
	}
}
