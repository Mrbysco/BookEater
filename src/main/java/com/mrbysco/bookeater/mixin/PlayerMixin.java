package com.mrbysco.bookeater.mixin;

import com.mrbysco.bookeater.util.EffectHelper;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Player.class)
public class PlayerMixin {

	@Inject(method = "hurtArmor(Lnet/minecraft/world/damagesource/DamageSource;F)V",
			at = @At(value = "HEAD"),
			cancellable = true)
	public void bookeater_hurtArmor(DamageSource source, float amount, CallbackInfo ci) {
		Player player = (Player) (Object) this;
		if (EffectHelper.cancelArmorDamage(player)) {
			ci.cancel();
		}
	}

	@Inject(method = "hurtHelmet(Lnet/minecraft/world/damagesource/DamageSource;F)V",
			at = @At(value = "HEAD"),
			cancellable = true)
	public void bookeater_hurtHelmet(DamageSource source, float amount, CallbackInfo ci) {
		Player player = (Player) (Object) this;
		if (EffectHelper.cancelArmorDamage(player)) {
			ci.cancel();
		}
	}
}
