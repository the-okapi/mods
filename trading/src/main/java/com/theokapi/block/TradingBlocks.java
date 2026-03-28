package com.theokapi.block;

import com.theokapi.Trading;
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

public class TradingBlocks {
    private static Block register(String name, Function<BlockBehaviour.Properties, Block> blockFunction, BlockBehaviour.Properties blockProperties) {
        ResourceKey<Block> blockResourceKey = ResourceKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(Trading.MOD_ID, name));

        Block block = blockFunction.apply(blockProperties.setId(blockResourceKey));

        ResourceKey<Item> itemResourceKey = ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(Trading.MOD_ID, name));

        BlockItem blockItem = new BlockItem(block, new Item.Properties().setId(itemResourceKey).useBlockDescriptionPrefix());
        Registry.register(BuiltInRegistries.ITEM, itemResourceKey, blockItem);

        return Registry.register(BuiltInRegistries.BLOCK, blockResourceKey, block);
    }

    public static final Block TRADING_TABLE = register("trading_table", Block::new, BlockBehaviour.Properties.of().sound(SoundType.WOOD).strength(2.5f).ignitedByLava());

    public static final Block BANKING_TABLE = register("banking_table", Block::new, BlockBehaviour.Properties.of().strength(0.8f).requiresCorrectToolForDrops());

    public static void init() {
        Trading.LOGGER.info("Initializing Blocks");

        CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.FUNCTIONAL_BLOCKS)
                .register((itemGroup) -> {
                    itemGroup.accept(TRADING_TABLE);
                    itemGroup.accept(BANKING_TABLE);
                });
    }
}
