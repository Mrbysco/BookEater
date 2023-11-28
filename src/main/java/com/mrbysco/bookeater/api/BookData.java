package com.mrbysco.bookeater.api;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.mrbysco.bookeater.BookEater;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.ExtraCodecs;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraftforge.registries.ForgeRegistries;

public record BookData(ResourceLocation enchantmentID, int enchantmentLevel, boolean useEnchantmentTag,
					   ResourceLocation effectID, int effectLevel, int effectDuration, boolean useEffectTag) {
	public static final ResourceKey<Registry<BookData>> REGISTRY_KEY = ResourceKey.createRegistryKey(
			new ResourceLocation(BookEater.MOD_ID, "book_effects"));

	public static final Codec<BookData> DIRECT_CODEC = ExtraCodecs.catchDecoderException(
			RecordCodecBuilder.create(
					apply -> apply.group(
									ResourceLocation.CODEC.fieldOf("enchantmentID").forGetter(BookData::enchantmentID),
									Codec.INT.optionalFieldOf("enchantmentLevel", -1).forGetter(BookData::enchantmentLevel),
									Codec.BOOL.optionalFieldOf("useEnchantmentTag", false).forGetter(BookData::useEnchantmentTag),


									ResourceLocation.CODEC.fieldOf("effectID").forGetter(BookData::effectID),
									Codec.INT.optionalFieldOf("effectLevel", -1).forGetter(BookData::effectLevel),
									Codec.INT.optionalFieldOf("effectDuration", 6000).forGetter(BookData::effectDuration),
									Codec.BOOL.optionalFieldOf("useEffectTag", false).forGetter(BookData::useEffectTag)
							)
							.apply(apply, BookData::new)
			)
	);

	public static class Builder {
		private final ResourceLocation enchantmentID;
		private int enchantmentLevel = -1;
		private boolean useEnchantmentTag = false;

		private ResourceLocation effectID;
		private int effectLevel = -1;
		private int effectDuration = 6000;
		private boolean useEffectTag = false;

		public Builder(ResourceLocation enchantmentID) {
			this.enchantmentID = enchantmentID;
		}

		public Builder(Enchantment enchantment) {
			this.enchantmentID = ForgeRegistries.ENCHANTMENTS.getKey(enchantment);
		}

		public Builder setEnchantmentLevel(int enchantmentLevel) {
			this.enchantmentLevel = enchantmentLevel;
			return this;
		}

		public Builder setUseEnchantmentTag(boolean useEnchantmentTag) {
			this.useEnchantmentTag = useEnchantmentTag;
			return this;
		}

		public Builder tagEffect(ResourceLocation effectID) {
			this.effectID = effectID;
			this.useEffectTag = true;
			return this;
		}

		public Builder effect(ResourceLocation effectID) {
			this.effectID = effectID;
			return this;
		}

		public Builder effect(MobEffect effect) {
			this.effectID = ForgeRegistries.MOB_EFFECTS.getKey(effect);
			return this;
		}

		public Builder setEffectLevel(int effectLevel) {
			this.effectLevel = effectLevel;
			return this;
		}

		public Builder setEffectDuration(int effectDuration) {
			this.effectDuration = effectDuration;
			return this;
		}

		public BookData build() {
			return new BookData(enchantmentID, enchantmentLevel, useEnchantmentTag, effectID, effectLevel, effectDuration, useEffectTag);
		}
	}
}
