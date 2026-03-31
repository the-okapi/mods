package com.theokapi;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.hud.HudElementRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.hud.VanillaHudElements;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

public class OriginsClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        HudElementRegistry.attachElementBefore(VanillaHudElements.CHAT, Identifier.fromNamespaceAndPath(Origins.MOD_ID, "before_chat"), OriginsClient::render);
    }

    private static void render(GuiGraphicsExtractor graphics, DeltaTracker deltaTracker) {
        Minecraft minecraft = Minecraft.getInstance();
        LocalPlayer player = minecraft.player;
        if (player == null) {
            return;
        }

        String origin = player.getAttached(Origins.ORIGIN_ATTACHMENT);

        Item item;

        switch (origin) {
            case "blazeborn":
                item = Items.BLAZE_ROD;
                break;
            case "avian":
                item = Items.FEATHER;
                break;
            case "enderian":
                item = Items.ENDER_PEARL;
                break;
            case "phantom":
                item = Items.PHANTOM_MEMBRANE;
                break;
            case "merling":
                item = Items.COD;
                break;
            case "elytrian":
                item = Items.ELYTRA;
                break;
            case "arachnid":
                item = Items.SPIDER_EYE;
                break;
            case "feline":
                item = Items.STRING;
                break;
            case "shulk":
                item = Items.SHULKER_SHELL;
                break;
            case null, default:
                return;
        }

        graphics.item(new ItemStack(item, 1), 4, 4);

        char[] chars = origin.toCharArray();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(String.valueOf(chars[0]).toUpperCase());
        for (int i = 1; i < chars.length; i++) {
            stringBuilder.append(chars[i]);
        }
        graphics.text(minecraft.font, stringBuilder.toString(), 24, 10, 0xFFFFFFFF, true);
    }
}
