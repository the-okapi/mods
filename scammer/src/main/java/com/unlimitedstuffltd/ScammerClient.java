package com.unlimitedstuffltd;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.hud.HudElementRegistry;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphicsExtractor;

public class ScammerClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
       HudElementRegistry.addLast(Scammer.id("hud_layer"), ScammerClient::extract);
    }

    private static void extract(GuiGraphicsExtractor graphics, DeltaTracker tickCounter) {
        Minecraft minecraft = Minecraft.getInstance();

        graphics.text(minecraft.font, "YOU GOT SCAMMED!!!!!!!", 10, 10, 0xFFFF0000, true);
    }
}