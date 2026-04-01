package com.theokapi.datagen;

import com.theokapi.Origins;
import com.theokapi.item.OriginsItems;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.minecraft.core.HolderLookup;
import org.jspecify.annotations.NonNull;

import java.util.concurrent.CompletableFuture;

public class ItemTagProvider extends FabricTagsProvider.ItemTagsProvider {
    public ItemTagProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registryLookupFuture) {
        super(output, registryLookupFuture);
    }

    @Override
    protected void addTags(HolderLookup.@NonNull Provider provider) {
        valueLookupBuilder(Origins.ORBS)
                .add(OriginsItems.BLAZEBORN_ORB)
                .add(OriginsItems.AVIAN_ORB)
                .add(OriginsItems.ENDERIAN_ORB)
                .add(OriginsItems.ELYTRIAN_ORB)
                .add(OriginsItems.MERLING_ORB)
                .add(OriginsItems.PHANTOM_ORB)
                .add(OriginsItems.ARACHNID_ORB)
                .add(OriginsItems.FELINE_ORB)
                .add(OriginsItems.SHULK_ORB)
                .add(OriginsItems.BREEZEBORN_ORB)
                .setReplace(true);
    }
}
