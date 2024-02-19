package com.mrbysco.bookeater.registry;

import com.mrbysco.bookeater.BookEater;
import com.mrbysco.bookeater.block.ThornsBlock;
import com.mrbysco.bookeater.blockentity.ThornsBlockEntity;
import com.mrbysco.bookeater.effect.AdrenalineRushEffect;
import com.mrbysco.bookeater.effect.AquaAffinityEffect;
import com.mrbysco.bookeater.effect.CustomEffect;
import com.mrbysco.bookeater.effect.DepthStriderEffect;
import com.mrbysco.bookeater.effect.DrizzleEffect;
import com.mrbysco.bookeater.effect.HerdingHarmonyEffect;
import com.mrbysco.bookeater.effect.KnockingEffect;
import com.mrbysco.bookeater.effect.LifeMendingEffect;
import com.mrbysco.bookeater.effect.LootingEffect;
import com.mrbysco.bookeater.effect.ProjectileRepelEffect;
import com.mrbysco.bookeater.effect.ResistanceEffect;
import com.mrbysco.bookeater.effect.ShiftingEffect;
import com.mrbysco.bookeater.effect.ThornsEffect;
import com.mrbysco.bookeater.effect.WaterWalkingEffect;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModRegistry {
	public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(BookEater.MOD_ID);
	public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITY_TYPES = DeferredRegister.create(BuiltInRegistries.BLOCK_ENTITY_TYPE, BookEater.MOD_ID);
	public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(BookEater.MOD_ID);
	public static final DeferredRegister<MobEffect> MOB_EFFECTS = DeferredRegister.create(BuiltInRegistries.MOB_EFFECT, BookEater.MOD_ID);

	/**
	 * Effects
	 */
	public static final DeferredHolder<MobEffect, ResistanceEffect> BLAST_RESISTANCE = MOB_EFFECTS.register("blast_resistance", () ->
			new ResistanceEffect(0x0FFed6240));
	public static final DeferredHolder<MobEffect, ResistanceEffect> PROJECTILE_RESISTANCE = MOB_EFFECTS.register("projectile_resistance", () ->
			new ResistanceEffect(10044730));
	public static final DeferredHolder<MobEffect, ProjectileRepelEffect> PROJECTILE_REPELLENT = MOB_EFFECTS.register("projectile_repellent", () ->
			new ProjectileRepelEffect(10044730));
	public static final DeferredHolder<MobEffect, AquaAffinityEffect> AQUA_AFFINITY = MOB_EFFECTS.register("aqua_affinity", () ->
			new AquaAffinityEffect(0x0FF00FFFF));
	public static final DeferredHolder<MobEffect, LifeMendingEffect> LIFE_MENDING = MOB_EFFECTS.register("life_mending", () ->
			new LifeMendingEffect(0x0FFc59500));
	public static final DeferredHolder<MobEffect, CustomEffect> WEED_WACKER = MOB_EFFECTS.register("weed_wacker", () ->
			new CustomEffect(MobEffectCategory.BENEFICIAL, 0x0FF499b4a));
	public static final DeferredHolder<MobEffect, ThornsEffect> THORNS = MOB_EFFECTS.register("thorns", () ->
			new ThornsEffect(0x0FF852121));
	public static final DeferredHolder<MobEffect, KnockingEffect> KNOCKING = MOB_EFFECTS.register("knocking", () ->
			new KnockingEffect(10044730));
	public static final DeferredHolder<MobEffect, CustomEffect> BOUNCY = MOB_EFFECTS.register("bouncy", () ->
			new CustomEffect(MobEffectCategory.NEUTRAL, 0x0FF65ff00));
	public static final DeferredHolder<MobEffect, ShiftingEffect> SHIFTING = MOB_EFFECTS.register("shifting", () ->
			new ShiftingEffect(10044730));
	public static final DeferredHolder<MobEffect, DrizzleEffect> DRIZZLE = MOB_EFFECTS.register("drizzle", () ->
			new DrizzleEffect(0x0FFC4D3DF));
	public static final DeferredHolder<MobEffect, DepthStriderEffect> DEPTH_STRIDER = MOB_EFFECTS.register("depth_strider", () ->
			new DepthStriderEffect(0x0FFC4D3DF));
	public static final DeferredHolder<MobEffect, WaterWalkingEffect> WATER_WALKING = MOB_EFFECTS.register("water_walking", () ->
			new WaterWalkingEffect(0x0FF00FFFF));
	public static final DeferredHolder<MobEffect, CustomEffect> BANE_OF_ARTHROPODS = MOB_EFFECTS.register("bane_of_arthropods", () ->
			new CustomEffect(MobEffectCategory.BENEFICIAL, 10044730));
	public static final DeferredHolder<MobEffect, LootingEffect> LOOTER = MOB_EFFECTS.register("looter", () ->
			new LootingEffect(10044730));
	public static final DeferredHolder<MobEffect, CustomEffect> SMITE = MOB_EFFECTS.register("smite", () ->
			new CustomEffect(MobEffectCategory.BENEFICIAL, 10044730));
	public static final DeferredHolder<MobEffect, CustomEffect> BOUND = MOB_EFFECTS.register("bound", () ->
			new CustomEffect(MobEffectCategory.BENEFICIAL, 0x0FF5e1c9e));
	public static final DeferredHolder<MobEffect, MobEffect> ADRENALINE_RUSH = MOB_EFFECTS.register("adrenaline_rush", () ->
			new AdrenalineRushEffect(0x0FFffff94));
	public static final DeferredHolder<MobEffect, CustomEffect> BLAZE_AURA = MOB_EFFECTS.register("blaze_aura", () ->
			new CustomEffect(MobEffectCategory.BENEFICIAL, 0x0FFfa8c4f));
	public static final DeferredHolder<MobEffect, HerdingHarmonyEffect> HERDING_HARMONY = MOB_EFFECTS.register("herding_harmony", () ->
			new HerdingHarmonyEffect(0x0FF91d956));
	public static final DeferredHolder<MobEffect, CustomEffect> BACKSTABBING = MOB_EFFECTS.register("backstabbing", () ->
			new CustomEffect(MobEffectCategory.BENEFICIAL, 0x0FF03a9e8));
	public static final DeferredHolder<MobEffect, CustomEffect> EXQUISITE_TOUCH = MOB_EFFECTS.register("exquisite_touch", () ->
			new CustomEffect(MobEffectCategory.BENEFICIAL, 0x0FF3339da));
	public static final DeferredHolder<MobEffect, CustomEffect> STURDY_DEFENSES = MOB_EFFECTS.register("sturdy_defenses", () ->
			new CustomEffect(MobEffectCategory.BENEFICIAL, 0x0FF48bd71));

	/**
	 * Blocks
	 */
	public static final DeferredBlock<ThornsBlock> THORNS_BLOCK = BLOCKS.register("thorns", () ->
			new ThornsBlock(BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).pushReaction(PushReaction.DESTROY).noCollission().noLootTable().instabreak()));

	/**
	 * Block Entities
	 */
	public static final Supplier<BlockEntityType<ThornsBlockEntity>> THORNS_BLOCK_ENTITY = BLOCK_ENTITY_TYPES.register("thorns", () -> BlockEntityType.Builder.of(
			ThornsBlockEntity::new, ModRegistry.THORNS_BLOCK.get()).build(null));

	/**
	 * Items
	 */
	public static final Supplier<BlockItem> THORNS_ITEM = ITEMS.registerSimpleBlockItem(THORNS_BLOCK);
}
