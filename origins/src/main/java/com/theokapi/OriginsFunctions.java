package com.theokapi;

import net.minecraft.tags.ItemTags;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import org.jspecify.annotations.Nullable;

public class OriginsFunctions {
    public static void callInitFunction(@Nullable String origin, Player player) {
        if ("avian".equals(origin)) {
            avianInit(player);
        }
        if ("breezeborn".equals(origin) || "blazeborn".equals(origin)) {
            removeArmorInit(player);
        }
        if ("merling".equals(origin)) {
            merlingInit(player);
        }
    }

    public static void callCleanupFunction(@Nullable String origin, Player player) {
        if ("avian".equals(origin)) {
            avianCleanup(player);
        }
        if ("merling".equals(origin)) {
            merlingCleanup(player);
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

    private static void merlingInit(Player player) {
        player.addEffect(new MobEffectInstance(MobEffects.DOLPHINS_GRACE, -1, 0, true, false, false));
        player.addEffect(new MobEffectInstance(MobEffects.WATER_BREATHING, -1, 0, true, false, false));
    }

    private static void merlingCleanup(Player player) {
        player.removeEffect(MobEffects.DOLPHINS_GRACE);
        player.removeEffect(MobEffects.WATER_BREATHING);
    }

    private static void removeArmorInit(Player player) {
        Inventory inventory = player.getInventory();
        for (int i = 36; i < 50; i++) {
            ItemStack item = inventory.getItem(i);
            if (item.is(ItemTags.HEAD_ARMOR) || item.is(ItemTags.CHEST_ARMOR) || item.is(ItemTags.LEG_ARMOR) || item.is(ItemTags.FOOT_ARMOR)) {
                inventory.setItem(i, ItemStack.EMPTY);
            }
            int freeSlot = inventory.getFreeSlot();
            if (freeSlot != -1) {
                inventory.setItem(freeSlot, item);
            } else {
                player.drop(item, true);
            }
        }
    }
}
