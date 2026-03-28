package com.theokapi;

import com.theokapi.block.TradingBlocks;
import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Trading implements ModInitializer {
	public static final String MOD_ID = "trading";

	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		TradingBlocks.init();
	}
}