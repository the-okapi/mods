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

import java.util.function.BiConsumer;
import java.util.function.Function;

public class OriginsItems {
    private static void blazebornInit(Level level, Player player) {
        // TODO: Teleport to nether
    }

    private static <T extends Item> T register(String name, Function<Item.Properties, T> itemFunction, Item.Properties itemProperties) {
        ResourceKey<Item> itemResourceKey = ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(Origins.MOD_ID, name));

        return Registry.register(BuiltInRegistries.ITEM, itemResourceKey, itemFunction.apply(itemProperties.setId(itemResourceKey)));
    }

    private static Item registerOrb(String name, BiConsumer<Level, Player> function) {
        return register(name+"_orb", properties -> new OrbItem(properties, name, function), new Item.Properties().stacksTo(1));
    }

    public static final Item BLAZEBORN_ORB = registerOrb("blazeborn", OriginsItems::blazebornInit);

    public static final Item AVIAN_ORB = registerOrb("avian", null);

    public static final Item ENDERIAN_ORB = registerOrb("enderian", null);

    public static final Item MERLING_ORB = registerOrb("merling", null);

    public static final Item PHANTOM_ORB = registerOrb("phantom", null);

    public static final Item ELYTRIAN_ORB = registerOrb("elytrian", null);

    public static final Item ARACHNID_ORB = registerOrb("arachnid", null);

    public static final Item SHULK_ORB = registerOrb("shulk", null);

    public static final Item FELINE_ORB = registerOrb("feline", null);

    public static void init() {
        Origins.LOGGER.info("Initializing Items");

        CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.TOOLS_AND_UTILITIES)
                .register(creativeModeTab -> {
                    creativeModeTab.accept(BLAZEBORN_ORB);
                    creativeModeTab.accept(AVIAN_ORB);
                    creativeModeTab.accept(ENDERIAN_ORB);
                    creativeModeTab.accept(MERLING_ORB);
                    creativeModeTab.accept(PHANTOM_ORB);
                    creativeModeTab.accept(ELYTRIAN_ORB);
                    creativeModeTab.accept(ARACHNID_ORB);
                    creativeModeTab.accept(SHULK_ORB);
                    creativeModeTab.accept(FELINE_ORB);
                });
    }
}
