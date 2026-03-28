package com.theokapi.block.tradingtable;

import com.theokapi.block.TradingBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class TradingTableBlockEntity extends BlockEntity {
    public TradingTableBlockEntity(BlockPos worldPosition, BlockState blockState) {
        super(TradingBlockEntities.TRADING_TABLE_BLOCK_ENTITY, worldPosition, blockState);
    }
}
