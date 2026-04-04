package com.theokapi.datagen;

import com.theokapi.block.MoneyBlocks;
import com.theokapi.item.MoneyItems;
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
                // $1
                shapeless(RecipeCategory.MISC, MoneyItems.ONE_DOLLAR, 5)
                        .requires(MoneyItems.FIVE_DOLLARS)
                        .unlockedBy(getHasName(MoneyItems.FIVE_DOLLARS), has(MoneyItems.FIVE_DOLLARS))
                        .group("one_dollar")
                        .save(recipeOutput, "one_from_five");

                shapeless(RecipeCategory.MISC, MoneyItems.ONE_DOLLAR, 10)
                        .requires(MoneyItems.TEN_DOLLARS)
                        .unlockedBy(getHasName(MoneyItems.TEN_DOLLARS), has(MoneyItems.TEN_DOLLARS))
                        .group("one_dollar")
                        .save(recipeOutput, "one_from_ten");

                shapeless(RecipeCategory.MISC, MoneyItems.ONE_DOLLAR, 20)
                        .requires(MoneyItems.TWENTY_DOLLARS)
                        .unlockedBy(getHasName(MoneyItems.TWENTY_DOLLARS), has(MoneyItems.TWENTY_DOLLARS))
                        .group("one_dollar")
                        .save(recipeOutput, "one_from_twenty");

                shapeless(RecipeCategory.MISC, MoneyItems.ONE_DOLLAR, 50)
                        .requires(MoneyItems.FIFTY_DOLLARS)
                        .unlockedBy(getHasName(MoneyItems.FIFTY_DOLLARS), has(MoneyItems.FIFTY_DOLLARS))
                        .group("one_dollar")
                        .save(recipeOutput, "one_from_fifty");

                // $5
                shapeless(RecipeCategory.MISC, MoneyItems.FIVE_DOLLARS)
                        .requires(MoneyItems.ONE_DOLLAR, 5)
                        .unlockedBy(getHasName(MoneyItems.ONE_DOLLAR), has(MoneyItems.ONE_DOLLAR))
                        .group("five_dollars")
                        .save(recipeOutput, "five_from_one");

                shapeless(RecipeCategory.MISC, MoneyItems.FIVE_DOLLARS, 2)
                        .requires(MoneyItems.TEN_DOLLARS)
                        .unlockedBy(getHasName(MoneyItems.TEN_DOLLARS), has(MoneyItems.TEN_DOLLARS))
                        .group("five_dollars")
                        .save(recipeOutput, "five_from_ten");

                shapeless(RecipeCategory.MISC, MoneyItems.FIVE_DOLLARS, 4)
                        .requires(MoneyItems.TWENTY_DOLLARS)
                        .unlockedBy(getHasName(MoneyItems.TWENTY_DOLLARS), has(MoneyItems.TWENTY_DOLLARS))
                        .group("five_dollars")
                        .save(recipeOutput, "five_from_twenty");

                shapeless(RecipeCategory.MISC, MoneyItems.FIVE_DOLLARS, 10)
                        .requires(MoneyItems.FIFTY_DOLLARS)
                        .unlockedBy(getHasName(MoneyItems.FIFTY_DOLLARS), has(MoneyItems.FIFTY_DOLLARS))
                        .group("five_dollars")
                        .save(recipeOutput, "five_from_fifty");

                shapeless(RecipeCategory.MISC, MoneyItems.FIVE_DOLLARS, 20)
                        .requires(MoneyItems.ONE_HUNDRED_DOLLARS)
                        .unlockedBy(getHasName(MoneyItems.ONE_HUNDRED_DOLLARS), has(MoneyItems.ONE_HUNDRED_DOLLARS))
                        .group("five_dollars")
                        .save(recipeOutput, "five_from_one_hundred");

                // $10
                shapeless(RecipeCategory.MISC, MoneyItems.TEN_DOLLARS)
                        .requires(MoneyItems.FIVE_DOLLARS, 2)
                        .unlockedBy(getHasName(MoneyItems.FIVE_DOLLARS), has(MoneyItems.FIVE_DOLLARS))
                        .group("ten_dollars")
                        .save(recipeOutput, "ten_from_five");

                shapeless(RecipeCategory.MISC, MoneyItems.TEN_DOLLARS, 2)
                        .requires(MoneyItems.TWENTY_DOLLARS)
                        .unlockedBy(getHasName(MoneyItems.TWENTY_DOLLARS), has(MoneyItems.TWENTY_DOLLARS))
                        .group("ten_dollars")
                        .save(recipeOutput, "ten_from_twenty");

                shapeless(RecipeCategory.MISC, MoneyItems.TEN_DOLLARS, 5)
                        .requires(MoneyItems.FIFTY_DOLLARS)
                        .unlockedBy(getHasName(MoneyItems.FIFTY_DOLLARS), has(MoneyItems.FIFTY_DOLLARS))
                        .group("ten_dollars")
                        .save(recipeOutput, "ten_from_fifty");

                shapeless(RecipeCategory.MISC, MoneyItems.TEN_DOLLARS, 10)
                        .requires(MoneyItems.ONE_HUNDRED_DOLLARS)
                        .unlockedBy(getHasName(MoneyItems.ONE_HUNDRED_DOLLARS), has(MoneyItems.ONE_HUNDRED_DOLLARS))
                        .group("ten_dollars")
                        .save(recipeOutput, "ten_from_one_hundred");

                // $20
                shapeless(RecipeCategory.MISC, MoneyItems.TWENTY_DOLLARS)
                        .requires(MoneyItems.FIVE_DOLLARS, 4)
                        .unlockedBy(getHasName(MoneyItems.FIVE_DOLLARS), has(MoneyItems.FIVE_DOLLARS))
                        .group("twenty_dollars")
                        .save(recipeOutput, "twenty_from_five");

                shapeless(RecipeCategory.MISC, MoneyItems.TWENTY_DOLLARS)
                        .requires(MoneyItems.TEN_DOLLARS, 2)
                        .unlockedBy(getHasName(MoneyItems.TEN_DOLLARS), has(MoneyItems.TEN_DOLLARS))
                        .group("twenty_dollars")
                        .save(recipeOutput, "twenty_from_ten");

                shapeless(RecipeCategory.MISC, MoneyItems.TWENTY_DOLLARS, 5)
                        .requires(MoneyItems.ONE_HUNDRED_DOLLARS)
                        .unlockedBy(getHasName(MoneyItems.ONE_HUNDRED_DOLLARS), has(MoneyItems.ONE_HUNDRED_DOLLARS))
                        .group("twenty_dollars")
                        .save(recipeOutput, "twenty_from_one_hundred");

                // $50
                shapeless(RecipeCategory.MISC, MoneyItems.FIFTY_DOLLARS)
                        .requires(MoneyItems.TEN_DOLLARS, 5)
                        .unlockedBy(getHasName(MoneyItems.TEN_DOLLARS), has(MoneyItems.TEN_DOLLARS))
                        .group("fifty_dollars")
                        .save(recipeOutput, "fifty_from_ten");

                shapeless(RecipeCategory.MISC, MoneyItems.FIFTY_DOLLARS, 2)
                        .requires(MoneyItems.ONE_HUNDRED_DOLLARS)
                        .unlockedBy(getHasName(MoneyItems.ONE_HUNDRED_DOLLARS), has(MoneyItems.ONE_HUNDRED_DOLLARS))
                        .group("fifty_dollars")
                        .save(recipeOutput, "fifty_from_one_hundred");

                // $100
                shapeless(RecipeCategory.MISC, MoneyItems.ONE_HUNDRED_DOLLARS)
                        .requires(MoneyItems.TWENTY_DOLLARS, 5)
                        .unlockedBy(getHasName(MoneyItems.TWENTY_DOLLARS), has(MoneyItems.TWENTY_DOLLARS))
                        .group("one_hundred_dollars")
                        .save(recipeOutput, "one_hundred_from_twenty");

                shapeless(RecipeCategory.MISC, MoneyItems.ONE_HUNDRED_DOLLARS)
                        .requires(MoneyItems.FIFTY_DOLLARS, 2)
                        .unlockedBy(getHasName(MoneyItems.FIFTY_DOLLARS), has(MoneyItems.FIFTY_DOLLARS))
                        .group("one_hundred_dollars")
                        .save(recipeOutput, "one_hundred_from_fifty");
                // End money conversion recipes

                shaped(RecipeCategory.REDSTONE, MoneyBlocks.ATM)
                        .pattern("iri")
                        .pattern("igi")
                        .pattern("iri")
                        .define('i', Items.IRON_INGOT)
                        .define('r', Items.REDSTONE)
                        .define('g', Items.GOLD_INGOT)
                        .unlockedBy(getHasName(Items.IRON_INGOT), has(Items.IRON_INGOT))
                        .unlockedBy(getHasName(Items.REDSTONE), has(Items.REDSTONE))
                        .unlockedBy(getHasName(Items.GOLD_INGOT), has(Items.GOLD_INGOT))
                        .save(recipeOutput);

                shapeless(RecipeCategory.FOOD, MoneyItems.CHEESE, 2)
                        .requires(Items.MILK_BUCKET)
                        .unlockedBy(getHasName(Items.MILK_BUCKET), has(Items.MILK_BUCKET))
                        .save(recipeOutput, "make_cheese");

                shaped(RecipeCategory.FOOD, MoneyBlocks.CHEESE_WHEEL)
                        .pattern("cc")
                        .pattern("cc")
                        .define('c', MoneyItems.CHEESE)
                        .unlockedBy(getHasName(MoneyItems.CHEESE), has(MoneyItems.CHEESE))
                        .save(recipeOutput);

                shapeless(RecipeCategory.FOOD, MoneyItems.CHEESE, 4)
                        .requires(MoneyBlocks.CHEESE_WHEEL)
                        .unlockedBy(getHasName(MoneyBlocks.CHEESE_WHEEL), has(MoneyBlocks.CHEESE_WHEEL))
                        .save(recipeOutput, "cheese_from_wheel");
            }
        };
    }

    @Override
    public @NonNull String getName() {
        return "RecipeProvider";
    }
}
