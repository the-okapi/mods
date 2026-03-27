package com.theokapi;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

public class GraveBlock extends BaseEntityBlock {
    protected GraveBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected @NonNull MapCodec<? extends BaseEntityBlock> codec() {
        return simpleCodec(GraveBlock::new);
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(@NonNull BlockPos worldPosition, @NonNull BlockState blockState) {
        return new GraveBlockEntity(worldPosition, blockState);
    }

    @Override
    protected @NonNull VoxelShape getBlockSupportShape(@NonNull BlockState state, @NonNull BlockGetter level, @NonNull BlockPos pos) {
        return Block.box(1, 0, 3, 15, 15, 8);
    }

    @Override
    protected @NonNull VoxelShape getShape(@NonNull BlockState state, @NonNull BlockGetter level, @NonNull BlockPos pos, @NonNull CollisionContext context) {
        return this.getBlockSupportShape(state, level, pos);
    }

    @Override
    protected @NonNull VoxelShape getCollisionShape(@NonNull BlockState state, @NonNull BlockGetter level, @NonNull BlockPos pos, @NonNull CollisionContext context) {
        return this.getBlockSupportShape(state, level, pos);
    }

    @Override
    protected @NonNull InteractionResult useWithoutItem(@NonNull BlockState state, Level level, @NonNull BlockPos pos, @NonNull Player player, @NonNull BlockHitResult hitResult) {
        BlockEntity blockEntity = level.getBlockEntity(pos);

        if (!(blockEntity instanceof GraveBlockEntity graveBlockEntity)) {
            return InteractionResult.FAIL;
        }

        NonNullList<ItemStack> items = graveBlockEntity.getItems();

        for (ItemStack item : items) {
            player.addItem(item);
        }

        level.setBlock(pos, Blocks.AIR.defaultBlockState(), 1);

        player.playSound(SoundEvents.ITEM_PICKUP);

        return InteractionResult.SUCCESS;
    }
}
