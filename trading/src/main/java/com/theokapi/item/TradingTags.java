package com.theokapi.item;

import com.theokapi.Trading;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public class TradingTags {
    public static final TagKey<Item> MONEY =
            TagKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(Trading.MOD_ID, "money"));

    public static void init() {
        Trading.LOGGER.info("Initializing Tags");
    }
}
