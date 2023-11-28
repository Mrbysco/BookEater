package com.mrbysco.bookeater.datagen.client;

import com.mrbysco.bookeater.BookEater;
import com.mrbysco.bookeater.registry.ModRegistry;
import net.minecraft.data.PackOutput;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class BookBlockStates extends BlockStateProvider {

	public BookBlockStates(PackOutput packOutput, ExistingFileHelper helper) {
		super(packOutput, BookEater.MOD_ID, helper);
	}

	@Override
	protected void registerStatesAndModels() {
		simpleBlock(ModRegistry.THORNS_BLOCK.get(), models().getExistingFile(modLoc("block/thorns")));
	}
}