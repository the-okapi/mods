package com.theokapi;

import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponentMap;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.Unit;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import org.jspecify.annotations.Nullable;

import java.util.List;

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
        if ("shulk".equals(origin)) {
            removeShieldInit(player);
            shulkInit(player);
        }
        switch (origin) {
            case "avian":
                avianInit(player);
                break;
            case "breezeborn", "blazeborn":
                removeArmorInit(player);
                break;
            case "merling":
                merlingInit(player);
                break;
            case "shulk":
                removeShieldInit(player);
                shulkInit(player);
            case "elytrian":
                elytrianInit(player);
                break;
            case "warden":
                wardenInit(player);
                break;
            case "enderian":
                enderianInit(player);
                break;
            case "golem":
                golemInit(player);
            case null, default:
                break;
        }
        setHearts(origin, player);
    }

    public static void callCleanupFunction(@Nullable String origin, Player player) {
        switch (origin) {
            case "avian":
                avianCleanup(player);
                break;
            case "merling":
                merlingCleanup(player);
                break;
            case "shulk":
                shulkCleanup(player);
                break;
            case "elytrian":
                elytrianCleanup(player);
                break;
            case "warden":
                wardenCleanup(player);
                break;
            case "enderian":
                enderianCleanup(player);
                break;
            case "golem":
                golemCleanup(player);
                break;
            case null, default:
                break;
        }
        List<String> healthAlteringOrigins = List.of("breezeborn", "feline", "arachnid", "warden", "golem");
        if (healthAlteringOrigins.contains(origin)) {
            resetHearts(player);
        }
    }

    private static void resetHearts(Player player) {
        AttributeInstance maxHealth = player.getAttribute(Attributes.MAX_HEALTH);
        if (maxHealth == null) {
            return;
        }

        maxHealth.setBaseValue(20.0);
    }

    private static void setHearts(String origin, Player player) {
        AttributeInstance maxHealth = player.getAttribute(Attributes.MAX_HEALTH);
        if (maxHealth == null) {
            return;
        }

        double maxHealthValue = -1.0;
        switch (origin) {
            case "breezeborn":
                maxHealthValue = 12.0;
                break;
            case "feline":
                maxHealthValue = 18.0;
                break;
            case "arachnid":
                maxHealthValue = 14.0;
                break;
            case "warden":
                maxHealthValue =  40.0;
                break;
            case "golem":
                maxHealthValue = 34.0;
            default:
                break;
        }
        if (maxHealthValue != -1.0) {
            maxHealth.setBaseValue(maxHealthValue);
        }
    }

    private static void enderianInit(Player player) {
        AttributeInstance attributeInstance = player.getAttribute(Attributes.BLOCK_INTERACTION_RANGE);
        if (attributeInstance == null) {
            return;
        }
        attributeInstance.setBaseValue(6.5);
    }

    private static void enderianCleanup(Player player) {
        AttributeInstance attributeInstance = player.getAttribute(Attributes.BLOCK_INTERACTION_RANGE);
        if (attributeInstance == null) {
            return;
        }
        attributeInstance.setBaseValue(4.5);
    }

    private static void elytrianInit(Player player) {
        Inventory inventory = player.getInventory();
        for (int i = 36; i < 40; i++) {
            ItemStack item = inventory.getItem(i);
            if (item.is(Origins.ELYTRIAN_NOT_ALLOWED)) {
                Origins.giveItem(player, item);
            }
        }
        ItemStack item = inventory.getItem(38);
        if (item != ItemStack.EMPTY) {
            Origins.giveItem(player, item);
        }
        ItemStack elytraItem = new ItemStack(Items.ELYTRA);
        Registry<Enchantment> registry = player.level().registryAccess().lookupOrThrow(Registries.ENCHANTMENT);
        Enchantment curseOfBinding = registry.get(Enchantments.BINDING_CURSE.identifier()).orElseThrow().value();
        Enchantment curseOfVanishing = registry.get(Enchantments.VANISHING_CURSE.identifier()).orElseThrow().value();
        elytraItem.enchant(Holder.direct(curseOfBinding), 1);
        elytraItem.enchant(Holder.direct(curseOfVanishing), 1);
        elytraItem.applyComponents(DataComponentMap.builder()
                .set(DataComponents.UNBREAKABLE, Unit.INSTANCE)
                .set(DataComponents.ITEM_NAME, Component.literal("Elytrian Wings"))
                .build());
        inventory.setItem(38, elytraItem);
    }

    private static void elytrianCleanup(Player player) {
        player.getInventory().setItem(38, ItemStack.EMPTY);
    }

    private static void avianInit(Player player) {
        player.addEffect(new MobEffectInstance(MobEffects.SPEED, -1, 0, true, false, false));
        player.addEffect(new MobEffectInstance(MobEffects.SLOW_FALLING, -1, 0, true, false, false));
    }

    private static void avianCleanup(Player player) {
        player.removeEffect(MobEffects.SPEED);
        player.removeEffect(MobEffects.SLOW_FALLING);
    }

    private static void golemInit(Player player) {
        player.addEffect(new MobEffectInstance(MobEffects.HERO_OF_THE_VILLAGE, -1, 0, true, false, false));
        player.addEffect(new MobEffectInstance(MobEffects.SLOWNESS, -1, 0, true, false, false));
    }

    private static void golemCleanup(Player player) {
        player.removeEffect(MobEffects.HERO_OF_THE_VILLAGE);
        player.removeEffect(MobEffects.SLOWNESS);
    }

    private static void wardenInit(Player player) {
        player.addEffect(new MobEffectInstance(MobEffects.SLOWNESS, -1, 1, true, false, false));
        player.addEffect(new MobEffectInstance(MobEffects.STRENGTH, -1, 1, true, false, false));
        player.addEffect(new MobEffectInstance(MobEffects.RESISTANCE, -1, 0, true, false, false));
        player.addEffect(new MobEffectInstance(MobEffects.BLINDNESS, -1, 0, true, false, false));
    }

    private static void wardenCleanup(Player player) {
        player.removeEffect(MobEffects.STRENGTH);
        player.removeEffect(MobEffects.RESISTANCE);
        player.removeEffect(MobEffects.BLINDNESS);
        player.removeEffect(MobEffects.SLOWNESS);
    }

    private static void shulkInit(Player player) {
        player.addEffect(new MobEffectInstance(MobEffects.RESISTANCE, -1, 0, true, false, false));
        player.addEffect(new MobEffectInstance(MobEffects.HUNGER, -1, 0, true, false, false));
    }

    private static void shulkCleanup(Player player) {
        player.removeEffect(MobEffects.RESISTANCE);
        player.removeEffect(MobEffects.HUNGER);
    }

    private static void merlingInit(Player player) {
        player.addEffect(new MobEffectInstance(MobEffects.DOLPHINS_GRACE, -1, 0, true, false, false));
        player.addEffect(new MobEffectInstance(MobEffects.WATER_BREATHING, -1, 0, true, false, false));
    }

    private static void merlingCleanup(Player player) {
        player.removeEffect(MobEffects.DOLPHINS_GRACE);
        player.removeEffect(MobEffects.WATER_BREATHING);
    }

    private static void removeShieldInit(Player player) {
        Inventory inventory = player.getInventory();
        ItemStack item = inventory.getItem(40);
        if (item.getItem() == Items.SHIELD) {
            inventory.setItem(40, ItemStack.EMPTY);
            Origins.giveItem(player, item);
        }
    }

    private static void removeArmorInit(Player player) {
        Inventory inventory = player.getInventory();
        for (int i = 36; i < 50; i++) {
            ItemStack item = inventory.getItem(i);
            if (item.is(ItemTags.HEAD_ARMOR) || item.is(ItemTags.CHEST_ARMOR) || item.is(ItemTags.LEG_ARMOR) || item.is(ItemTags.FOOT_ARMOR)) {
                inventory.setItem(i, ItemStack.EMPTY);
                Origins.giveItem(player, item);
            }
        }
    }
}
