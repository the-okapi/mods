package com.theokapi;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.tags.BlockTags;
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
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.BlockHitResult;

import java.util.Collection;
import java.util.List;


public class OriginsEvents {
    public static boolean allowDamage(LivingEntity livingEntity, DamageSource damageSource, float damageAmount) {
        if (!(livingEntity.level() instanceof ServerLevel level)) {
            return true;
        }

        String origin = livingEntity.getAttached(Origins.ORIGIN_ATTACHMENT);
        Registry<DamageType> registry = livingEntity.level().registryAccess().lookupOrThrow(Registries.DAMAGE_TYPE);
        List<String> blazebornDamageTypes = List.of(
                registry.get(DamageTypes.ON_FIRE).orElseThrow().value().msgId(),
                registry.get(DamageTypes.FIREBALL).orElseThrow().value().msgId(),
                registry.get(DamageTypes.HOT_FLOOR).orElseThrow().value().msgId(),
                registry.get(DamageTypes.IN_FIRE).orElseThrow().value().msgId()
        );
        String damageSourceMsgId = damageSource.type().msgId();
        if ("blazeborn".equals(origin)) {
            return !blazebornDamageTypes.contains(damageSourceMsgId);
        }
        if ("breezeborn".equals(origin) || "feline".equals(origin)) {
            String fallDamageMsgId = registry.get(DamageTypes.FALL).orElseThrow().value().msgId();
            if (damageSourceMsgId.equals(fallDamageMsgId)) {
                return false;
            }
        }
        Holder<DamageType> GENERIC_DAMAGE_TYPE = registry.get(DamageTypes.GENERIC).orElseThrow();
        if ("breezeborn".equals(origin)) {
            if (blazebornDamageTypes.contains(damageSourceMsgId)) {
                livingEntity.hurtServer(level, new DamageSource(GENERIC_DAMAGE_TYPE), damageAmount * 2);
                return false;
            }
        }
        if ("elytrian".equals(origin)) {
            String kineticDamageMsgId = registry.get(DamageTypes.FLY_INTO_WALL).orElseThrow().value().msgId();
            String fallDamageMsgId = registry.get(DamageTypes.FALL).orElseThrow().value().msgId();
            if (damageSourceMsgId.equals(kineticDamageMsgId) || damageSourceMsgId.equals(fallDamageMsgId)) {
                livingEntity.hurtServer(level, new DamageSource(GENERIC_DAMAGE_TYPE), damageAmount * 1.5f);
                return false;
            }
        }
        Entity entity = damageSource.getDirectEntity();
        if (entity == null) {
            return true;
        }
        if (!(entity.level() instanceof ServerLevel serverLevel)) {
            return true;
        }
        if ("arachnid".equals(origin)) {
            serverLevel.setBlock(entity.blockPosition(), Blocks.COBWEB.defaultBlockState(), 3);
        }
        String sourceOrigin = entity.getAttached(Origins.ORIGIN_ATTACHMENT);
        if ("blazeborn".equals(sourceOrigin) && entity.getRemainingFireTicks() > 0) {
            livingEntity.hurtServer(serverLevel, new DamageSource(damageSource.typeHolder()), damageAmount * 1.5f);
            return false;
        }
        if ("elytrian".equals(sourceOrigin) && !entity.onGround()) {
            livingEntity.hurtServer(serverLevel, new DamageSource(damageSource.typeHolder()), damageAmount * 2);
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
            if ("enderian".equals(origin)) {
                Inventory inventory = player.getInventory();

                ItemStack headItem = inventory.getItem(39);
                ItemStack activeItem = player.getActiveItem();

                if (headItem.is(Origins.PUMPKIN)  || activeItem.is(Origins.PUMPKIN)) {
                    player.hurtServer(serverLevel, new DamageSource(
                            serverLevel.registryAccess().lookupOrThrow(Registries.DAMAGE_TYPE).get(DamageTypes.GENERIC.identifier()).orElseThrow()
                    ), 1f);
                }
            }
            if ("merling".equals(origin)) {
                Integer airSupply = player.getAttached(Origins.MERLING_AIR_SUPPLY);
                if (airSupply == null) {
                    player.setAttached(Origins.MERLING_AIR_SUPPLY, 300);
                    airSupply = 300;
                }

                if (player.isInWater()) {
                    if (airSupply < 300) {
                        airSupply++;
                        player.setAttached(Origins.MERLING_AIR_SUPPLY, airSupply);
                    }
                } else {
                    if (airSupply > 0) {
                        airSupply--;
                        player.setAttached(Origins.MERLING_AIR_SUPPLY, airSupply);
                    } else {
                        player.hurtServer(serverLevel, new DamageSource(
                                serverLevel.registryAccess().lookupOrThrow(Registries.DAMAGE_TYPE).get(DamageTypes.DRY_OUT.identifier()).orElseThrow()
                        ), 1.0f);
                    }
                }
            }
            if ("elytrian".equals(origin)) {
                BlockPos blockPos = player.blockPosition();
                ServerLevel level = player.level();
                List<BlockState> blocks = List.of(
                        level.getBlockState(blockPos.offset(0, 1, 0)),
                        level.getBlockState(blockPos.offset(0, 2, 0)),
                        level.getBlockState(blockPos.offset(0, 3, 0))
                );
                for (BlockState blockState : blocks) {
                    if (blockState != Blocks.AIR.defaultBlockState() && !blockState.is(BlockTags.REPLACEABLE)) {
                        player.addEffect(new MobEffectInstance(MobEffects.SLOWNESS, 15 * 20));
                        player.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 15 * 20));
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


        if (("blazeborn".equals(origin) || "breezeborn".equals(origin) || "golem".equals(origin)) && (
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

        if ("shulk".equals(origin) && item.getItem() == Items.SHIELD) {
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
            OriginsFunctions.callInitFunction(origin, player);
        }
    }

    private static Item convertStone(Item stoneFrom) {
        if (stoneFrom == Items.STONE) {
            return Items.COBBLESTONE;
        } else if (stoneFrom == Items.DEEPSLATE) {
            return Items.COBBLED_DEEPSLATE;
        }
        return stoneFrom;
    }

    public static void afterBlockBreak(Player player, BlockState blockState) {
        String origin = player.getAttached(Origins.ORIGIN_ATTACHMENT);
        if (!"shulk".equals(origin)) {
            return;
        }
        if (blockState.is(BlockTags.BASE_STONE_OVERWORLD)) {
            ItemStack item = new ItemStack(convertStone(blockState.getBlock().asItem()));
            Origins.giveItem(player, item);
        }
    }

    public static boolean beforeBlockBreak(Level level, Player player, BlockPos blockPos, BlockState blockState) {
        String origin = player.getAttached(Origins.ORIGIN_ATTACHMENT);
        if (!"feline".equals(origin) || !blockState.is(BlockTags.BASE_STONE_OVERWORLD)) {
            return true;
        }
        List<BlockState> blockStates = List.of(
                level.getBlockState(blockPos.offset(1, 0, 0)),
                level.getBlockState(blockPos.offset(-1, 0, 0)),
                level.getBlockState(blockPos.offset(0, 1, 0)),
                level.getBlockState(blockPos.offset(0, -1, 0)),
                level.getBlockState(blockPos.offset(0, 0, 1)),
                level.getBlockState(blockPos.offset(0, 0, -1))
        );
        int naturalStoneBlocks = 0;
        for (BlockState state : blockStates) {
            if (state.is(BlockTags.BASE_STONE_OVERWORLD) || state.is(Origins.ORES)) {
                naturalStoneBlocks++;
            }
        }
        return naturalStoneBlocks <= 3;
    }
}
