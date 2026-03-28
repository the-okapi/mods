package com.theokapi.block.bankingtable;

import com.theokapi.block.TradingBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class BankingTableBlockEntity extends BlockEntity {
    public BankingTableBlockEntity(BlockPos worldPosition, BlockState blockState) {
        super(TradingBlockEntities.BANKING_TABLE_BLOCK_ENTITY, worldPosition, blockState);
    }
}
