package com.theokapi.datagen;

import com.theokapi.Origins;
import com.theokapi.item.OriginsItems;
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
                shaped(RecipeCategory.MISC, OriginsItems.BLAZEBORN_ORB)
                        .pattern("ebe")
                        .pattern("bgb")
                        .pattern("ebe")
                        .define('e', Items.ENDER_EYE)
                        .define('g', Items.ENCHANTED_GOLDEN_APPLE)
                        .define('b', Items.BLAZE_ROD)
                        .unlockedBy(getHasName(Items.ENDER_EYE), has(Items.ENDER_EYE))
                        .unlockedBy(getHasName(Items.ENCHANTED_GOLDEN_APPLE), has(Items.ENCHANTED_GOLDEN_APPLE))
                        .unlockedBy(getHasName(Items.BLAZE_ROD), has(Items.BLAZE_ROD))
                        .group("blazeborn_orb")
                        .save(recipeOutput, "blazeborn_orb_crafting");

                shapeless(RecipeCategory.MISC, OriginsItems.BLAZEBORN_ORB)
                        .requires(Origins.ORBS)
                        .requires(Items.BLAZE_ROD, 4)
                        .unlockedBy(getHasName(Items.BLAZE_ROD), has(Items.BLAZE_ROD))
                        .group("blazeborn_orb")
                        .save(recipeOutput, "blazeborn_orb_converting");

                shaped(RecipeCategory.MISC, OriginsItems.AVIAN_ORB)
                        .pattern("ebe")
                        .pattern("bgb")
                        .pattern("ebe")
                        .define('e', Items.ENDER_EYE)
                        .define('g', Items.ENCHANTED_GOLDEN_APPLE)
                        .define('b', Items.FEATHER)
                        .unlockedBy(getHasName(Items.ENDER_EYE), has(Items.ENDER_EYE))
                        .unlockedBy(getHasName(Items.ENCHANTED_GOLDEN_APPLE), has(Items.ENCHANTED_GOLDEN_APPLE))
                        .unlockedBy(getHasName(Items.FEATHER), has(Items.FEATHER))
                        .group("avian_orb")
                        .save(recipeOutput, "avian_orb_crafting");

                shapeless(RecipeCategory.MISC, OriginsItems.AVIAN_ORB)
                        .requires(Origins.ORBS)
                        .requires(Items.FEATHER, 4)
                        .unlockedBy(getHasName(Items.FEATHER), has(Items.FEATHER))
                        .group("avian_orb")
                        .save(recipeOutput, "avian_orb_converting");

                shaped(RecipeCategory.MISC, OriginsItems.ENDERIAN_ORB)
                        .pattern("ebe")
                        .pattern("bgb")
                        .pattern("ebe")
                        .define('e', Items.ENDER_EYE)
                        .define('g', Items.ENCHANTED_GOLDEN_APPLE)
                        .define('b', Items.ENDER_PEARL)
                        .unlockedBy(getHasName(Items.ENDER_EYE), has(Items.ENDER_EYE))
                        .unlockedBy(getHasName(Items.ENCHANTED_GOLDEN_APPLE), has(Items.ENCHANTED_GOLDEN_APPLE))
                        .unlockedBy(getHasName(Items.ENDER_PEARL), has(Items.ENDER_PEARL))
                        .group("enderian_orb")
                        .save(recipeOutput, "enderian_orb_crafting");

                shapeless(RecipeCategory.MISC, OriginsItems.ENDERIAN_ORB)
                        .requires(Origins.ORBS)
                        .requires(Items.ENDER_PEARL, 4)
                        .unlockedBy(getHasName(Items.ENDER_PEARL), has(Items.ENDER_PEARL))
                        .group("enderian_orb")
                        .save(recipeOutput, "enderian_orb_converting");

                shaped(RecipeCategory.MISC, OriginsItems.MERLING_ORB)
                        .pattern("ebe")
                        .pattern("bgb")
                        .pattern("ebe")
                        .define('e', Items.ENDER_EYE)
                        .define('g', Items.ENCHANTED_GOLDEN_APPLE)
                        .define('b', ItemTags.FISHES)
                        .unlockedBy(getHasName(Items.ENDER_EYE), has(Items.ENDER_EYE))
                        .unlockedBy(getHasName(Items.ENCHANTED_GOLDEN_APPLE), has(Items.ENCHANTED_GOLDEN_APPLE))
                        .unlockedBy(ItemTags.FISHES.toString(), has(ItemTags.FISHES))
                        .group("merling_orb")
                        .save(recipeOutput, "merling_orb_crafting");

                shapeless(RecipeCategory.MISC, OriginsItems.MERLING_ORB)
                        .requires(Origins.ORBS)
                        .requires(ItemTags.FISHES)
                        .requires(ItemTags.FISHES)
                        .requires(ItemTags.FISHES)
                        .requires(ItemTags.FISHES)
                        .unlockedBy(ItemTags.FISHES.toString(), has(ItemTags.FISHES))
                        .group("merling_orb")
                        .save(recipeOutput, "merling_orb_converting");

                shaped(RecipeCategory.MISC, OriginsItems.WARDEN_ORB)
                        .pattern("ebe")
                        .pattern("bgb")
                        .pattern("ebe")
                        .define('e', Items.ENDER_EYE)
                        .define('g', Items.ENCHANTED_GOLDEN_APPLE)
                        .define('b', Items.SCULK)
                        .unlockedBy(getHasName(Items.ENDER_EYE), has(Items.ENDER_EYE))
                        .unlockedBy(getHasName(Items.ENCHANTED_GOLDEN_APPLE), has(Items.ENCHANTED_GOLDEN_APPLE))
                        .unlockedBy(getHasName(Items.SCULK), has(Items.SCULK))
                        .group("warden_orb")
                        .save(recipeOutput, "warden_orb_crafting");

                shapeless(RecipeCategory.MISC, OriginsItems.WARDEN_ORB)
                        .requires(Origins.ORBS)
                        .requires(Items.SCULK, 4)
                        .unlockedBy(getHasName(Items.SCULK), has(Items.SCULK))
                        .group("warden_orb")
                        .save(recipeOutput, "warden_orb_converting");

                shaped(RecipeCategory.MISC, OriginsItems.ELYTRIAN_ORB)
                        .pattern("ebe")
                        .pattern("bgb")
                        .pattern("ebe")
                        .define('e', Items.ENDER_EYE)
                        .define('g', Items.ENCHANTED_GOLDEN_APPLE)
                        .define('b', Items.DRAGON_HEAD)
                        .unlockedBy(getHasName(Items.ENDER_EYE), has(Items.ENDER_EYE))
                        .unlockedBy(getHasName(Items.ENCHANTED_GOLDEN_APPLE), has(Items.ENCHANTED_GOLDEN_APPLE))
                        .unlockedBy(getHasName(Items.DRAGON_HEAD), has(Items.DRAGON_HEAD))
                        .group("elytrian_orb")
                        .save(recipeOutput, "elytrian_orb_crafting");

                shapeless(RecipeCategory.MISC, OriginsItems.ELYTRIAN_ORB)
                        .requires(Origins.ORBS)
                        .requires(Items.DRAGON_HEAD, 4)
                        .unlockedBy(getHasName(Items.DRAGON_HEAD), has(Items.DRAGON_HEAD))
                        .group("elytrian_orb")
                        .save(recipeOutput, "elytrian_orb_converting");

                shaped(RecipeCategory.MISC, OriginsItems.ARACHNID_ORB)
                        .pattern("ebe")
                        .pattern("bgb")
                        .pattern("ebe")
                        .define('e', Items.ENDER_EYE)
                        .define('g', Items.ENCHANTED_GOLDEN_APPLE)
                        .define('b', Items.SPIDER_EYE)
                        .unlockedBy(getHasName(Items.ENDER_EYE), has(Items.ENDER_EYE))
                        .unlockedBy(getHasName(Items.ENCHANTED_GOLDEN_APPLE), has(Items.ENCHANTED_GOLDEN_APPLE))
                        .unlockedBy(getHasName(Items.SPIDER_EYE), has(Items.SPIDER_EYE))
                        .group("arachnid_orb")
                        .save(recipeOutput, "arachnid_orb_crafting");

                shapeless(RecipeCategory.MISC, OriginsItems.ARACHNID_ORB)
                        .requires(Origins.ORBS)
                        .requires(Items.SPIDER_EYE, 4)
                        .unlockedBy(getHasName(Items.SPIDER_EYE), has(Items.SPIDER_EYE))
                        .group("arachnid_orb")
                        .save(recipeOutput, "arachnid_orb_converting");

                shaped(RecipeCategory.MISC, OriginsItems.FELINE_ORB)
                        .pattern("ebe")
                        .pattern("bgb")
                        .pattern("ebe")
                        .define('e', Items.ENDER_EYE)
                        .define('g', Items.ENCHANTED_GOLDEN_APPLE)
                        .define('b', Items.STRING)
                        .unlockedBy(getHasName(Items.ENDER_EYE), has(Items.ENDER_EYE))
                        .unlockedBy(getHasName(Items.ENCHANTED_GOLDEN_APPLE), has(Items.ENCHANTED_GOLDEN_APPLE))
                        .unlockedBy(getHasName(Items.STRING), has(Items.STRING))
                        .group("feline_orb")
                        .save(recipeOutput, "feline_orb_crafting");

                shapeless(RecipeCategory.MISC, OriginsItems.FELINE_ORB)
                        .requires(Origins.ORBS)
                        .requires(Items.STRING, 4)
                        .unlockedBy(getHasName(Items.STRING), has(Items.STRING))
                        .group("feline_orb")
                        .save(recipeOutput, "feline_orb_converting");

                shaped(RecipeCategory.MISC, OriginsItems.SHULK_ORB)
                        .pattern("ebe")
                        .pattern("bgb")
                        .pattern("ebe")
                        .define('e', Items.ENDER_EYE)
                        .define('g', Items.ENCHANTED_GOLDEN_APPLE)
                        .define('b', Items.SHULKER_SHELL)
                        .unlockedBy(getHasName(Items.ENDER_EYE), has(Items.ENDER_EYE))
                        .unlockedBy(getHasName(Items.ENCHANTED_GOLDEN_APPLE), has(Items.ENCHANTED_GOLDEN_APPLE))
                        .unlockedBy(getHasName(Items.SHULKER_SHELL), has(Items.SHULKER_SHELL))
                        .group("shulk_orb")
                        .save(recipeOutput, "shulk_orb_crafting");

                shapeless(RecipeCategory.MISC, OriginsItems.SHULK_ORB)
                        .requires(Origins.ORBS)
                        .requires(Items.SHULKER_SHELL, 4)
                        .unlockedBy(getHasName(Items.SHULKER_SHELL), has(Items.SHULKER_SHELL))
                        .group("shulk_orb")
                        .save(recipeOutput, "shulk_orb_converting");

                shaped(RecipeCategory.MISC, OriginsItems.BREEZEBORN_ORB)
                        .pattern("ebe")
                        .pattern("bgb")
                        .pattern("ebe")
                        .define('e', Items.ENDER_EYE)
                        .define('g', Items.ENCHANTED_GOLDEN_APPLE)
                        .define('b', Items.BREEZE_ROD)
                        .unlockedBy(getHasName(Items.ENDER_EYE), has(Items.ENDER_EYE))
                        .unlockedBy(getHasName(Items.ENCHANTED_GOLDEN_APPLE), has(Items.ENCHANTED_GOLDEN_APPLE))
                        .unlockedBy(getHasName(Items.BREEZE_ROD), has(Items.BREEZE_ROD))
                        .group("breezeborn_orb")
                        .save(recipeOutput, "breezeborn_orb_crafting");

                shapeless(RecipeCategory.MISC, OriginsItems.BREEZEBORN_ORB)
                        .requires(Origins.ORBS)
                        .requires(Items.BREEZE_ROD, 4)
                        .unlockedBy(getHasName(Items.BREEZE_ROD), has(Items.BREEZE_ROD))
                        .group("breezeborn_orb")
                        .save(recipeOutput, "breezeborn_orb_converting");
            }
        };
    }

    @Override
    public @NonNull String getName() {
        return "RecipeProvider";
    }
}
