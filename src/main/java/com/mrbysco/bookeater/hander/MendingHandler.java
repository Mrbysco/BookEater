package com.mrbysco.bookeater.hander;

import com.mrbysco.bookeater.registry.ModRegistry;
import net.minecraft.world.entity.ExperienceOrb;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.player.PlayerXpEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class MendingHandler {

	@SubscribeEvent
	public void onPickupXP(PlayerXpEvent.PickupXp event) {
		Player player = event.getEntity();
		if (player != null && !player.getActiveEffects().isEmpty()) {
			ExperienceOrb orb = event.getOrb();
			if (player.hasEffect(ModRegistry.LIFE_MENDING.get())) {
				orb.value = this.applyLifeMending(player, orb.value);
			}
		}
	}

	private int applyLifeMending(Player player, int value) {
		int missingHealth = (int) (player.getMaxHealth() - player.getHealth());
		int i = Math.min(value * 2, missingHealth);
		player.setHealth(player.getHealth() + (missingHealth - i));
		int j = value - i / 2;
		return j > 0 ? this.applyLifeMending(player, j) : 0;
	}

}
