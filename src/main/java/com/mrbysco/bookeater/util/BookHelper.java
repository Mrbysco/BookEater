package com.mrbysco.bookeater.util;

import com.mrbysco.bookeater.api.BookData;
import com.mrbysco.bookeater.data.BookEffectManager;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Optional;

public class BookHelper {
	public static FoodProperties getActualProperties(ItemStack stack, @Nullable LivingEntity entity) {
		FoodProperties properties = stack.getFoodProperties(entity);
		if (properties != null) {
			FoodProperties.Builder bookBuilder = (new FoodProperties.Builder()).nutrition(1).saturationMod(0.1F).alwaysEat();
			List<BookData> bookDataList = BookEffectManager.dataFromBook(stack);
			if (bookDataList != null && entity != null) {
				for (BookData data : bookDataList) {
					MobEffect effect;
					if (data.useEffectTag()) {
						TagKey<MobEffect> effectTagKey = TagKey.create(Registries.MOB_EFFECT, data.effectID());
						Optional<HolderSet.Named<MobEffect>> optionalHolder = BuiltInRegistries.MOB_EFFECT.getTag(effectTagKey);
						if (optionalHolder.isPresent() && optionalHolder.get().size() > 0) {
							Holder<MobEffect> holder = optionalHolder.get().getRandomElement(entity.getRandom()).orElse(null);
							effect = holder != null ? holder.value() : null;
						} else {
							effect = null;
						}
					} else {
						effect = BuiltInRegistries.MOB_EFFECT.get(data.effectID());
					}
					if (effect != null) {
						bookBuilder.effect(() -> new MobEffectInstance(effect, data.effectDuration()), 1.0F);
					}
				}
			}
			properties = bookBuilder.build();
		}
		return properties;
	}
}
