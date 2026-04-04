package com.theokapi.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jspecify.annotations.NonNull;

public class CheeseWheelBlock extends Block {
    public CheeseWheelBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected @NonNull VoxelShape getBlockSupportShape(@NonNull BlockState state, @NonNull BlockGetter level, @NonNull BlockPos pos) {
        return Block.box(1, 0, 1, 15, 6, 15);
    }

    @Override
    protected @NonNull VoxelShape getShape(@NonNull BlockState state, @NonNull BlockGetter level, @NonNull BlockPos pos, @NonNull CollisionContext context) {
        return this.getBlockSupportShape(state, level, pos);
    }

    @Override
    protected @NonNull VoxelShape getCollisionShape(@NonNull BlockState state, @NonNull BlockGetter level, @NonNull BlockPos pos, @NonNull CollisionContext context) {
        return this.getBlockSupportShape(state, level, pos);
    }
}
