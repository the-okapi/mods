package com.theokapi;

import com.mojang.brigadier.context.CommandContext;
import net.minecraft.commands.CommandSourceStack;

public class MoneyCommands {
    public static int exchangeRate(CommandContext<CommandSourceStack> commandContext) {
        return 1;
    }
}
