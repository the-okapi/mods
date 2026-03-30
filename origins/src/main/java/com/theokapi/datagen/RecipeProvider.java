package com.theokapi.datagen;

import com.theokapi.item.OriginsItems;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
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
                shaped(RecipeCategory.TOOLS, OriginsItems.BLAZEBORN_ORB)
                        .pattern("ebe")
                        .pattern("bgb")
                        .pattern("ebe")
                        .define('b', Items.BLAZE_POWDER)
                        .define('e', Items.ENDER_EYE)
                        .define('g', Items.ENCHANTED_GOLDEN_APPLE)
                        .unlockedBy(getHasName(Items.BLAZE_POWDER), has(Items.BLAZE_POWDER))
                        .unlockedBy(getHasName(Items.ENDER_EYE), has(Items.ENDER_EYE))
                        .unlockedBy(getHasName(Items.ENCHANTED_GOLDEN_APPLE), has(Items.ENCHANTED_GOLDEN_APPLE))
                        .save(recipeOutput);

                shaped(RecipeCategory.TOOLS, OriginsItems.AVIAN_ORB)
                        .pattern("efe")
                        .pattern("fgf")
                        .pattern("efe")
                        .define('f', Items.FEATHER)
                        .define('e', Items.ENDER_EYE)
                        .define('g', Items.ENCHANTED_GOLDEN_APPLE)
                        .unlockedBy(getHasName(Items.FEATHER), has(Items.FEATHER))
                        .unlockedBy(getHasName(Items.ENDER_EYE), has(Items.ENDER_EYE))
                        .unlockedBy(getHasName(Items.ENCHANTED_GOLDEN_APPLE), has(Items.ENCHANTED_GOLDEN_APPLE))
                        .save(recipeOutput);

                shaped(RecipeCategory.TOOLS, OriginsItems.ENDERIAN_ORB)
                        .pattern("epe")
                        .pattern("pgp")
                        .pattern("epe")
                        .define('p', Items.ENDER_PEARL)
                        .define('e', Items.ENDER_EYE)
                        .define('g', Items.ENCHANTED_GOLDEN_APPLE)
                        .unlockedBy(getHasName(Items.ENDER_PEARL), has(Items.ENDER_PEARL))
                        .unlockedBy(getHasName(Items.ENDER_EYE), has(Items.ENDER_EYE))
                        .unlockedBy(getHasName(Items.ENCHANTED_GOLDEN_APPLE), has(Items.ENCHANTED_GOLDEN_APPLE))
                        .save(recipeOutput);
            }
        };
    }

    @Override
    public @NonNull String getName() {
        return "RecipeProvider";
    }
}
