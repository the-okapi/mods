package com.theokapi.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.core.HolderLookup;
import org.jspecify.annotations.NonNull;

import java.util.concurrent.CompletableFuture;

public class LangProvider extends FabricLanguageProvider {
    public LangProvider(FabricPackOutput packOutput, CompletableFuture<HolderLookup.Provider> registryLookup) {
        super(packOutput, "en_us", registryLookup);
    }

    @Override
    public void generateTranslations(HolderLookup.@NonNull Provider provider, TranslationBuilder translationBuilder) {
        translationBuilder.add("block.trading.trading_table", "Trading Table");
        translationBuilder.add("block.trading.banking_table", "Banking Table");

        translationBuilder.add("item.trading.one_dollar", "One Dollar");
        translationBuilder.add("item.trading.two_dollars", "Two Dollars");
        translationBuilder.add("item.trading.five_dollars", "Five Dollars");
        translationBuilder.add("item.trading.ten_dollars", "Ten Dollars");
        translationBuilder.add("item.trading.twenty_dollars", "Twenty Dollars");
        translationBuilder.add("item.trading.fifty_dollars", "Fifty Dollars");
        translationBuilder.add("item.trading.one_hundred_dollars", "One Hundred Dollars");
    }
}
