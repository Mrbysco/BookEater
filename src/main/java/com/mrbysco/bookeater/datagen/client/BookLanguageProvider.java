package com.mrbysco.bookeater.datagen.client;

import com.mrbysco.bookeater.BookEater;
import com.mrbysco.bookeater.registry.ModRegistry;
import net.minecraft.data.PackOutput;
import net.minecraft.world.effect.MobEffect;
import net.minecraftforge.common.data.LanguageProvider;

import java.util.function.Supplier;

public class BookLanguageProvider extends LanguageProvider {

	public BookLanguageProvider(PackOutput packOutput) {
		super(packOutput, BookEater.MOD_ID, "en_us");
	}

	@Override
	protected void addTranslations() {
		addEffect(ModRegistry.BLAST_RESISTANCE, "Blast Resistance");
		addEffectDescription(ModRegistry.BLAST_RESISTANCE, "Reduces damage from explosions.");

		addEffect(ModRegistry.PROJECTILE_RESISTANCE, "Projectile Resistance");
		addEffectDescription(ModRegistry.PROJECTILE_RESISTANCE, "Reduces damage from projectiles.");

		addEffect(ModRegistry.PROJECTILE_REPELLENT, "Projectile Repellent");
		addEffectDescription(ModRegistry.PROJECTILE_REPELLENT, "Has a chance to repel incoming projectiles.");

		addEffect(ModRegistry.AQUA_AFFINITY, "Aqua Affinity");
		addEffectDescription(ModRegistry.AQUA_AFFINITY, "Allows the user to use tools underwater.");

		addEffect(ModRegistry.LIFE_MENDING, "Life Mending");
		addEffectDescription(ModRegistry.LIFE_MENDING, "Regenerates health when picking up experience.");

		addEffect(ModRegistry.WEED_WACKER, "Weed Wacker");
		addEffectDescription(ModRegistry.WEED_WACKER, "Allows the user to hit mobs through weeds.");

		addEffect(ModRegistry.THORNS, "Thorns");
		addEffectDescription(ModRegistry.THORNS, "Plants temporary thorns around the user.");
		addBlock(ModRegistry.THORNS_BLOCK, "Thorns");

		addEffect(ModRegistry.KNOCKING, "Knocking");
		addEffectDescription(ModRegistry.KNOCKING, "The knocker becomes the knocked.");

		addEffect(ModRegistry.BOUNCY, "Bouncing");
		addEffectDescription(ModRegistry.BOUNCY, "The user gets infused with a bouncy ball.");

		addEffect(ModRegistry.SHIFTING, "Shifting");
		addEffectDescription(ModRegistry.SHIFTING, "The user is forced to sneak hastily.");

		addEffect(ModRegistry.DRIZZLE, "Drizzle");
		addEffectDescription(ModRegistry.DRIZZLE, "The user gains additional speed while wet.");

		addEffect(ModRegistry.DEPTH_STRIDER, "Depth Strider");
		addEffectDescription(ModRegistry.DEPTH_STRIDER, "The user gains additional speed the lower they get in the world.");

		addEffect(ModRegistry.WATER_WALKING, "Water Walking");
		addEffectDescription(ModRegistry.WATER_WALKING, "The user gains the ability to walk on water.");

		addEffect(ModRegistry.BANE_OF_ARTHROPODS, "Bane of Arthropods");
		addEffectDescription(ModRegistry.BANE_OF_ARTHROPODS, "The user will be invisible to arthropods.");

		addEffect(ModRegistry.LOOTER, "Looter");
		addEffectDescription(ModRegistry.LOOTER, "The user has a chance of disarming enemies when sneaking and looking at them.");

		addEffect(ModRegistry.SMITE, "Smite");
		addEffectDescription(ModRegistry.SMITE, "The user does more damage to undead mobs.");

		addEffect(ModRegistry.BOUND, "Bound");
		addEffectDescription(ModRegistry.BOUND, "The user gets bound to it's current dimension.");
		add("bookeater.bound.message", "You can't do that, you're bound to the current dimension");

		addEffect(ModRegistry.ADRENALINE_RUSH, "Adrenaline Rush");
		addEffectDescription(ModRegistry.ADRENALINE_RUSH, "The user will get faster the lower their health gets.");

		addEffect(ModRegistry.HERDING_HARMONY, "Herding Harmony");
		addEffectDescription(ModRegistry.HERDING_HARMONY, "The user will get followed by farm animals.");

		addEffect(ModRegistry.BACKSTABBING, "Backstabbing");
		addEffectDescription(ModRegistry.BACKSTABBING, "The user will do more damage if done from behind.");

		addEffect(ModRegistry.EXQUISITE_TOUCH, "Exquisite Touch");
		addEffectDescription(ModRegistry.EXQUISITE_TOUCH, "The user will get more value out of food.");

		addEffect(ModRegistry.STURDY_DEFENSES, "Sturdy Defenses");
		addEffectDescription(ModRegistry.STURDY_DEFENSES, "The user's armor will take less damage.");
	}

	private void addEffectDescription(Supplier<? extends MobEffect> key, String description) {
		add(key.get().getDescriptionId() + ".description", description);
	}
}
