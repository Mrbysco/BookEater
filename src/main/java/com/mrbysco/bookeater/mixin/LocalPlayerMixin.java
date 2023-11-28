package com.mrbysco.bookeater.mixin;

import com.mrbysco.bookeater.registry.ModRegistry;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.util.Mth;
import net.minecraft.world.effect.MobEffectInstance;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(LocalPlayer.class)
public class LocalPlayerMixin {
	@ModifyArg(method = "aiStep()V",
			at = @At(
					value = "INVOKE",
					target = "Lnet/minecraft/client/player/Input;tick(ZF)V"),
			index = 1)
	private float bookeater_aiStep(float walkingSpeed) {
		LocalPlayer localPlayer = (LocalPlayer) (Object) this;
		if (localPlayer.hasEffect(ModRegistry.SHIFTING.get())) {
			MobEffectInstance effectInstance = localPlayer.getEffect(ModRegistry.SHIFTING.get());
			if (effectInstance != null) {
				return Mth.clamp(walkingSpeed + ((effectInstance.getAmplifier() + 1) * 0.15F), 0.0F, 1.0F);
			}
		}
		return walkingSpeed;
	}
}
