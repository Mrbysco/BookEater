package com.mrbysco.bookeater.api;

import com.google.common.collect.ImmutableList;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.mojang.serialization.JsonOps;
import com.mrbysco.bookeater.BookEater;
import cpw.mods.modlauncher.api.LamdbaExceptionUtils;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.crafting.conditions.ICondition;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.nio.file.Path;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

public abstract class BookEffectProvider implements DataProvider {
	private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
	private static final Logger LOGGER = LogManager.getLogger();
	private final PackOutput output;
	private final String modid;
	private final Map<String, JsonElement> toSerialize = new HashMap<>();

	public BookEffectProvider(PackOutput packOutput, String modid) {
		this.output = packOutput;
		this.modid = modid;
	}

	public CompletableFuture<?> run(CachedOutput cache) {
		start();

		ImmutableList.Builder<CompletableFuture<?>> futuresBuilder = new ImmutableList.Builder<>();

		Path path = this.output.getOutputFolder(PackOutput.Target.DATA_PACK).resolve(this.modid).resolve(BookEater.MOD_ID).resolve("book_effects");
		toSerialize.forEach(LamdbaExceptionUtils.rethrowBiConsumer((name, json) -> {
			Path modifierPath = path.resolve(name + ".json");
			futuresBuilder.add(DataProvider.saveStable(cache, json, modifierPath));
		}));

		return CompletableFuture.allOf(futuresBuilder.build().toArray(CompletableFuture[]::new));
	}

	protected abstract void start();

	public <T extends BookData> void addEffect(String path, T instance, List<ICondition> conditions) {
		JsonElement json = BookData.DIRECT_CODEC.encodeStart(JsonOps.INSTANCE, instance).getOrThrow(false, s -> {
		});
		this.toSerialize.put(path, json);
	}

	public <T extends BookData> void addEffect(String path, T instance, ICondition... conditions) {
		addEffect(path, instance, Arrays.asList(conditions));
	}

	@Override
	public String getName()
	{
		return "Book Effects : " + modid;
	}
}
