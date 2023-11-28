package com.mrbysco.bookeater.registry;

import net.minecraft.world.food.FoodProperties;

public class ModFoods {
	public static final FoodProperties BOOK = (new FoodProperties.Builder()).nutrition(1).saturationMod(0.1F).alwaysEat().build();
}
