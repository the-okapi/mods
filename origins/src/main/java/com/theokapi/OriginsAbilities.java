package com.theokapi;

import com.mojang.blaze3d.platform.InputConstants;
import com.theokapi.item.OriginsItems;
import com.theokapi.networking.ServerboundPearlPayload;
import com.theokapi.networking.ServerboundWindChargePayload;
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
import net.minecraft.world.entity.projectile.hurtingprojectile.windcharge.WindCharge;
import net.minecraft.world.entity.projectile.throwableitemprojectile.ThrownEnderpearl;
import net.minecraft.world.item.ItemCooldowns;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import org.lwjgl.glfw.GLFW;

import java.util.Collection;



public class OriginsAbilities {
    private record PlayerCooldowns(ServerPlayer player, ItemCooldowns itemCooldowns) {}

    private static final KeyMapping ORIGIN_ABILITY_KEY_MAPPING = KeyMappingHelper.registerKeyMapping(
            new KeyMapping(
                    "key.origins.origin_ability",
                    InputConstants.Type.KEYSYM,
                    GLFW.GLFW_KEY_C,
                    Origins.CATEGORY
            )
    );

    private static PlayerCooldowns getPlayerAndCooldowns(ServerPlayNetworking.Context context, String playerName) {
        MinecraftServer server = context.server();
        Collection<ServerPlayer> players = PlayerLookup.all(server);
        ServerPlayer player = null;
        for (ServerPlayer p : players) {
            if (p.getPlainTextName().equals(playerName)) {
                player = p;
            }
        }
        if (player == null) {
            return null;
        }
        ItemCooldowns cooldowns = player.getCooldowns();
        return new PlayerCooldowns(player, cooldowns);
    }

    public static void init() {
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (ORIGIN_ABILITY_KEY_MAPPING.consumeClick()) {
                if (client.player == null) {
                    return;
                }

                Player player = client.player;
                String origin = player.getAttached(Origins.ORIGIN_ATTACHMENT);

                Level level = client.level;
                if (level == null) {
                    return;
                }

                if ("enderian".equals(origin)) {
                    ServerboundPearlPayload payload = new ServerboundPearlPayload(player.getPlainTextName());
                    ClientPlayNetworking.send(payload);
                }

                if ("breezeborn".equals(origin)) {
                    ServerboundWindChargePayload payload = new ServerboundWindChargePayload(player.getPlainTextName());
                    ClientPlayNetworking.send(payload);
                }

                if ("elytrian".equals(origin) && player.onGround()) {
                    ItemCooldowns cooldowns = player.getCooldowns();
                    float windChargeCooldown = cooldowns.getCooldownPercent(OriginsItems.ROCKET_ITEM.getDefaultInstance(), 1);
                    if (windChargeCooldown > 0.0f) {
                        return;
                    }
                    player.setDeltaMovement(0.0, 2.0, 0.0);
                    cooldowns.addCooldown(OriginsItems.ROCKET_ITEM.getDefaultInstance(), 30 * 20);
                }
            }
        });


        ServerPlayNetworking.registerGlobalReceiver(ServerboundPearlPayload.TYPE, ((payload, context) -> {
            PlayerCooldowns playerCooldowns = getPlayerAndCooldowns(context, payload.player());
            if (playerCooldowns == null) {
                return;
            }
            ItemCooldowns cooldowns = playerCooldowns.itemCooldowns;
            ServerPlayer player = playerCooldowns.player;
            float pearlCooldown = cooldowns.getCooldownPercent(OriginsItems.PEARL_ITEM.getDefaultInstance(), 1);
            if (pearlCooldown > 0.0f) {
                return;
            }
            ServerLevel level = player.level();
            level.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.ENDER_PEARL_THROW, SoundSource.NEUTRAL, 0.5f, 0.4F / (level.getRandom().nextFloat() * 0.4F + 0.8F));
            Projectile.spawnProjectileFromRotation(ThrownEnderpearl::new, level, new ItemStack(Items.ENDER_PEARL), player, 0.0F, 1.5F, 1.0F);
            cooldowns.addCooldown(OriginsItems.PEARL_ITEM.getDefaultInstance(), 20);
        }));

        ServerPlayNetworking.registerGlobalReceiver(ServerboundWindChargePayload.TYPE, (((payload, context) -> {
            PlayerCooldowns playerCooldowns = getPlayerAndCooldowns(context, payload.player());
            if (playerCooldowns == null) {
                return;
            }
            ItemCooldowns cooldowns = playerCooldowns.itemCooldowns;
            ServerPlayer player = playerCooldowns.player;
            float windChargeCooldown = cooldowns.getCooldownPercent(OriginsItems.WIND_CHARGE_ITEM.getDefaultInstance(), 1);
            if (windChargeCooldown > 0.0f) {
                return;
            }
            ServerLevel level = player.level();
            level.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.WIND_CHARGE_THROW, SoundSource.NEUTRAL, 0.5F, 0.4F / (level.getRandom().nextFloat() * 0.4F + 0.8F));
            Projectile.spawnProjectileFromRotation(
                    (_, _, _) -> new WindCharge(player, level, player.position().x(), player.getEyePosition().y(), player.position().z()),
                    level,
                    new ItemStack(Items.WIND_CHARGE),
                    player,
                    0.0F,
                    1.5F,
                    1.0F
            );
            cooldowns.addCooldown(OriginsItems.WIND_CHARGE_ITEM.getDefaultInstance(), 10);
        })));
    }
}
