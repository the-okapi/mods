package com.unlimitedstuffltd;

import net.fabricmc.api.ModInitializer;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.EntityType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Okapi implements ModInitializer {
	public static final String MOD_ID = "okapi";

	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	public static final ResourceKey<EntityType<?>> OKAPI_KEY = ResourceKey.create(Registries.ENTITY_TYPE, Identifier.fromNamespaceAndPath(MOD_ID, "okapi"));
	public static final EntityType<OkapiEntity> OKAPI =

	@Override
	public void onInitialize() {}
}