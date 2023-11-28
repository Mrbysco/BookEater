package com.mrbysco.bookeater.mixin;

import com.mrbysco.bookeater.registry.ModFoods;
import net.minecraft.world.item.EnchantedBookItem;
import net.minecraft.world.item.Item;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(EnchantedBookItem.class)
public class EnchantedBookMixin {
	@ModifyArg(method = "<init>", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/Item;<init>(Lnet/minecraft/world/item/Item$Properties;)V"))
	private static Item.Properties cck$setCraftRemainder(Item.Properties properties) {
		return properties.food(ModFoods.BOOK);
	}
}
