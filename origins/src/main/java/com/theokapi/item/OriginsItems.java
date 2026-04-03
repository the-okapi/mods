package com.theokapi.item;

import com.theokapi.Origins;
import net.fabricmc.fabric.api.creativetab.v1.CreativeModeTabEvents;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;

import java.util.function.Function;

public class OriginsItems {
    private static <T extends Item> T register(String name, Function<Item.Properties, T> itemFunction, Item.Properties itemProperties) {
        ResourceKey<Item> itemResourceKey = ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(Origins.MOD_ID, name));

        return Registry.register(BuiltInRegistries.ITEM, itemResourceKey, itemFunction.apply(itemProperties.setId(itemResourceKey)));
    }

    private static Item registerOrb(String name) {
        return register(name+"_orb", properties -> new OrbItem(properties, name), new Item.Properties().stacksTo(1));
    }

    public static final Item BLAZEBORN_ORB = registerOrb("blazeborn");

    public static final Item AVIAN_ORB = registerOrb("avian");

    public static final Item ENDERIAN_ORB = registerOrb("enderian");

    public static final Item MERLING_ORB = registerOrb("merling");

    public static final Item WARDEN_ORB = registerOrb("warden");

    public static final Item ELYTRIAN_ORB = registerOrb("elytrian");

    public static final Item ARACHNID_ORB = registerOrb("arachnid");

    public static final Item SHULK_ORB = registerOrb("shulk");

    public static final Item FELINE_ORB = registerOrb("feline");

    public static final Item BREEZEBORN_ORB = registerOrb("breezeborn");

    public static final Item GOLEM_ORB = registerOrb("golem");

    public static final Item REVERSE_ORB =
            register("reverse_orb", properties -> new OrbItem(properties, ""), new Item.Properties().stacksTo(1));

    public static void init() {
        Origins.LOGGER.info("Initializing Items");

        CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.TOOLS_AND_UTILITIES)
                .register(creativeModeTab -> {
                    creativeModeTab.accept(ARACHNID_ORB);
                    creativeModeTab.accept(AVIAN_ORB);
                    creativeModeTab.accept(BLAZEBORN_ORB);
                    creativeModeTab.accept(BREEZEBORN_ORB);
                    creativeModeTab.accept(ELYTRIAN_ORB);
                    creativeModeTab.accept(ENDERIAN_ORB);
                    creativeModeTab.accept(FELINE_ORB);
                    creativeModeTab.accept(GOLEM_ORB);
                    creativeModeTab.accept(MERLING_ORB);
                    creativeModeTab.accept(SHULK_ORB);
                    creativeModeTab.accept(WARDEN_ORB);
                    creativeModeTab.accept(REVERSE_ORB);
                });
    }
}
