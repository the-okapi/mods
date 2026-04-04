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
    private static final List<String> ORIGINS = List.of(
            "blazeborn",
            "enderian",
            "avian",
            "elytrian",
            "merling",
            "warden",
            "shulk",
            "arachnid",
            "feline",
            "breezeborn",
            "golem"
    );

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

    private static int setOrigin(CommandSourceStack commandSourceStack, ServerPlayer player, String origin) {
        if (ORIGINS.contains(origin)) {
            OriginsFunctions.callCleanupFunction(player.getAttached(Origins.ORIGIN_ATTACHMENT), player);
            player.setAttached(Origins.ORIGIN_ATTACHMENT, origin);
            OriginsFunctions.callInitFunction(origin, player);

            commandSourceStack.sendSuccess(() -> Component.literal(player.getPlainTextName() + "'s origin set to " + origin), true);
            return 1;
        } else {
            commandSourceStack.sendFailure(Component.literal(origin + " is not a valid origin"));
            return 0;
        }
    }

    public static int otherSetOriginCommand(CommandContext<CommandSourceStack> commandContext) {
        ServerPlayer player = commandContext.getSource().getPlayer();
        if (player == null) {
            return 0;
        }

        String origin = StringArgumentType.getString(commandContext, "origin");

        CommandSourceStack commandSourceStack = commandContext.getSource();

        return setOrigin(commandSourceStack, player, origin);
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

        return setOrigin(commandSourceStack, player, origin);
    }
}
