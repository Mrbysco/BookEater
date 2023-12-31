package com.mrbysco.bookeater.hander;

import com.mrbysco.bookeater.registry.ModRegistry;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.client.event.MovementInputUpdateEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class SneakHandler {

	@SubscribeEvent
	public void onMovementUpdate(MovementInputUpdateEvent event) {
		Player player = event.getEntity();
		if (player.hasEffect(ModRegistry.SHIFTING.get())) {
			event.getInput().shiftKeyDown = true;
		}
	}
}
