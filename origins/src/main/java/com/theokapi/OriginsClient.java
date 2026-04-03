package com.theokapi;

import com.theokapi.item.OriginsItems;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.hud.HudElementRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.hud.VanillaHudElements;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemCooldowns;
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

        float cooldown = 0.0f;

        switch (origin) {
            case "blazeborn":
                item = Items.BLAZE_ROD;
                break;
            case "avian":
                item = Items.FEATHER;
                break;
            case "enderian":
                item = Items.ENDER_PEARL;
                ItemCooldowns cooldowns = player.getCooldowns();
                cooldown = cooldowns.getCooldownPercent(OriginsItems.PEARL_ITEM.getDefaultInstance(), 1.0f);
                break;
            case "breezeborn":
                item = Items.BREEZE_ROD;
                ItemCooldowns breezebornCooldowns = player.getCooldowns();
                cooldown = breezebornCooldowns.getCooldownPercent(OriginsItems.WIND_CHARGE_ITEM.getDefaultInstance(), 1.0f);
                break;
            case "warden":
                item = Items.SCULK;
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
            case "golem":
                item = Items.IRON_INGOT;
                break;
            case null, default:
                return;
        }

        graphics.item(new ItemStack(item, 1), 4, 4);

        if ("enderian".equals(origin) && cooldown != 0.0f) {
            graphics.fill(23, 9, ((int) (24 + cooldown * 100))+1, 15, 0xFF032620);
            graphics.fill(24, 10, (int) (24 + cooldown * 100), 14, 0xFF105E51);
        } else if ("breezeborn".equals(origin) && cooldown != 0.0f) {
            graphics.fill(23, 9, ((int) (24 + cooldown * 100))+1, 15, 0xFF576C9D);
            graphics.fill(24, 10, (int) (24 + cooldown * 100), 14, 0xFFBFAFC9);
        }

    }
}
