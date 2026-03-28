package com.theokapi.datagen;

import com.theokapi.block.TradingBlocks;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import org.jspecify.annotations.NonNull;

import java.util.concurrent.CompletableFuture;

public class RecipeProvider extends FabricRecipeProvider {
    public RecipeProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected net.minecraft.data.recipes.@NonNull RecipeProvider createRecipeProvider(HolderLookup.@NonNull Provider provider, @NonNull RecipeOutput recipeOutput) {
        return new net.minecraft.data.recipes.RecipeProvider(provider, recipeOutput) {
            @Override
            public void buildRecipes() {
                shaped(RecipeCategory.MISC, TradingBlocks.TRADING_TABLE)
                        .pattern("ppp")
                        .pattern("ppp")
                        .pattern("ppp")
                        .define('p', ItemTags.PLANKS)
                        .unlockedBy(ItemTags.PLANKS.toString(), has(ItemTags.PLANKS))
                        .save(recipeOutput);

                shaped(RecipeCategory.MISC, TradingBlocks.BANKING_TABLE)
                        .pattern("qqq")
                        .pattern("qgq")
                        .pattern("qqq")
                        .define('q', Items.QUARTZ)
                        .define('g', Items.GOLD_INGOT)
                        .unlockedBy(getHasName(Items.QUARTZ), has(Items.QUARTZ))
                        .unlockedBy(getHasName(Items.GOLD_INGOT), has(Items.GOLD_INGOT))
                        .save(recipeOutput);
            }
        };
    }

    @Override
    public @NonNull String getName() {
        return "RecipeProvider";
    }
}
