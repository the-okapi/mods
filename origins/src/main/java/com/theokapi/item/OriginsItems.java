package com.theokapi.item;

import com.theokapi.Origins;
import net.fabricmc.fabric.api.creativetab.v1.CreativeModeTabEvents;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;

import java.util.function.Function;

public class OriginsItems {
    private static void blazebornInit(Level level, Player player) {
        // TODO: Teleport to nether
    }

    private static <T extends Item> T register(String name, Function<Item.Properties, T> itemFunction, Item.Properties itemProperties) {
        ResourceKey<Item> itemResourceKey = ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(Origins.MOD_ID, name));

        return Registry.register(BuiltInRegistries.ITEM, itemResourceKey, itemFunction.apply(itemProperties.setId(itemResourceKey)));
    }

    public static final Item BLAZEBORN_ORB = register(
            "blazeborn_orb",
            properties -> new OrbItem(properties, "blazeborn", OriginsItems::blazebornInit),
            new Item.Properties().stacksTo(1)
    );

    public static final Item AVIAN_ORB = register(
            "avian_orb",
            properties -> new OrbItem(properties, "avian", null),
            new Item.Properties().stacksTo(1)
    );

    public static final Item ENDERIAN_ORB = register(
            "enderian_orb",
            properties -> new OrbItem(properties, "enderian", null),
            new Item.Properties().stacksTo(1)
    );

    public static void init() {
        Origins.LOGGER.info("Initializing Items");

        CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.TOOLS_AND_UTILITIES)
                .register(creativeModeTab -> {
                    creativeModeTab.accept(BLAZEBORN_ORB);
                    creativeModeTab.accept(AVIAN_ORB);
                    creativeModeTab.accept(ENDERIAN_ORB);
                });
    }
}
