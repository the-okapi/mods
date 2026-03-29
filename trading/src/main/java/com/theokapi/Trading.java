package com.theokapi;

import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.theokapi.block.TradingBlockEntities;
import com.theokapi.block.TradingBlocks;
import com.theokapi.item.TradingItems;
import com.theokapi.item.TradingTags;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.item.ItemArgument;
import net.minecraft.server.permissions.Permissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Trading implements ModInitializer {
	public static final String MOD_ID = "trading";

	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		TradingBlocks.init();
		TradingBlockEntities.init();
		TradingItems.init();
		TradingTags.init();

		CommandRegistrationCallback.EVENT.register(((commandDispatcher, commandBuildContext, _) ->
			commandDispatcher.register(Commands.literal("exchange_rate")
					.requires(commandSourceStack -> commandSourceStack.permissions().hasPermission(Permissions.COMMANDS_MODERATOR))
					.then(Commands.argument("dollars", IntegerArgumentType.integer())
					.executes(_ -> 0)
					.then(Commands.argument("item", ItemArgument.item(commandBuildContext))
					.executes(_ -> 0)
					.then(Commands.argument("count", IntegerArgumentType.integer())
					.executes(context -> TradingCommands.exchangeRate(
							IntegerArgumentType.getInteger(context, "dollars"),
							ItemArgument.getItem(context, "item").item().value(),
							IntegerArgumentType.getInteger(context, "count"),
							context
					))))))/*
				commandDispatcher.register(Commands.literal("command_with_arg")
						.then(Commands.argument("value", IntegerArgumentType.integer())
								.executes(TradingCommands::executeCommandWithArg)))*/
		));
    }
}