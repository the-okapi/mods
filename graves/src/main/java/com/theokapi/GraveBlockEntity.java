package com.theokapi;

import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import org.jspecify.annotations.NonNull;

import java.util.Optional;

public class GraveBlockEntity extends BlockEntity {
    public GraveBlockEntity(BlockPos worldPosition, BlockState blockState) {
        super(Graves.GRAVE_BLOCK_ENTITY, worldPosition, blockState);
    }

    private final NonNullList<ItemStack> items = NonNullList.create();

    public void addItem(ItemStack item) {
        if (item != ItemStack.EMPTY) {
            Graves.LOGGER.info(item.toString());
            items.add(item);
            Graves.LOGGER.info(items.toString());
        }
        setChanged();
    }

    public NonNullList<ItemStack> getItems() {
        return items;
    }

    @Override
    protected void saveAdditional(@NonNull ValueOutput output) {
        for (int i = 0; i < items.size(); i++) {
            output.store("item"+i, ItemStack.CODEC, items.get(i));
        }

        super.saveAdditional(output);
    }

    @Override
    protected void loadAdditional(@NonNull ValueInput input) {
        super.loadAdditional(input);
        for (int i = 0; i < 41; i++) {
            Optional<ItemStack> item = input.read("item"+i, ItemStack.CODEC);
            item.ifPresent(items::add);
        }
    }
}
