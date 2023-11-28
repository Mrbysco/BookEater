package com.mrbysco.bookeater.blockentity;

import com.mrbysco.bookeater.registry.ModRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtUtils;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

public class ThornsBlockEntity extends BlockEntity {
	private int timer;

	private BlockState storedState;
	private CompoundTag storedBlockTag;

	public ThornsBlockEntity(BlockEntityType<?> blockEntityType, BlockPos pos, BlockState state) {
		super(blockEntityType, pos, state);
		this.timer = 100;
	}

	public ThornsBlockEntity(BlockPos pos, BlockState state) {
		this(ModRegistry.THORNS_BLOCK_ENTITY.get(), pos, state);
	}

	@Override
	protected void saveAdditional(CompoundTag tag) {
		super.saveAdditional(tag);
		tag.putInt("timer", timer);
		if (storedState != null) {
			tag.put("storedBlockState", NbtUtils.writeBlockState(storedState));
		}
		if (storedBlockTag != null) {
			tag.put("storedBlockTag", storedBlockTag);
		}
	}

	@Override
	public void load(CompoundTag tag) {
		super.load(tag);
		this.timer = tag.getInt("timer");
		if (tag.contains("storedBlockState", 10)) {
			storedState = NbtUtils.readBlockState(BuiltInRegistries.BLOCK.asLookup(), tag.getCompound("storedBlockState"));
			if (storedState.isAir()) {
				storedState = null;
			}
		}
		if (tag.contains("storedBlockTag", 10)) {
			storedBlockTag = tag.getCompound("storedBlockTag");
		}
	}

	public static void serverTick(Level level, BlockPos pos, BlockState state, ThornsBlockEntity thornsBE) {
		if (thornsBE.timer-- <= 0) {
			thornsBE.restoreBlock();
		}
	}

	public void storeBlock(BlockState state, @Nullable CompoundTag tag) {
		this.storedState = state;
		this.storedBlockTag = tag;
	}

	public void restoreBlock() {
		if (storedState != null) {
			BlockPos pos = getBlockPos();
			if (level != null) {
				level.setBlockAndUpdate(pos, storedState);
				if (storedBlockTag != null) {
					BlockEntity storedEntity = BlockEntity.loadStatic(pos, storedState, storedBlockTag);
					if (storedEntity != null)
						level.setBlockEntity(storedEntity);
				}
			}
		}
	}
}
