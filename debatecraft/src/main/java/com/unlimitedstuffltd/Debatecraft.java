package com.unlimitedstuffltd;

import com.unlimitedstuffltd.attributes.DebatecraftAttributes;
import com.unlimitedstuffltd.effects.DebatecraftEffects;
import com.unlimitedstuffltd.effects.DebatecraftPotions;
import com.unlimitedstuffltd.input.DebatecraftKeyMappings;
import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Debatecraft implements ModInitializer {
	public static final String MOD_ID = "debatecraft";


	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		DebatecraftKeyMappings.init();
		DebatecraftEffects.init();
		DebatecraftPotions.init();
		DebatecraftEvents.init();
		DebatecraftAttributes.init();
	}
}