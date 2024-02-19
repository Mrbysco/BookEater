package com.mrbysco.bookeater;

import com.mojang.logging.LogUtils;
import com.mrbysco.bookeater.hander.DamageHandler;
import com.mrbysco.bookeater.hander.DimensionHandler;
import com.mrbysco.bookeater.hander.KnockHandler;
import com.mrbysco.bookeater.hander.MendingHandler;
import com.mrbysco.bookeater.hander.SneakHandler;
import com.mrbysco.bookeater.hander.TargetHandler;
import com.mrbysco.bookeater.hander.WackerHandler;
import com.mrbysco.bookeater.registry.ModBookEffects;
import com.mrbysco.bookeater.registry.ModRegistry;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.loading.FMLEnvironment;
import net.neoforged.neoforge.common.NeoForge;
import org.slf4j.Logger;

@Mod(BookEater.MOD_ID)
public class BookEater {
	public static final String MOD_ID = "bookeater";
	public static final Logger LOGGER = LogUtils.getLogger();

	public BookEater(IEventBus eventBus) {
		eventBus.addListener(ModBookEffects::onNewRegistry);

		ModRegistry.BLOCKS.register(eventBus);
		ModRegistry.BLOCK_ENTITY_TYPES.register(eventBus);
		ModRegistry.ITEMS.register(eventBus);
		ModRegistry.MOB_EFFECTS.register(eventBus);

		NeoForge.EVENT_BUS.register(new DamageHandler());
		NeoForge.EVENT_BUS.register(new DimensionHandler());
		NeoForge.EVENT_BUS.register(new KnockHandler());
		NeoForge.EVENT_BUS.register(new MendingHandler());
		NeoForge.EVENT_BUS.register(new TargetHandler());
		NeoForge.EVENT_BUS.register(new WackerHandler());

		if (FMLEnvironment.dist.isClient()) {
			NeoForge.EVENT_BUS.register(new SneakHandler());
		}
	}
}
