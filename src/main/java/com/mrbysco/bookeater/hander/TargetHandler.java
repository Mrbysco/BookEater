package com.mrbysco.bookeater.hander;

import com.mrbysco.bookeater.registry.ModRegistry;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobType;
import net.minecraftforge.event.entity.living.LivingChangeTargetEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class TargetHandler {

	@SubscribeEvent
	public void onKnockBack(LivingChangeTargetEvent event) {
		LivingEntity livingEntity = event.getEntity();
		if (livingEntity.getMobType() != MobType.ARTHROPOD) return;

		if (event.getNewTarget() != null && event.getNewTarget().hasEffect(ModRegistry.BANE_OF_ARTHROPODS.get())) {
			event.setNewTarget(null);
		}
	}
}
