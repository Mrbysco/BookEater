package com.mrbysco.bookeater.datagen.client;

import com.mrbysco.bookeater.BookEater;
import com.mrbysco.bookeater.registry.ModRegistry;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.client.model.generators.BlockModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

public class BookBlockModels extends BlockModelProvider {
	public BookBlockModels(PackOutput packOutput, ExistingFileHelper helper) {
		super(packOutput, BookEater.MOD_ID, helper);
	}

	@Override
	protected void registerModels() {
		crossBlock(ModRegistry.THORNS_BLOCK.getId(), modLoc("block/thorns"));
	}


	protected void crossBlock(ResourceLocation blockLocation, ResourceLocation oreTexture) {
		String path = blockLocation.getPath();
		withExistingParent(path, mcLoc("block/cross"))
				.texture("cross", oreTexture).renderType("cutout");
	}
}
