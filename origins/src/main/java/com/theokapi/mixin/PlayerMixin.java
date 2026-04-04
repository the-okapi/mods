package com.theokapi.mixin;

import com.theokapi.Origins;
import net.minecraft.world.entity.Avatar;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;


@Mixin(Player.class)
abstract class PlayerMixin extends Avatar {
    protected PlayerMixin(EntityType<? extends LivingEntity> type, Level level) {
        super(type, level);
    }

    @Inject(method = "tick", at = @At("TAIL"))
    public void tick(CallbackInfo ci) {
        LivingEntity entity = this;
        String origin = entity.getAttached(Origins.ORIGIN_ATTACHMENT);
        if ("merling".equals(origin) && entity.isInWater()) {
            Vec3 deltaMovement = this.getDeltaMovement();
            if (deltaMovement.y < 0) {
                this.setDeltaMovement(deltaMovement.x, 0.0, deltaMovement.z);
            }
        }
    }
}
