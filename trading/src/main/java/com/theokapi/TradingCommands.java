package com.theokapi;

import com.mojang.brigadier.context.CommandContext;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;

public class TradingCommands {
    public static int exchangeRate(int dollars, Item item, int count, CommandContext<CommandSourceStack> context) {
        // TODO
        context.getSource().sendSuccess(() -> Component.literal("Changed exchange rate to " + count + " " + item.toString() + " per $" + dollars), true);
        return 1;
    }
}
