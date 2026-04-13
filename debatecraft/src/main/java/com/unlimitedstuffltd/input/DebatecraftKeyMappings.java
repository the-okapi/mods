package com.unlimitedstuffltd.input;

import com.mojang.blaze3d.platform.InputConstants;
import com.unlimitedstuffltd.Debatecraft;
import com.unlimitedstuffltd.screen.DebateScreen;
import com.unlimitedstuffltd.world.WorldEvent;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keymapping.v1.KeyMappingHelper;
import net.minecraft.client.KeyMapping;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import org.lwjgl.glfw.GLFW;

public class DebatecraftKeyMappings {
    public static final KeyMapping.Category CATEGORY = KeyMapping.Category.register(
            Identifier.fromNamespaceAndPath(Debatecraft.MOD_ID, "debatecraft_category")
    );

    public static final KeyMapping DEBATE_MENU_KEY = KeyMappingHelper.registerKeyMapping(
            new KeyMapping(
                    "key.debatecraft.debate_menu",
                    InputConstants.Type.KEYSYM,
                    GLFW.GLFW_KEY_V,
                    CATEGORY
            )
    );

    public static void init() {
        Debatecraft.LOGGER.info("Initializing Key Mappings");

        ClientTickEvents.END_CLIENT_TICK.register(minecraft -> {
            if (DEBATE_MENU_KEY.consumeClick()) {
                minecraft.setScreen(new DebateScreen(Component.empty(), WorldEvent.PLAYER_OPENS_MENU));
            }
        });
    }
}
