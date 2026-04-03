package com.theokapi.datagen;

import com.theokapi.item.OriginsItems;
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
    public void generateItemModels(ItemModelGenerators itemModelGenerators) {
        itemModelGenerators.generateFlatItem(OriginsItems.BLAZEBORN_ORB, ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(OriginsItems.AVIAN_ORB, ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(OriginsItems.ENDERIAN_ORB, ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(OriginsItems.MERLING_ORB, ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(OriginsItems.WARDEN_ORB, ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(OriginsItems.ELYTRIAN_ORB, ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(OriginsItems.ARACHNID_ORB, ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(OriginsItems.FELINE_ORB, ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(OriginsItems.SHULK_ORB, ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(OriginsItems.REVERSE_ORB, ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(OriginsItems.BREEZEBORN_ORB, ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(OriginsItems.GOLEM_ORB, ModelTemplates.FLAT_ITEM);
    }
}
