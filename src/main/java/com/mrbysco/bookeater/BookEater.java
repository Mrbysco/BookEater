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
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

@Mod(BookEater.MOD_ID)
public class BookEater {
	public static final String MOD_ID = "bookeater";
	public static final Logger LOGGER = LogUtils.getLogger();

	public BookEater() {
		IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();

		eventBus.addListener(ModBookEffects::onNewRegistry);

		ModRegistry.BLOCKS.register(eventBus);
		ModRegistry.BLOCK_ENTITY_TYPES.register(eventBus);
		ModRegistry.ITEMS.register(eventBus);
		ModRegistry.MINECRAFT_ITEMS.register(eventBus);
		ModRegistry.MOB_EFFECTS.register(eventBus);

		MinecraftForge.EVENT_BUS.register(new DamageHandler());
		MinecraftForge.EVENT_BUS.register(new DimensionHandler());
		MinecraftForge.EVENT_BUS.register(new KnockHandler());
		MinecraftForge.EVENT_BUS.register(new MendingHandler());
		MinecraftForge.EVENT_BUS.register(new TargetHandler());
		MinecraftForge.EVENT_BUS.register(new WackerHandler());

		DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> {
			MinecraftForge.EVENT_BUS.register(new SneakHandler());
		});
	}
}
