package com.theokapi;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Graves implements ModInitializer {
	public static final String MOD_ID = "graves";

	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	static final ResourceKey<Block> graveKey =
			ResourceKey.create(
					Registries.BLOCK,
					Identifier.fromNamespaceAndPath(MOD_ID, "grave")
			);
	public static final Block GRAVE = Registry.register(
			BuiltInRegistries.BLOCK,
			graveKey,
			new GraveBlock(BlockBehaviour.Properties.of().sound(SoundType.STONE).strength(-1f, 3600000f).setId(graveKey))
	);

	public static final BlockEntityType<GraveBlockEntity> GRAVE_BLOCK_ENTITY =
			Registry.register(
					BuiltInRegistries.BLOCK_ENTITY_TYPE,
					Identifier.fromNamespaceAndPath(Graves.MOD_ID, "grave"),
					FabricBlockEntityTypeBuilder.create(GraveBlockEntity::new, GRAVE).build()
			);

	public static final TagKey<Block> GRAVE_REPLACEABLE = TagKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(MOD_ID, "grave_replaceable"));

	@Override
	public void onInitialize() {
		LOGGER.info("Initializing mod");

		ServerLivingEntityEvents.ALLOW_DEATH.register(((entity, _, _) -> GraveSpawn.allowDeath(entity)));
	}
}