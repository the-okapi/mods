package com.theokapi;

import com.mojang.blaze3d.platform.InputConstants;
import com.theokapi.item.OriginsItems;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keymapping.v1.KeyMappingHelper;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PlayerLookup;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.client.KeyMapping;
import net.minecraft.resources.Identifier;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.projectile.throwableitemprojectile.ThrownEnderpearl;
import net.minecraft.world.item.ItemCooldowns;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import org.lwjgl.glfw.GLFW;

import java.util.Collection;


public class OriginsAbilities {

    private static final KeyMapping.Category CATEGORY = KeyMapping.Category.register(
            Identifier.fromNamespaceAndPath(Origins.MOD_ID, "origins_category")
    );

    private static final KeyMapping ORIGIN_ABILITY_KEY_MAPPING = KeyMappingHelper.registerKeyMapping(
            new KeyMapping(
                    "key.origins.origin_ability",
                    InputConstants.Type.KEYSYM,
                    GLFW.GLFW_KEY_C,
                    CATEGORY
            )
    );

    public static void init() {
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (ORIGIN_ABILITY_KEY_MAPPING.consumeClick()) {
                if (client.player == null) {
                    return;
                }

                Player player = client.player;
                String origin = player.getAttached(Origins.ORIGIN_ATTACHMENT);

                if ("enderian".equals(origin)) {
                    Level level = client.level;
                    if (level == null) {
                        return;
                    }

                    ServerboundPearlPayload payload = new ServerboundPearlPayload(player.getPlainTextName());
                    ClientPlayNetworking.send(payload);
                }
            }
        });

        ServerPlayNetworking.registerGlobalReceiver(ServerboundPearlPayload.TYPE, ((payload, context) -> {
            MinecraftServer server = context.server();
            Collection<ServerPlayer> players = PlayerLookup.all(server);
            ServerPlayer player = null;
            for (ServerPlayer p : players) {
                if (p.getPlainTextName().equals(payload.player())) {
                    player = p;
                }
            }
            if (player == null) {
                return;
            }
            ItemCooldowns cooldowns = player.getCooldowns();
            float pearlCooldown = cooldowns.getCooldownPercent(OriginsItems.PEARL_ITEM.getDefaultInstance(), 1.0f);
            if (pearlCooldown > 0.0f) {
                return;
            }
            ServerLevel level = player.level();
            level.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.ENDER_PEARL_THROW, SoundSource.NEUTRAL, 0.5f, 0.4F / (level.getRandom().nextFloat() * 0.4F + 0.8F));
            Projectile.spawnProjectileFromRotation(ThrownEnderpearl::new, level, new ItemStack(Items.ENDER_PEARL), player, 0.0F, 1.5F, 1.0F);
            cooldowns.addCooldown(OriginsItems.PEARL_ITEM.getDefaultInstance(), 20);
        }));
    }
}
