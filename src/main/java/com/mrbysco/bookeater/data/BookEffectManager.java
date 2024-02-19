package com.mrbysco.bookeater.data;

import com.mrbysco.bookeater.BookEater;
import com.mrbysco.bookeater.api.BookData;
import net.minecraft.core.Registry;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.EnchantedBookItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.event.OnDatapackSyncEvent;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Mod.EventBusSubscriber(modid = BookEater.MOD_ID)
public class BookEffectManager {
	private static final Map<ResourceLocation, BookData> MAP = new LinkedHashMap<>();

	private static Collection<BookData> getValues() {
		return MAP.values();
	}

	@Nullable
	public static List<BookData> dataFromBook(ItemStack stack) {
		if (!(stack.getItem() instanceof EnchantedBookItem)) return null;

		List<BookData> dataList = new ArrayList<>();
		Map<Enchantment, Integer> enchantments = EnchantmentHelper.getEnchantments(stack);
		for (Map.Entry<Enchantment, Integer> entry : enchantments.entrySet()) {
			ResourceLocation enchantmentID = BuiltInRegistries.ENCHANTMENT.getKey(entry.getKey());
			getValues().stream().filter(bookData -> bookData.enchantmentID().equals(enchantmentID))
					.findFirst().ifPresent(dataList::add);
		}

		return dataList;
	}

	@SubscribeEvent
	public static void onTagsUpdated(OnDatapackSyncEvent event) {
		final RegistryAccess registryAccess = event.getPlayerList().getServer().registryAccess();

		MAP.clear();
		final Registry<BookData> biomePlaceRegistry = registryAccess.registryOrThrow(BookData.REGISTRY_KEY);
		biomePlaceRegistry.entrySet().forEach((key) -> MAP.put(key.getKey().location(), key.getValue()));
		BookEater.LOGGER.info("Loaded Book Effects: " + MAP.size() + " effects");
	}
}
