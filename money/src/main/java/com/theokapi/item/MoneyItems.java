package com.theokapi.item;

import com.theokapi.Money;
import net.fabricmc.fabric.api.creativetab.v1.CreativeModeTabEvents;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;

import java.util.function.Function;

public class MoneyItems {
    private static <T extends Item> T register(String name, Function<Item.Properties, T> itemFunction, Item.Properties itemProperties) {
        ResourceKey<Item> itemResourceKey = ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(Money.MOD_ID, name));

        return Registry.register(BuiltInRegistries.ITEM, itemResourceKey, itemFunction.apply(itemProperties.setId(itemResourceKey)));
    }

    public static final Item ONE_DOLLAR = register("one_dollar", Item::new, new Item.Properties());
    public static final Item FIVE_DOLLARS = register("five_dollars", Item::new, new Item.Properties());
    public static final Item TEN_DOLLARS = register("ten_dollars", Item::new, new Item.Properties());
    public static final Item TWENTY_DOLLARS = register("twenty_dollars", Item::new, new Item.Properties());
    public static final Item FIFTY_DOLLARS = register("fifty_dollars", Item::new, new Item.Properties().rarity(Rarity.UNCOMMON));
    public static final Item ONE_HUNDRED_DOLLARS = register("one_hundred_dollars", Item::new, new Item.Properties().rarity(Rarity.UNCOMMON));

    public static void init() {
        Money.LOGGER.info("Initializing Items");

        CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.TOOLS_AND_UTILITIES)
                .register((creativeModeTab) -> {
                    creativeModeTab.accept(ONE_DOLLAR);
                    creativeModeTab.accept(FIVE_DOLLARS);
                    creativeModeTab.accept(TEN_DOLLARS);
                    creativeModeTab.accept(TWENTY_DOLLARS);
                    creativeModeTab.accept(FIFTY_DOLLARS);
                    creativeModeTab.accept(ONE_HUNDRED_DOLLARS);
                });
    }
}
