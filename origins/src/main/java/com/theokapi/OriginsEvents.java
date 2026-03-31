package com.theokapi;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.Fluids;

import java.util.Collection;
import java.util.List;


public class OriginsEvents {
    public static boolean allowDamage(LivingEntity livingEntity, DamageSource damageSource, float damageAmount) {
        String origin = livingEntity.getAttached(Origins.ORIGIN_ATTACHMENT);
        Registry<DamageType> registry = livingEntity.level().registryAccess().lookupOrThrow(Registries.DAMAGE_TYPE);
        if ("blazeborn".equals(origin)) {
            List<String> blazeBornDamageTypes = List.of(
                    registry.get(DamageTypes.ON_FIRE).orElseThrow().value().msgId(),
                    registry.get(DamageTypes.FIREBALL).orElseThrow().value().msgId(),
                    registry.get(DamageTypes.HOT_FLOOR).orElseThrow().value().msgId(),
                    registry.get(DamageTypes.IN_FIRE).orElseThrow().value().msgId()
            );
            Origins.LOGGER.info(damageSource.toString());
            return !blazeBornDamageTypes.contains(damageSource.type().msgId());
        }
        return true;
    }

    public static void levelTickStart(ServerLevel serverLevel) {
        List<ServerPlayer> players = serverLevel.players();

        for (ServerPlayer player : players) {
            Fluid fluid = serverLevel.getBlockState(player.blockPosition()).getFluidState().getType();
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
                            Origins.LOGGER.info("{}1", blockState);
                            Origins.LOGGER.info("{}2", Blocks.AIR.defaultBlockState());
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
}
