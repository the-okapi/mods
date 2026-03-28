package com.theokapi.block;

import com.theokapi.Trading;
import com.theokapi.block.bankingtable.BankingTableBlockEntity;
import com.theokapi.block.tradingtable.TradingTableBlockEntity;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;

public class TradingBlockEntities {
    private static <T extends BlockEntity> BlockEntityType<T> register(
            String name,
            FabricBlockEntityTypeBuilder.Factory<? extends T> blockEntityTypeBuilder,
            Block... blocks
    ) {
        return Registry.register(
                BuiltInRegistries.BLOCK_ENTITY_TYPE,
                Identifier.fromNamespaceAndPath(Trading.MOD_ID, name),
                FabricBlockEntityTypeBuilder.<T>create(blockEntityTypeBuilder, blocks).build());
    }

    public static final BlockEntityType<TradingTableBlockEntity> TRADING_TABLE_BLOCK_ENTITY =
            register("trading_table", TradingTableBlockEntity::new, TradingBlocks.TRADING_TABLE);

    public static final BlockEntityType<BankingTableBlockEntity> BANKING_TABLE_BLOCK_ENTITY =
            register("banking_table", BankingTableBlockEntity::new, TradingBlocks.BANKING_TABLE);

    public static void init() {
        Trading.LOGGER.info("Initializing Block Entities");
    }
}
