package com.mrbysco.bookeater.effect;

import com.mrbysco.bookeater.blockentity.ThornsBlockEntity;
import com.mrbysco.bookeater.registry.ModRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class ThornsEffect extends CustomEffect {
	public ThornsEffect(int color) {
		super(MobEffectCategory.BENEFICIAL, color);
	}

	@Override
	public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) {
		return true;
	}

	@Override
	public void applyEffectTick(LivingEntity livingEntity, int amplifier) {
		Level level = livingEntity.getCommandSenderWorld();
		BlockPos pos = livingEntity.blockPosition();
		if (!level.isClientSide && livingEntity.tickCount % 20 == 0 && livingEntity.getRandom().nextDouble() <= 0.4) {
			BlockState thornState = ModRegistry.THORNS_BLOCK.get().defaultBlockState();
			BlockState oldState = level.getBlockState(pos);
			BlockEntity blockEntity = level.getBlockEntity(pos);
			CompoundTag tag = blockEntity != null ? blockEntity.saveWithFullMetadata() : null;
			if (thornState.canSurvive(level, pos) && oldState.canBeReplaced()) {
				level.setBlockAndUpdate(pos, thornState);
				ThornsBlockEntity thornsBlockEntity = new ThornsBlockEntity(pos, thornState);
				thornsBlockEntity.storeBlock(oldState, tag);
				level.removeBlockEntity(pos);
				level.setBlockEntity(thornsBlockEntity);
			}
		}
	}
}
