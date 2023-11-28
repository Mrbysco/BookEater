package com.mrbysco.bookeater.datagen.client;

import com.mrbysco.bookeater.BookEater;
import com.mrbysco.bookeater.registry.ModRegistry;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.BlockModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;

public class BookBlockModels extends BlockModelProvider {
	public BookBlockModels(PackOutput packOutput, ExistingFileHelper helper) {
		super(packOutput, BookEater.MOD_ID, helper);
	}

	@Override
	protected void registerModels() {
		crossBlock(ModRegistry.THORNS_BLOCK.get(), modLoc("block/thorns"));
	}


	protected void crossBlock(Block block, ResourceLocation oreTexture) {
		String path = ForgeRegistries.BLOCKS.getKey(block).getPath();
		withExistingParent(path, mcLoc("block/cross"))
				.texture("cross", oreTexture).renderType("cutout");
	}
}
