package com.mrbysco.bookeater.datagen;

import com.mrbysco.bookeater.datagen.client.BookBlockModels;
import com.mrbysco.bookeater.datagen.client.BookBlockStates;
import com.mrbysco.bookeater.datagen.client.BookItemModels;
import com.mrbysco.bookeater.datagen.client.BookLanguageProvider;
import com.mrbysco.bookeater.datagen.server.BookDataProvider;
import com.mrbysco.bookeater.datagen.server.MobEffectTagProvider;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class BookDatagen {

	@SubscribeEvent
	public static void gatherData(GatherDataEvent event) {
		DataGenerator generator = event.getGenerator();
		PackOutput packOutput = generator.getPackOutput();
		ExistingFileHelper existingFileHelper = event.getExistingFileHelper();

		if (event.includeServer()) {
			generator.addProvider(event.includeServer(), new BookDataProvider(packOutput));
			generator.addProvider(event.includeServer(), new MobEffectTagProvider(packOutput, event.getLookupProvider(), existingFileHelper));
		}
		if (event.includeClient()) {
			generator.addProvider(event.includeClient(), new BookBlockModels(packOutput, existingFileHelper));
			generator.addProvider(event.includeClient(), new BookItemModels(packOutput, existingFileHelper));
			generator.addProvider(event.includeClient(), new BookBlockStates(packOutput, existingFileHelper));
			generator.addProvider(event.includeClient(), new BookLanguageProvider(packOutput));
		}
	}
}
