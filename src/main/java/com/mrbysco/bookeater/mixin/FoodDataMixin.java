package com.mrbysco.bookeater.mixin;

import com.mrbysco.bookeater.registry.ModRegistry;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.food.FoodData;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(FoodData.class)
public abstract class FoodDataMixin {
	@Shadow
	public abstract void eat(int p_38708_, float p_38709_);

	@Inject(method = "eat(Lnet/minecraft/world/item/Item;Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/world/entity/LivingEntity;)V", at = @At(
			value = "INVOKE",
			target = "Lnet/minecraft/world/food/FoodData;eat(IF)V",
			shift = At.Shift.AFTER,
			ordinal = 0))
	public void bookeater_eat(Item item, ItemStack stack, LivingEntity livingEntity, CallbackInfo ci) {
		if (livingEntity.hasEffect(ModRegistry.EXQUISITE_TOUCH.get())) {
			FoodProperties foodproperties = stack.getFoodProperties(livingEntity);
			this.eat(Mth.ceil(foodproperties.getNutrition() * 0.4F), Mth.ceil((foodproperties.getSaturationModifier() * 0.4F)));
		}
	}
}