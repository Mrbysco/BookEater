package com.mrbysco.bookeater.datagen.server;

import com.mrbysco.bookeater.BookEater;
import com.mrbysco.bookeater.api.BookData;
import com.mrbysco.bookeater.api.BookEffectProvider;
import com.mrbysco.bookeater.registry.ModRegistry;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.enchantment.Enchantments;

public class BookDataProvider extends BookEffectProvider {

	public BookDataProvider(PackOutput packOutput) {
		super(packOutput, BookEater.MOD_ID);
	}

	@Override
	protected void start() {
		addEffect("protection", new BookData.Builder(Enchantments.ALL_DAMAGE_PROTECTION).effect(MobEffects.DAMAGE_RESISTANCE).build());
		addEffect("fire_protection", new BookData.Builder(Enchantments.FIRE_PROTECTION).effect(MobEffects.FIRE_RESISTANCE).build());
		addEffect("feather_falling", new BookData.Builder(Enchantments.FALL_PROTECTION).effect(MobEffects.SLOW_FALLING).build());
		addEffect("blast_protection", new BookData.Builder(Enchantments.BLAST_PROTECTION).effect(ModRegistry.BLAST_RESISTANCE.getId()).build());
		addEffect("projectile_protection", new BookData.Builder(Enchantments.PROJECTILE_PROTECTION).tagEffect(modLoc("projectile_protection")).build());
		addEffect("respiration", new BookData.Builder(Enchantments.RESPIRATION).effect(MobEffects.WATER_BREATHING).build());
		addEffect("aqua_affinity", new BookData.Builder(Enchantments.AQUA_AFFINITY).effect(ModRegistry.AQUA_AFFINITY.getId()).build());
		addEffect("thorns", new BookData.Builder(Enchantments.THORNS).effect(ModRegistry.THORNS.getId()).build());
		addEffect("depth_strider", new BookData.Builder(Enchantments.DEPTH_STRIDER).effect(ModRegistry.DEPTH_STRIDER.getId()).build());
		addEffect("frost_walker", new BookData.Builder(Enchantments.FROST_WALKER).effect(ModRegistry.WATER_WALKING.getId()).build());
		addEffect("binding_curse", new BookData.Builder(Enchantments.BINDING_CURSE).effect(ModRegistry.BOUND.getId()).build());
		addEffect("soul_speed", new BookData.Builder(Enchantments.SOUL_SPEED).effect(ModRegistry.ADRENALINE_RUSH.getId()).build()); //Faster the lower your health
		addEffect("swift_sneak", new BookData.Builder(Enchantments.SWIFT_SNEAK).effect(ModRegistry.SHIFTING.getId()).build());
		addEffect("sharpness", new BookData.Builder(Enchantments.SHARPNESS).effect(MobEffects.DAMAGE_BOOST).build());
		addEffect("smite", new BookData.Builder(Enchantments.SMITE).effect(ModRegistry.SMITE.getId()).build());
		addEffect("bane_of_arthropods", new BookData.Builder(Enchantments.BANE_OF_ARTHROPODS).effect(ModRegistry.BANE_OF_ARTHROPODS.getId()).build());
		addEffect("knockback", new BookData.Builder(Enchantments.KNOCKBACK).tagEffect(modLoc("knockback")).build());
		addEffect("fire_aspect", new BookData.Builder(Enchantments.FIRE_ASPECT).effect(ModRegistry.BLAZE_AURA.getId()).build());
		addEffect("mob_looting", new BookData.Builder(Enchantments.MOB_LOOTING).effect(ModRegistry.LOOTER.getId()).build());
		addEffect("sweeping_edge", new BookData.Builder(Enchantments.SWEEPING_EDGE).effect(ModRegistry.WEED_WACKER.getId()).build());
		addEffect("efficiency", new BookData.Builder(Enchantments.BLOCK_EFFICIENCY).effect(MobEffects.DIG_SPEED).build());
		addEffect("silk_touch", new BookData.Builder(Enchantments.SILK_TOUCH).effect(ModRegistry.EXQUISITE_TOUCH.getId()).build());
		addEffect("unbreaking", new BookData.Builder(Enchantments.UNBREAKING).effect(ModRegistry.STURDY_DEFENSES.getId()).build());
//		addEffect("block_fortune", new BookData.Builder(Enchantments.BLOCK_FORTUNE));
//		addEffect("power_arrows", new BookData.Builder(Enchantments.POWER_ARROWS));
//		addEffect("punch_arrows", new BookData.Builder(Enchantments.PUNCH_ARROWS));
//		addEffect("flaming_arrows", new BookData.Builder(Enchantments.FLAMING_ARROWS));
//		addEffect("infinity_arrows", new BookData.Builder(Enchantments.INFINITY_ARROWS));
//		addEffect("fishing_luck", new BookData.Builder(Enchantments.FISHING_LUCK));
//		addEffect("fishing_speed", new BookData.Builder(Enchantments.FISHING_SPEED));
		addEffect("loyalty", new BookData.Builder(Enchantments.LOYALTY).effect(ModRegistry.HERDING_HARMONY.getId()).build());
		addEffect("impaling", new BookData.Builder(Enchantments.IMPALING).effect(ModRegistry.BACKSTABBING.getId()).build());
		addEffect("riptide", new BookData.Builder(Enchantments.RIPTIDE).effect(ModRegistry.DRIZZLE.getId()).build());
//		addEffect("channeling", new BookData.Builder(Enchantments.CHANNELING));
//		addEffect("multishot", new BookData.Builder(Enchantments.MULTISHOT));
//		addEffect("quick_charge", new BookData.Builder(Enchantments.QUICK_CHARGE));
//		addEffect("piercing", new BookData.Builder(Enchantments.PIERCING));
		addEffect("mending", new BookData.Builder(Enchantments.MENDING).effect(ModRegistry.LIFE_MENDING.getId()).build());
//		addEffect("VANISHING_CURSE", new BookData.Builder(Enchantments.VANISHING_CURSE));
	}

	private ResourceLocation modLoc(String name) {
		return new ResourceLocation(BookEater.MOD_ID, name);
	}
}
