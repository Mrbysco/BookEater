package com.mrbysco.bookeater.util;

import com.mrbysco.bookeater.api.BookData;
import com.mrbysco.bookeater.data.BookEffectManager;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.tags.ITagManager;
import org.jetbrains.annotations.Nullable;

import java.util.List;

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
						ITagManager<MobEffect> tagManager = ForgeRegistries.MOB_EFFECTS.tags();
						if (tagManager != null && tagManager.isKnownTagName(effectTagKey)) {
							effect = tagManager.getTag(effectTagKey).getRandomElement(entity.getRandom()).orElse(null);
						} else {
							effect = null;
						}
					} else {
						effect = ForgeRegistries.MOB_EFFECTS.getValue(data.effectID());
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
