package com.mrbysco.bookeater.datagen.server;

import com.mrbysco.bookeater.BookEater;
import com.mrbysco.bookeater.registry.ModRegistry;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.effect.MobEffect;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class MobEffectTagProvider extends TagsProvider<MobEffect> {
	public MobEffectTagProvider(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
		super(packOutput, Registries.MOB_EFFECT, lookupProvider, BookEater.MOD_ID, existingFileHelper);
	}

	public static final TagKey<MobEffect> PROJECTILE_PROTECTION = create(modLoc("projectile_protection"));
	public static final TagKey<MobEffect> KNOCKBACK = create(modLoc("knockback"));

	@Override
	protected void addTags(HolderLookup.Provider provider) {
		this.tag(PROJECTILE_PROTECTION).add(ModRegistry.PROJECTILE_REPELLENT.getKey(), ModRegistry.PROJECTILE_RESISTANCE.getKey());
		this.tag(KNOCKBACK).add(ModRegistry.KNOCKING.getKey(), ModRegistry.BOUNCY.getKey());
	}

	private static TagKey<MobEffect> create(ResourceLocation id) {
		return TagKey.create(Registries.MOB_EFFECT, id);
	}

	private static ResourceLocation modLoc(String path) {
		return new ResourceLocation(BookEater.MOD_ID, path);
	}
}
