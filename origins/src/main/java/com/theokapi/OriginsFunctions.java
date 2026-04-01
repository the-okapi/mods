package com.theokapi;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import org.jspecify.annotations.Nullable;

public class OriginsFunctions {
    public static void callInitFunction(@Nullable String origin, Player player) {
        if ("avian".equals(origin)) {
            avianInit(player);
        }
    }

    public static void callCleanupFunction(@Nullable String origin, Player player) {
        if ("avian".equals(origin)) {
            avianCleanup(player);
        }
    }

    private static void avianInit(Player player) {
        player.addEffect(new MobEffectInstance(MobEffects.SPEED, -1, 0, true, false, false));
        player.addEffect(new MobEffectInstance(MobEffects.SLOW_FALLING, -1, 0, true, false, false));
    }

    private static void avianCleanup(Player player) {
        player.removeEffect(MobEffects.SPEED);
        player.removeEffect(MobEffects.SLOW_FALLING);
    }
}
