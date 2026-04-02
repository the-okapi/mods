package com.theokapi;

import com.theokapi.item.OriginsItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.network.ServerGamePacketListenerImpl;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.BlockHitResult;

import java.util.Collection;
import java.util.List;
import java.util.Random;


public class OriginsEvents {
    public static boolean allowDamage(LivingEntity livingEntity, DamageSource damageSource, float damageAmount) {
        String origin = livingEntity.getAttached(Origins.ORIGIN_ATTACHMENT);
        Registry<DamageType> registry = livingEntity.level().registryAccess().lookupOrThrow(Registries.DAMAGE_TYPE);
        if ("blazeborn".equals(origin)) {
            List<String> blazebornDamageTypes = List.of(
                    registry.get(DamageTypes.ON_FIRE).orElseThrow().value().msgId(),
                    registry.get(DamageTypes.FIREBALL).orElseThrow().value().msgId(),
                    registry.get(DamageTypes.HOT_FLOOR).orElseThrow().value().msgId(),
                    registry.get(DamageTypes.IN_FIRE).orElseThrow().value().msgId()
            );
            return !blazebornDamageTypes.contains(damageSource.type().msgId());
        }
        Entity entity = damageSource.getDirectEntity();
        if (entity == null) {
            return true;
        }
        String sourceOrigin = entity.getAttached(Origins.ORIGIN_ATTACHMENT);
        if ("blazeborn".equals(sourceOrigin) && entity.getRemainingFireTicks() > 0) {
            if (!(entity.level() instanceof ServerLevel serverLevel)) {
                return true;
            }
            livingEntity.hurtServer(serverLevel, new DamageSource(damageSource.typeHolder()), damageAmount * 1.5f);
            return false;
        }
        return true;
    }

    public static void levelTickStart(ServerLevel serverLevel) {
        List<ServerPlayer> players = serverLevel.players();

        for (ServerPlayer player : players) {
            BlockState block = serverLevel.getBlockState(player.blockPosition());
            Fluid fluid = block.getFluidState().getType();
            String origin = player.getAttached(Origins.ORIGIN_ATTACHMENT);
            if ("blazeborn".equals(origin) || "enderian".equals(origin)) {
                if (fluid == Fluids.FLOWING_WATER || fluid == Fluids.WATER) {
                    player.hurtServer(serverLevel, new DamageSource(
                            serverLevel.registryAccess().lookupOrThrow(Registries.DAMAGE_TYPE).get(DamageTypes.DROWN.identifier()).orElseThrow()
                    ), 1f);
                } else if (serverLevel.getWeatherData().isRaining()) {
                    BlockPos blockPos = player.blockPosition();
                    boolean blockAbove = false;
                    for (int i = blockPos.getY()+2; i < 320; i++) {
                        BlockState blockState = serverLevel.getBlockState(new BlockPos(blockPos.getX(), i, blockPos.getY()));
                        if (blockState != Blocks.AIR.defaultBlockState()) {
                            blockAbove = true;
                        }
                    }
                    if (!blockAbove) {
                        player.hurtServer(serverLevel, new DamageSource(
                                serverLevel.registryAccess().lookupOrThrow(Registries.DAMAGE_TYPE).get(DamageTypes.DROWN.identifier()).orElseThrow()
                        ), 1f);
                    }
                }
            }
            if ("blazeborn".equals(origin)) {
                List<MobEffect> blazebornImmune = List.of(MobEffects.POISON.value(), MobEffects.HUNGER.value());
                Collection<MobEffectInstance> effects = player.getActiveEffects();
                for (MobEffectInstance effectInstance : effects) {
                    Holder<MobEffect> effect = effectInstance.getEffect();
                    if (blazebornImmune.contains(effect.value())) {
                        player.removeEffect(effect);
                    }
                }
            }
        }
    }

    public static Player.BedSleepingProblem allowSleeping(Player player, BlockPos blockPos) {
        String origin = player.getAttached(Origins.ORIGIN_ATTACHMENT);

        if ("avian".equals(origin) && blockPos.getY() < 110) {
            player.sendOverlayMessage(Component.literal("Avians can only sleep above Y level 110"));
            return Player.BedSleepingProblem.OTHER_PROBLEM;
        }

        return null;
    }

    public static InteractionResult useItem(Player player) {
        String origin = player.getAttached(Origins.ORIGIN_ATTACHMENT);

        ItemStack item = player.getActiveItem();

        if ("avian".equals(origin) && item.is(ItemTags.MEAT)) {
            return InteractionResult.FAIL;
        }

        if ("arachnid".equals(origin) && item.is(Origins.FOODS) && !item.is(ItemTags.MEAT)) {
            return InteractionResult.FAIL;
        }


        if (("blazeborn".equals(origin) || "breezeborn".equals(origin)) && (
                item.is(ItemTags.HEAD_ARMOR) ||
                        item.is(ItemTags.CHEST_ARMOR) ||
                        item.is(ItemTags.LEG_ARMOR) ||
                        item.is(ItemTags.FOOT_ARMOR)
                )) {
            return InteractionResult.FAIL;
        }

        if ("elytrian".equals(origin) && item.is(Origins.ELYTRIAN_NOT_ALLOWED)) {
            return InteractionResult.FAIL;
        }

        return InteractionResult.PASS;
    }

    public static InteractionResult useBlock(Player player, Level level, BlockHitResult blockHitResult) {
        String origin = player.getAttached(Origins.ORIGIN_ATTACHMENT);
        Block block = level.getBlockState(blockHitResult.getBlockPos()).getBlock();
        if ("arachnid".equals(origin) && block == Blocks.CAKE) {
            return InteractionResult.FAIL;
        }
        return InteractionResult.PASS;
    }

    public static void afterRespawn(ServerPlayer player) {
        String origin = player.getAttached(Origins.ORIGIN_ATTACHMENT);

        if (origin != null) {
            Origins.LOGGER.info("respawn");
            OriginsFunctions.callInitFunction(origin, player);
        }
    }
}
