package com.theokapi;

import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.serialization.Codec;
import com.theokapi.item.OriginsItems;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.attachment.v1.AttachmentRegistry;
import net.fabricmc.fabric.api.attachment.v1.AttachmentSyncPredicate;
import net.fabricmc.fabric.api.attachment.v1.AttachmentType;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.fabricmc.fabric.api.entity.event.v1.EntitySleepEvents;
import net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents;
import net.fabricmc.fabric.api.entity.event.v1.ServerPlayerEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents;
import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.fabricmc.fabric.api.event.player.UseItemCallback;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Origins implements ModInitializer {
	public static final String MOD_ID = "origins";

	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	public static final AttachmentType<String> ORIGIN_ATTACHMENT = AttachmentRegistry.create(
			Identifier.fromNamespaceAndPath(MOD_ID, "origin_attachment"),
			stringBuilder -> stringBuilder
					.initializer(() -> "")
					.persistent(Codec.STRING)
					.copyOnDeath()
					.syncWith(
							ByteBufCodecs.STRING_UTF8,
							AttachmentSyncPredicate.targetOnly()
					)
	);

	public static final TagKey<Item> ORBS = TagKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(MOD_ID, "orbs"));

	public static final TagKey<Item> ELYTRIAN_NOT_ALLOWED = TagKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(MOD_ID, "elytrian_not_allowed"));

	public static final TagKey<Item> FOODS = TagKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(MOD_ID, "foods"));

	public static final TagKey<Item> PUMPKIN = TagKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(MOD_ID, "pumpkin"));

	public static <T extends Player> void giveItem(T player, ItemStack item) {
		boolean addedItem = player.addItem(item);
		if (!addedItem) {
			player.drop(item, true);
		}
	}

	@Override
	public void onInitialize() {
		PayloadTypeRegistry.serverboundPlay().register(ServerboundPearlPayload.TYPE, ServerboundPearlPayload.CODEC);

		ServerLivingEntityEvents.ALLOW_DAMAGE.register((OriginsEvents::allowDamage));
		ServerTickEvents.START_LEVEL_TICK.register(OriginsEvents::levelTickStart);
		EntitySleepEvents.ALLOW_SLEEPING.register(OriginsEvents::allowSleeping);
		UseItemCallback.EVENT.register((player, _, _) -> OriginsEvents.useItem(player));
		UseBlockCallback.EVENT.register(((player, level, _, blockHitResult) -> OriginsEvents.useBlock(player, level, blockHitResult)));
		ServerPlayerEvents.AFTER_RESPAWN.register(((_, newPlayer, _) -> OriginsEvents.afterRespawn(newPlayer)));
		PlayerBlockBreakEvents.AFTER.register(((_, player, _, state, _) -> OriginsEvents.afterBlockBreak(player, state)));

		CommandRegistrationCallback.EVENT.register(((dispatcher, _, _) -> {
			dispatcher.register(Commands.literal("get_origin")
					.then(Commands.argument("player", EntityArgument.player())
							.executes(OriginsCommands::originCommand))
			);

			dispatcher.register(Commands.literal("origin")
					.then(Commands.argument("origin", StringArgumentType.string())
							.executes(OriginsCommands::setOriginCommand)));
		}));

		OriginsItems.init();
		OriginsAbilities.init();
	}
}