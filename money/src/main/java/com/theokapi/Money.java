package com.theokapi;

import com.theokapi.block.MoneyBlocks;
import com.theokapi.item.MoneyItems;
import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Money implements ModInitializer {
	public static final String MOD_ID = "money";

	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		MoneyItems.init();
		MoneyBlocks.init();
	}
}