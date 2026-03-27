package com.theokapi;

import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import org.jspecify.annotations.Nullable;

public class GraveSpawn {
    private static BlockPos getClosestValidGravePos(BlockPos deathPoint, Level level) {

        for (int i = 0; i < 319 - deathPoint.getY(); i++) {
            BlockPos newBlockPos = getBlockPos(deathPoint, level, i);
            if (newBlockPos != null) return newBlockPos;
        }

        for (int i = deathPoint.getY(); i > -62; i--) {
            BlockPos newBlockPos = getBlockPos(deathPoint, level, i);
            if (newBlockPos != null) return newBlockPos;
        }

        return deathPoint;
    }

    @Nullable
    private static BlockPos getBlockPos(BlockPos deathPoint, Level level, int i) {
        int[][] relativeSpawns = {
                {0, 0},
                {1, 0},
                {1, 1},
                {0, 1},
                {-1, 1},
                {-1, 0},
                {-1, -1},
                {0, -1},
                {1, -1}
        };
        for (int j = 0; j < 9; j++) {
            int[] relativeSpawn = relativeSpawns[j];
            BlockPos newBlockPos = deathPoint.offset(relativeSpawn[0], i, relativeSpawn[1]);
            if (level.getBlockState(newBlockPos).is(Graves.GRAVE_REPLACEABLE)) {
                return newBlockPos;
            }
        }
        return null;
    }

    public static boolean allowDeath(LivingEntity livingEntity, DamageSource damageSource, float damageAmount) {
        if (!(livingEntity instanceof ServerPlayer player)) {
            return true;
        }

        Level level = player.level();

        BlockPos blockPos = getClosestValidGravePos(player.blockPosition(), level);

        level.setBlock(blockPos, Graves.GRAVE.defaultBlockState(), 1);

        BlockEntity blockEntity = level.getBlockEntity(blockPos);

        if (!(blockEntity instanceof GraveBlockEntity graveBlockEntity)) {
            return true;
        }

        Inventory inventory = player.getInventory();

        for (int i = 0; i < 41; i++) {
            graveBlockEntity.addItem(inventory.getItem(i));
        }

        inventory.clearContent();

        return true;
    }
}
