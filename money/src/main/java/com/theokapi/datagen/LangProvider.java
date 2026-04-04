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
    public void generateTranslations(HolderLookup.@NonNull Provider provider, @NonNull TranslationBuilder translationBuilder) {
        translationBuilder.add("item.money.one_dollar", "One Dollar");
        translationBuilder.add("item.money.five_dollars", "Five Dollars");
        translationBuilder.add("item.money.ten_dollars", "Ten Dollars");
        translationBuilder.add("item.money.twenty_dollars", "Twenty Dollars");
        translationBuilder.add("item.money.fifty_dollars", "Fifty Dollars");
        translationBuilder.add("item.money.one_hundred_dollars", "One Hundred Dollars");

        translationBuilder.add("item.money.cheese", "Cheese");
        translationBuilder.add("block.money.cheese_wheel", "Cheese Wheel");

        translationBuilder.add("block.money.atm", "ATM");
    }
}
