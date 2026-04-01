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
        translationBuilder.add("item.origins.blazeborn_orb", "Blazeborn Orb");
        translationBuilder.add("item.origins.avian_orb", "Avian Orb");
        translationBuilder.add("item.origins.enderian_orb", "Enderian Orb");
        translationBuilder.add("item.origins.merling_orb", "Merling Orb");
        translationBuilder.add("item.origins.phantom_orb", "Phantom Orb");
        translationBuilder.add("item.origins.elytrian_orb", "Elytrian Orb");
        translationBuilder.add("item.origins.arachnid_orb", "Arachnid Orb");
        translationBuilder.add("item.origins.feline_orb", "Feline Orb");
        translationBuilder.add("item.origins.shulk_orb", "Shulk Orb");
        translationBuilder.add("item.origins.reverse_orb", "Reverse Orb");
        translationBuilder.add("item.origins.breezeborn_orb", "Breezeborn Orb");

        translationBuilder.add("tag.item.origins.orbs", "Orbs");
        translationBuilder.add("tag.item.origins.elytrian_not_allowed", "Elytrian Not Allowed");
        translationBuilder.add("tag.item.origins.foods", "Foods");
    }
}
