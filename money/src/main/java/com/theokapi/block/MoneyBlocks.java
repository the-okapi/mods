package com.theokapi.block;

import com.theokapi.Money;
import net.fabricmc.fabric.api.creativetab.v1.CreativeModeTabEvents;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;

import java.util.function.Function;

public class MoneyBlocks {
    private static Block register(String name, Function<BlockBehaviour.Properties, Block> blockFunction, BlockBehaviour.Properties blockProperties) {
        ResourceKey<Block> blockResourceKey = ResourceKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(Money.MOD_ID, name));

        Block block = blockFunction.apply(blockProperties.setId(blockResourceKey));

        // Only if registering item
        ResourceKey<Item> itemResourceKey = ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(Money.MOD_ID, name));

        BlockItem blockItem = new BlockItem(block, new Item.Properties().setId(itemResourceKey).useBlockDescriptionPrefix());
        Registry.register(BuiltInRegistries.ITEM, itemResourceKey, blockItem);

        return Registry.register(BuiltInRegistries.BLOCK, blockResourceKey, block);
    }

    public static final Block ATM = register(
            "atm",
            ATMBlock::new,
            BlockBehaviour.Properties.of()
                    .sound(SoundType.IRON)
                    .strength(5f, 6f)
                    .requiresCorrectToolForDrops()
    );

    public static final Block CHEESE_WHEEL = register("cheese_wheel", CheeseWheelBlock::new, BlockBehaviour.Properties.of().sound(SoundType.FUNGUS));

    public static void init() {
        Money.LOGGER.info("Initializing Items");

        CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.FUNCTIONAL_BLOCKS)
                .register((creativeModeTab) -> creativeModeTab.accept(ATM));

        CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.FOOD_AND_DRINKS)
                .register((creativeModeTab) -> creativeModeTab.accept(CHEESE_WHEEL));
    }
}
