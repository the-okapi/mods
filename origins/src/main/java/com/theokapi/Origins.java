package com.theokapi;

import com.mojang.serialization.Codec;
import com.theokapi.item.OriginsItems;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.attachment.v1.AttachmentRegistry;
import net.fabricmc.fabric.api.attachment.v1.AttachmentSyncPredicate;
import net.fabricmc.fabric.api.attachment.v1.AttachmentType;
import net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
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

	public static final AttachmentType<Boolean> JOINED_ATTACHMENT = AttachmentRegistry.createPersistent(
			Identifier.fromNamespaceAndPath(MOD_ID, "joined_attachment"),
			Codec.BOOL
	);

	public static final TagKey<Item> ORBS = TagKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(MOD_ID, "orbs"));

	@Override
	public void onInitialize() {
		ServerLivingEntityEvents.ALLOW_DAMAGE.register(((livingEntity, damageSource, _) -> OriginsEvents.allowDamage(livingEntity, damageSource)));
		ServerTickEvents.START_LEVEL_TICK.register(OriginsEvents::levelTickStart);
		ServerPlayConnectionEvents.JOIN.register((handler, _, _) -> OriginsEvents.join(handler));

		OriginsItems.init();
	}
}