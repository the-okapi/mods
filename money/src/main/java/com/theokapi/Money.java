package com.theokapi;

import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.theokapi.block.MoneyBlocks;
import com.theokapi.item.MoneyItems;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.item.ItemArgument;
import net.minecraft.server.permissions.Permissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Money implements ModInitializer {
	public static final String MOD_ID = "money";

	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		MoneyItems.init();
		MoneyBlocks.init();

		CommandRegistrationCallback.EVENT.register(((commandDispatcher, commandBuildContext, _) ->
			commandDispatcher.register(Commands.literal("exchange_rate")
					.requires(source -> source.permissions().hasPermission(Permissions.COMMANDS_MODERATOR))
					.then(Commands.argument("dollars", IntegerArgumentType.integer())
							.then(Commands.argument("item", ItemArgument.item(commandBuildContext))
							.then(Commands.argument("count", IntegerArgumentType.integer())
							.executes(MoneyCommands::exchangeRate)))
		))));
	}
}