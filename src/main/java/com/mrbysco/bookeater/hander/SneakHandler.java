package com.mrbysco.bookeater.hander;

import com.mrbysco.bookeater.registry.ModRegistry;
import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.client.event.MovementInputUpdateEvent;

public class SneakHandler {

	@SubscribeEvent
	public void onMovementUpdate(MovementInputUpdateEvent event) {
		Player player = event.getEntity();
		if (player.hasEffect(ModRegistry.SHIFTING.get())) {
			event.getInput().shiftKeyDown = true;
		}
	}
}
