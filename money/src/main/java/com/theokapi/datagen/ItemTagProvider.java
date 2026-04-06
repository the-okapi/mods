package com.theokapi.datagen;

import com.theokapi.item.MoneyItems;
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
    protected void addTags(HolderLookup.@NonNull Provider registries) {
        valueLookupBuilder(MoneyItems.MONEY)
                .add(MoneyItems.ONE_DOLLAR)
                .add(MoneyItems.FIVE_DOLLARS)
                .add(MoneyItems.TEN_DOLLARS)
                .add(MoneyItems.TWENTY_DOLLARS)
                .add(MoneyItems.FIFTY_DOLLARS)
                .add(MoneyItems.ONE_HUNDRED_DOLLARS)
                .setReplace(true);
    }
}
