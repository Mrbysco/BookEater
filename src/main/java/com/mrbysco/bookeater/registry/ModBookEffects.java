package com.mrbysco.bookeater.registry;

import com.mrbysco.bookeater.BookEater;
import com.mrbysco.bookeater.api.BookData;
import net.minecraftforge.registries.DataPackRegistryEvent;

public class ModBookEffects {
	public static void onNewRegistry(DataPackRegistryEvent.NewRegistry event) {
		event.dataPackRegistry(BookData.REGISTRY_KEY,
				BookData.DIRECT_CODEC, BookData.DIRECT_CODEC);
		BookEater.LOGGER.info("Registered Book Effect Registry");
	}
}
