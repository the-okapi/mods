package com.theokapi.datagen;

import com.theokapi.block.TradingBlocks;
import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.model.TexturedModel;
import org.jspecify.annotations.NonNull;

public class ModelProvider extends FabricModelProvider {
    public ModelProvider(FabricPackOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockModelGenerators blockModelGenerators) {
        blockModelGenerators.createTrivialBlock(TradingBlocks.TRADING_TABLE, TexturedModel.CUBE_TOP_BOTTOM);
        blockModelGenerators.createTrivialBlock(TradingBlocks.BANKING_TABLE, TexturedModel.CUBE_TOP_BOTTOM);
    }

    @Override
    public void generateItemModels(@NonNull ItemModelGenerators itemModelGenerators) {

    }

    @Override
    public @NonNull String getName() {
        return "ModelProvider";
    }
}
