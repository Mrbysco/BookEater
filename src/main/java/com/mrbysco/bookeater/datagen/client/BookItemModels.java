package com.mrbysco.bookeater.datagen.client;

import com.mrbysco.bookeater.BookEater;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

public class BookItemModels extends ItemModelProvider {
	public BookItemModels(PackOutput packOutput, ExistingFileHelper helper) {
		super(packOutput, BookEater.MOD_ID, helper);
	}

	@Override
	protected void registerModels() {
		singleTexture("thorns", mcLoc("item/generated"), "layer0", modLoc("block/thorns"));
	}
}