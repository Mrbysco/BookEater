package com.mrbysco.bookeater.util;

import com.mrbysco.bookeater.registry.ModRegistry;
import net.minecraft.util.Mth;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.player.Player;

public class EffectHelper {
	public static boolean cancelArmorDamage(Player player) {
		if (player.hasEffect(ModRegistry.EXQUISITE_TOUCH.get())) {
			MobEffectInstance effectInstance = player.getEffect(ModRegistry.EXQUISITE_TOUCH.get());
			int amplifier = Mth.clamp(effectInstance.getAmplifier() + 1, 1, 10);
			return amplifier == 10 || player.getRandom().nextInt(10) <= amplifier;
		}
		return false;
	}
}
