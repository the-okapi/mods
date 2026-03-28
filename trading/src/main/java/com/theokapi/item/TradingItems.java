package com.theokapi.item;

import com.theokapi.Trading;
import net.fabricmc.fabric.api.creativetab.v1.CreativeModeTabEvents;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;

import java.util.function.Function;

public class TradingItems {
    private static <T extends Item> T register(String name, Function<Item.Properties, T> itemFunction, Item.Properties itemProperties) {
        ResourceKey<Item> itemResourceKey = ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(Trading.MOD_ID, name));

        T item = itemFunction.apply(itemProperties.setId(itemResourceKey));

        return Registry.register(BuiltInRegistries.ITEM, itemResourceKey, item);
    }

    public static final Item ONE_DOLLAR = register("one_dollar", Item::new, new Item.Properties());
    public static final Item TWO_DOLLARS = register("two_dollars", Item::new, new Item.Properties());
    public static final Item FIVE_DOLLARS = register("five_dollars", Item::new, new Item.Properties());
    public static final Item TEN_DOLLARS = register("ten_dollars", Item::new, new Item.Properties());
    public static final Item TWENTY_DOLLARS = register("twenty_dollars", Item::new, new Item.Properties());
    public static final Item FIFTY_DOLLARS = register("fifty_dollars", Item::new, new Item.Properties());
    public static final Item ONE_HUNDRED_DOLLARS = register("one_hundred_dollars", Item::new, new Item.Properties());

    public static void init() {
        Trading.LOGGER.info("Initializing Items");

        CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.TOOLS_AND_UTILITIES)
                .register((itemGroup) -> {
                    itemGroup.accept(ONE_DOLLAR);
                    itemGroup.accept(TWO_DOLLARS);
                    itemGroup.accept(FIVE_DOLLARS);
                    itemGroup.accept(TEN_DOLLARS);
                    itemGroup.accept(TWENTY_DOLLARS);
                    itemGroup.accept(FIFTY_DOLLARS);
                    itemGroup.accept(ONE_HUNDRED_DOLLARS);
                });
    }
}
