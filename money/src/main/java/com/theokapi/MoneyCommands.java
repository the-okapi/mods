package com.theokapi;

import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.arguments.item.ItemArgument;
import net.minecraft.network.chat.Component;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class MoneyCommands {
    public static int exchangeRate(CommandContext<CommandSourceStack> commandContext) {
        int dollars = IntegerArgumentType.getInteger(commandContext, "dollars");
        Item item = ItemArgument.getItem(commandContext, "item").item().value();
        int count = IntegerArgumentType.getInteger(commandContext, "count");

        MinecraftServer server = commandContext.getSource().getServer();

        MoneySavedData moneySavedData = MoneySavedData.getMoneySavedData(server);

        moneySavedData.setExchangeRate(dollars, item, count);

        commandContext.getSource().sendSuccess(() -> Component.literal(
                "Exchange rate is set to " + count + " " + new ItemStack(item, 1).getItemName().getString() + (count > 1 ? "s" : "") +" for $" + dollars
        ), true);
        return 1;
    }
}
