package com.theokapi.datagen;

import com.theokapi.item.MoneyItems;
import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.model.ModelTemplates;
import org.jspecify.annotations.NonNull;

public class ModelProvider extends FabricModelProvider {
    public ModelProvider(FabricPackOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(@NonNull BlockModelGenerators blockModelGenerators) {

    }

    @Override
    public void generateItemModels(@NonNull ItemModelGenerators itemModelGenerators) {
        itemModelGenerators.generateFlatItem(MoneyItems.ONE_DOLLAR, ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(MoneyItems.FIVE_DOLLARS, ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(MoneyItems.TEN_DOLLARS, ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(MoneyItems.TWENTY_DOLLARS, ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(MoneyItems.FIFTY_DOLLARS, ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(MoneyItems.ONE_HUNDRED_DOLLARS, ModelTemplates.FLAT_ITEM);
    }
}
