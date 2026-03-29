package com.theokapi.datagen;

import com.theokapi.block.TradingBlocks;
import com.theokapi.item.TradingItems;
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

                shaped(RecipeCategory.MISC, TradingBlocks.ATM)
                        .pattern("iri")
                        .pattern("igi")
                        .pattern("iri")
                        .define('i', Items.IRON_INGOT)
                        .define('g', Items.GOLD_INGOT)
                        .define('r', Items.REDSTONE)
                        .unlockedBy(getHasName(Items.IRON_INGOT), has(Items.IRON_INGOT))
                        .unlockedBy(getHasName(Items.GOLD_INGOT), has(Items.GOLD_INGOT))
                        .unlockedBy(getHasName(Items.REDSTONE), has(Items.REDSTONE))
                        .save(recipeOutput);

                // $1
                shapeless(RecipeCategory.MISC, TradingItems.ONE_DOLLAR, 2)
                        .requires(TradingItems.TWO_DOLLARS)
                        .unlockedBy(getHasName(TradingItems.TWO_DOLLARS), has(TradingItems.TWO_DOLLARS))
                        .group("one_dollar")
                        .save(recipeOutput, "one_from_two");

                shapeless(RecipeCategory.MISC, TradingItems.ONE_DOLLAR, 5)
                        .requires(TradingItems.FIVE_DOLLARS)
                        .unlockedBy(getHasName(TradingItems.FIVE_DOLLARS), has(TradingItems.FIVE_DOLLARS))
                        .group("one_dollar")
                        .save(recipeOutput, "one_from_five");

                shapeless(RecipeCategory.MISC, TradingItems.ONE_DOLLAR, 10)
                        .requires(TradingItems.TEN_DOLLARS)
                        .unlockedBy(getHasName(TradingItems.TEN_DOLLARS), has(TradingItems.TEN_DOLLARS))
                        .group("one_dollar")
                        .save(recipeOutput, "one_from_ten");

                shapeless(RecipeCategory.MISC, TradingItems.ONE_DOLLAR, 20)
                        .requires(TradingItems.TWENTY_DOLLARS)
                        .unlockedBy(getHasName(TradingItems.TWENTY_DOLLARS), has(TradingItems.TWENTY_DOLLARS))
                        .group("one_dollar")
                        .save(recipeOutput, "one_from_twenty");

                shapeless(RecipeCategory.MISC, TradingItems.ONE_DOLLAR, 50)
                        .requires(TradingItems.FIFTY_DOLLARS)
                        .unlockedBy(getHasName(TradingItems.FIFTY_DOLLARS), has(TradingItems.FIFTY_DOLLARS))
                        .group("one_dollar")
                        .save(recipeOutput, "one_from_fifty");

                // $2
                shapeless(RecipeCategory.MISC, TradingItems.TWO_DOLLARS)
                        .requires(TradingItems.ONE_DOLLAR, 2)
                        .unlockedBy(getHasName(TradingItems.ONE_DOLLAR), has(TradingItems.ONE_DOLLAR))
                        .group("two_dollars")
                        .save(recipeOutput, "two_from_one");

                shapeless(RecipeCategory.MISC, TradingItems.TWO_DOLLARS, 5)
                        .requires(TradingItems.TEN_DOLLARS)
                        .unlockedBy(getHasName(TradingItems.TEN_DOLLARS), has(TradingItems.TEN_DOLLARS))
                        .group("two_dollars")
                        .save(recipeOutput, "two_from_ten");

                shapeless(RecipeCategory.MISC, TradingItems.TWO_DOLLARS, 10)
                        .requires(TradingItems.TWENTY_DOLLARS)
                        .unlockedBy(getHasName(TradingItems.TWENTY_DOLLARS), has(TradingItems.TWENTY_DOLLARS))
                        .group("two_dollars")
                        .save(recipeOutput, "two_from_twenty");

                shapeless(RecipeCategory.MISC, TradingItems.TWO_DOLLARS, 25)
                        .requires(TradingItems.FIFTY_DOLLARS)
                        .unlockedBy(getHasName(TradingItems.FIFTY_DOLLARS), has(TradingItems.FIFTY_DOLLARS))
                        .group("two_dollars")
                        .save(recipeOutput, "two_from_fifty");

                shapeless(RecipeCategory.MISC, TradingItems.TWO_DOLLARS, 50)
                        .requires(TradingItems.ONE_HUNDRED_DOLLARS)
                        .unlockedBy(getHasName(TradingItems.ONE_HUNDRED_DOLLARS), has(TradingItems.ONE_HUNDRED_DOLLARS))
                        .group("two_dollars")
                        .save(recipeOutput, "two_from_one_hundred");

                // $5
                shapeless(RecipeCategory.MISC, TradingItems.FIVE_DOLLARS)
                        .requires(TradingItems.ONE_DOLLAR, 5)
                        .unlockedBy(getHasName(TradingItems.ONE_DOLLAR), has(TradingItems.ONE_DOLLAR))
                        .group("five_dollars")
                        .save(recipeOutput, "five_from_one");

                shapeless(RecipeCategory.MISC, TradingItems.FIVE_DOLLARS, 2)
                        .requires(TradingItems.TEN_DOLLARS)
                        .unlockedBy(getHasName(TradingItems.TEN_DOLLARS), has(TradingItems.TEN_DOLLARS))
                        .group("five_dollars")
                        .save(recipeOutput, "five_from_ten");

                shapeless(RecipeCategory.MISC, TradingItems.FIVE_DOLLARS, 4)
                        .requires(TradingItems.TWENTY_DOLLARS)
                        .unlockedBy(getHasName(TradingItems.TWENTY_DOLLARS), has(TradingItems.TWENTY_DOLLARS))
                        .group("five_dollars")
                        .save(recipeOutput, "five_from_twenty");

                shapeless(RecipeCategory.MISC, TradingItems.FIVE_DOLLARS, 10)
                        .requires(TradingItems.FIFTY_DOLLARS)
                        .unlockedBy(getHasName(TradingItems.FIFTY_DOLLARS), has(TradingItems.FIFTY_DOLLARS))
                        .group("five_dollars")
                        .save(recipeOutput, "five_from_fifty");

                shapeless(RecipeCategory.MISC, TradingItems.FIVE_DOLLARS, 20)
                        .requires(TradingItems.ONE_HUNDRED_DOLLARS)
                        .unlockedBy(getHasName(TradingItems.ONE_HUNDRED_DOLLARS), has(TradingItems.ONE_HUNDRED_DOLLARS))
                        .group("five_dollars")
                        .save(recipeOutput, "five_from_one_hundred");

                // $10
                shapeless(RecipeCategory.MISC, TradingItems.TEN_DOLLARS)
                        .requires(TradingItems.TWO_DOLLARS, 5)
                        .unlockedBy(getHasName(TradingItems.TWO_DOLLARS), has(TradingItems.TWO_DOLLARS))
                        .group("ten_dollars")
                        .save(recipeOutput, "ten_from_two");

                shapeless(RecipeCategory.MISC, TradingItems.TEN_DOLLARS)
                        .requires(TradingItems.FIVE_DOLLARS, 2)
                        .unlockedBy(getHasName(TradingItems.FIVE_DOLLARS), has(TradingItems.FIVE_DOLLARS))
                        .group("ten_dollars")
                        .save(recipeOutput, "ten_from_five");

                shapeless(RecipeCategory.MISC, TradingItems.TEN_DOLLARS, 2)
                        .requires(TradingItems.TWENTY_DOLLARS)
                        .unlockedBy(getHasName(TradingItems.TWENTY_DOLLARS), has(TradingItems.TWENTY_DOLLARS))
                        .group("ten_dollars")
                        .save(recipeOutput, "ten_from_twenty");

                shapeless(RecipeCategory.MISC, TradingItems.TEN_DOLLARS, 5)
                        .requires(TradingItems.FIFTY_DOLLARS)
                        .unlockedBy(getHasName(TradingItems.FIFTY_DOLLARS), has(TradingItems.FIFTY_DOLLARS))
                        .group("ten_dollars")
                        .save(recipeOutput, "ten_from_fifty");

                shapeless(RecipeCategory.MISC, TradingItems.TEN_DOLLARS, 10)
                        .requires(TradingItems.ONE_HUNDRED_DOLLARS)
                        .unlockedBy(getHasName(TradingItems.ONE_HUNDRED_DOLLARS), has(TradingItems.ONE_HUNDRED_DOLLARS))
                        .group("ten_dollars")
                        .save(recipeOutput, "ten_from_one_hundred");

                // $20
                shapeless(RecipeCategory.MISC, TradingItems.TWENTY_DOLLARS)
                        .requires(TradingItems.FIVE_DOLLARS, 4)
                        .unlockedBy(getHasName(TradingItems.FIVE_DOLLARS), has(TradingItems.FIVE_DOLLARS))
                        .group("twenty_dollars")
                        .save(recipeOutput, "twenty_from_five");

                shapeless(RecipeCategory.MISC, TradingItems.TWENTY_DOLLARS)
                        .requires(TradingItems.TEN_DOLLARS, 2)
                        .unlockedBy(getHasName(TradingItems.TEN_DOLLARS), has(TradingItems.TEN_DOLLARS))
                        .group("twenty_dollars")
                        .save(recipeOutput, "twenty_from_ten");

                shapeless(RecipeCategory.MISC, TradingItems.TWENTY_DOLLARS, 5)
                        .requires(TradingItems.ONE_HUNDRED_DOLLARS)
                        .unlockedBy(getHasName(TradingItems.ONE_HUNDRED_DOLLARS), has(TradingItems.ONE_HUNDRED_DOLLARS))
                        .group("twenty_dollars")
                        .save(recipeOutput, "twenty_from_one_hundred");

                // $50
                shapeless(RecipeCategory.MISC, TradingItems.FIFTY_DOLLARS)
                        .requires(TradingItems.TEN_DOLLARS, 5)
                        .unlockedBy(getHasName(TradingItems.TEN_DOLLARS), has(TradingItems.TEN_DOLLARS))
                        .group("fifty_dollars")
                        .save(recipeOutput, "fifty_from_ten");

                shapeless(RecipeCategory.MISC, TradingItems.FIFTY_DOLLARS, 2)
                        .requires(TradingItems.ONE_HUNDRED_DOLLARS)
                        .unlockedBy(getHasName(TradingItems.ONE_HUNDRED_DOLLARS), has(TradingItems.ONE_HUNDRED_DOLLARS))
                        .group("fifty_dollars")
                        .save(recipeOutput, "fifty_from_one_hundred");

                // $100
                shapeless(RecipeCategory.MISC, TradingItems.ONE_HUNDRED_DOLLARS)
                        .requires(TradingItems.TWENTY_DOLLARS, 5)
                        .unlockedBy(getHasName(TradingItems.TWENTY_DOLLARS), has(TradingItems.TWENTY_DOLLARS))
                        .group("one_hundred_dollars")
                        .save(recipeOutput, "one_hundred_from_twenty");

                shapeless(RecipeCategory.MISC, TradingItems.ONE_HUNDRED_DOLLARS)
                        .requires(TradingItems.FIFTY_DOLLARS, 2)
                        .unlockedBy(getHasName(TradingItems.FIFTY_DOLLARS), has(TradingItems.FIFTY_DOLLARS))
                        .group("one_hundred_dollars")
                        .save(recipeOutput, "one_hundred_from_fifty");
            }
        };
    }

    @Override
    public @NonNull String getName() {
        return "RecipeProvider";
    }
}
