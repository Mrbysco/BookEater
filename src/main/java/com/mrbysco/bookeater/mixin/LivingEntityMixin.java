package com.mrbysco.bookeater.mixin;

import com.mojang.datafixers.util.Pair;
import com.mrbysco.bookeater.util.BookHelper;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.EnchantedBookItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)
public class LivingEntityMixin {

	@Inject(method = "addEatEffect(Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/world/level/Level;Lnet/minecraft/world/entity/LivingEntity;)V", at = @At(
			value = "INVOKE",
			target = "Lnet/minecraft/world/item/ItemStack;getFoodProperties(Lnet/minecraft/world/entity/LivingEntity;)Lnet/minecraft/world/food/FoodProperties;",
			shift = At.Shift.BEFORE,
			ordinal = 0), cancellable = true)
	private void bookeater_addEatEffect(ItemStack stack, Level level, LivingEntity livingEntity, CallbackInfo ci) {
		if (stack.getItem() instanceof EnchantedBookItem) {
			FoodProperties foodProperties = BookHelper.getActualProperties(stack, livingEntity);
			if (foodProperties != null) {
				for (Pair<MobEffectInstance, Float> pair : foodProperties.getEffects()) {
					if (!level.isClientSide && pair.getFirst() != null && level.random.nextFloat() < pair.getSecond()) {
						livingEntity.addEffect(new MobEffectInstance(pair.getFirst()));
					}
				}
				ci.cancel();
			}
		}
	}
}
