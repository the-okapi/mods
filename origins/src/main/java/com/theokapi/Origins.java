package com.theokapi;

import com.mojang.serialization.Codec;
import com.theokapi.item.OriginsItems;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.attachment.v1.AttachmentRegistry;
import net.fabricmc.fabric.api.attachment.v1.AttachmentType;
import net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.resources.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Origins implements ModInitializer {
	public static final String MOD_ID = "origins";

	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	public static final AttachmentType<String> ORIGIN_ATTACHMENT = AttachmentRegistry.createPersistent(
			Identifier.fromNamespaceAndPath(MOD_ID, "origin_attachment"),
			Codec.STRING
	);

	@Override
	public void onInitialize() {
		ServerLivingEntityEvents.ALLOW_DAMAGE.register(OriginsEvents::allowDamage);
		ServerTickEvents.START_LEVEL_TICK.register(OriginsEvents::levelTickStart);

		OriginsItems.init();
	}
}