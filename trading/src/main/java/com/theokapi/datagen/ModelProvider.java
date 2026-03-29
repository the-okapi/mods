package com.theokapi.datagen;

import com.theokapi.block.TradingBlocks;
import com.theokapi.item.TradingItems;
import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.client.data.models.model.TexturedModel;
import org.jspecify.annotations.NonNull;

public class ModelProvider extends FabricModelProvider {
    public ModelProvider(FabricPackOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockModelGenerators blockModelGenerators) {
        blockModelGenerators.createTrivialBlock(TradingBlocks.TRADING_TABLE, TexturedModel.CUBE_TOP_BOTTOM);
        blockModelGenerators.createTrivialBlock(TradingBlocks.ATM, TexturedModel.CUBE_TOP_BOTTOM);
    }

    @Override
    public void generateItemModels(@NonNull ItemModelGenerators itemModelGenerators) {
        itemModelGenerators.generateFlatItem(TradingItems.ONE_DOLLAR, ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(TradingItems.TWO_DOLLARS, ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(TradingItems.FIVE_DOLLARS, ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(TradingItems.TEN_DOLLARS, ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(TradingItems.TWENTY_DOLLARS, ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(TradingItems.FIFTY_DOLLARS, ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(TradingItems.ONE_HUNDRED_DOLLARS, ModelTemplates.FLAT_ITEM);
    }

    @Override
    public @NonNull String getName() {
        return "ModelProvider";
    }
}
