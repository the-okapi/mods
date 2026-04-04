package com.theokapi.mixin;

import com.google.common.collect.Lists;
import com.theokapi.Origins;
import net.minecraft.client.Camera;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.fog.FogData;
import net.minecraft.client.renderer.fog.FogRenderer;
import net.minecraft.client.renderer.fog.environment.*;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.material.FogType;
import org.joml.Vector4f;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

@Mixin(FogRenderer.class)
abstract class FogRendererMixin {
    @Unique
    private static final List<FogEnvironment> FOG_ENVIRONMENTS = Lists.newArrayList(
            new LavaFogEnvironment(),
            new PowderedSnowFogEnvironment(),
            new BlindnessFogEnvironment(),
            new DarknessFogEnvironment(),
            new WaterFogEnvironment(),
            new AtmosphericFogEnvironment());

    @Inject(method = "setupFog", at = @At("HEAD"), cancellable = true)
    private void setupFog(Camera camera, int renderDistanceInChunks, DeltaTracker deltaTracker, float darkenWorldAmount, ClientLevel level, CallbackInfoReturnable<FogData> cir) {
        Entity entity = camera.entity();
        if (entity == null) {
            return;
        }

        String origin = entity.getAttached(Origins.ORIGIN_ATTACHMENT);
        if (!"merling".equals(origin)) {
            return;
        }
        FogType fogType = camera.getFluidInCamera();
        if (fogType != FogType.WATER) {
            return;
        }
        float renderDistanceInBlocks = renderDistanceInChunks * 16;
        FogData fog = new FogData();
        fog.color = new Vector4f(0f, 0f, 0f, 0f);
        for (FogEnvironment fogEnvironment : FOG_ENVIRONMENTS) {
            if (fogEnvironment.isApplicable(FogType.WATER, entity)) {
                fogEnvironment.setupFog(fog, camera, level, renderDistanceInBlocks, deltaTracker);
                break;
            }
        }

        float renderDistanceFogSpan = Mth.clamp(renderDistanceInBlocks / 10.0F, 4.0F, 64.0F);
        fog.renderDistanceStart = renderDistanceInBlocks - renderDistanceFogSpan;
        fog.renderDistanceEnd = renderDistanceInBlocks;
        cir.setReturnValue(fog);
    }
}
