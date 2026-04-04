package com.theokapi;

import com.theokapi.item.OriginsItems;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.hud.HudElementRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.hud.VanillaHudElements;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemCooldowns;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.GameType;

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
            case "elytrian":
                item = Items.ELYTRA;
                ItemCooldowns elytrianCooldowns = player.getCooldowns();
                cooldown = elytrianCooldowns.getCooldownPercent(OriginsItems.ROCKET_ITEM.getDefaultInstance(), 1.0f);
                break;
            case "warden":
                item = Items.SCULK;
                break;
            case "merling":
                item = Items.COD;
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

        if ("merling".equals(origin)) {
            if (player.gameMode() != GameType.SURVIVAL) {
                return;
            }
            Integer airSupply = player.getAttached(Origins.MERLING_AIR_SUPPLY);
            if (airSupply == null) {
                return;
            }

            int airBubbles = (airSupply + (30 - (airSupply % 30))) / 30;
            if (airSupply == 0) {
                airBubbles = 0;
            } else if (airSupply == 300) {
                airBubbles = 10;
            }

            int scaledHeight = minecraft.getWindow().getGuiScaledHeight();
            int scaledWidth = minecraft.getWindow().getGuiScaledWidth();
            int x = scaledWidth / 2 + 82;
            int y = scaledHeight - 49;
            Identifier airTexture = Identifier.fromNamespaceAndPath("minecraft", "textures/gui/sprites/hud/air.png");
            Identifier emptyBubbleTexture = Identifier.fromNamespaceAndPath("minecraft", "textures/gui/sprites/hud/air_empty.png");
            for (int i = 0; i < airBubbles; i++) {
                graphics.blit(RenderPipelines.GUI_TEXTURED, airTexture, x - (8 * i), y, 0, 0, 9, 9, 9, 9);
            }
            for (int i = airBubbles; i < 10; i++) {
                graphics.blit(RenderPipelines.GUI_TEXTURED, emptyBubbleTexture, x - (8 * i), y, 0, 0, 9, 9, 9, 9);
            }
        } else if ("enderian".equals(origin) && cooldown != 0.0f) {
            graphics.fill(23, 9, ((int) (24 + cooldown * 100))+1, 15, 0xFF032620);
            graphics.fill(24, 10, (int) (24 + cooldown * 100), 14, 0xFF105E51);
        } else if ("breezeborn".equals(origin) && cooldown != 0.0f) {
            graphics.fill(23, 9, ((int) (24 + cooldown * 100))+1, 15, 0xFF576C9D);
            graphics.fill(24, 10, (int) (24 + cooldown * 100), 14, 0xFFBFAFC9);
        } else if ("elytrian".equals(origin) && cooldown != 0.0f) {
            graphics.fill(23, 9, ((int) (24 + cooldown * 100))+1, 15, 0xFF353535);
            graphics.fill(24, 10, (int) (24 + cooldown * 100), 14, 0xFF8F8FB3);
        }
    }
}
