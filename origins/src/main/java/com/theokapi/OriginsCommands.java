package com.theokapi;

import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;

import java.util.List;

public class OriginsCommands {
    public static int originCommand(CommandContext<CommandSourceStack> commandContext) throws CommandSyntaxException {
        ServerPlayer player = EntityArgument.getPlayer(commandContext, "player");
        String origin = player.getAttached(Origins.ORIGIN_ATTACHMENT);
        if (origin == null) {
            commandContext.getSource().sendSuccess(() -> Component.literal(player.getPlainTextName() + " has no origin"), false);
        } else {
            commandContext.getSource().sendSuccess(() -> Component.literal(player.getPlainTextName() + " is of origin " + origin), false);
        }
        return 1;
    }

    public static int setOriginCommand(CommandContext<CommandSourceStack> commandContext) {
        ServerPlayer player = commandContext.getSource().getPlayer();
        if (player == null) {
            return 1;
        }

        String existingOrigin = player.getAttached(Origins.ORIGIN_ATTACHMENT);

        CommandSourceStack commandSourceStack = commandContext.getSource();

        if (existingOrigin != null) {
            commandSourceStack.sendFailure(Component.literal("Origin may only be set once."));
            return 0;
        }

        String origin = StringArgumentType.getString(commandContext, "origin");

        List<String> origins = List.of(
                "blazeborn",
                "enderian",
                "avian",
                "elytrian",
                "merling",
                "phantom",
                "shulk",
                "arachnid",
                "feline",
                "breezeborn"
        );

        if (origins.contains(origin)) {
            player.setAttached(Origins.ORIGIN_ATTACHMENT, origin);
            commandSourceStack.sendSuccess(() -> Component.literal(player.getPlainTextName() + "'s origin set to " + origin), true);
            return 1;
        } else {
            commandSourceStack.sendSuccess(() -> Component.literal(origin + " is not a valid origin"), false);
            return 0;
        }
    }
}
