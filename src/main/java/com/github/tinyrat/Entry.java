package com.github.tinyrat;

import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Entry implements ModInitializer {
	public static final String MOD_ID = "tinyrat";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		LOGGER.info("tiny rat initialized");
	}
}