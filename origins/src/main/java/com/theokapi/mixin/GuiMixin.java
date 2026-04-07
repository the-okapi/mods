package com.theokapi.mixin;

import com.theokapi.Origins;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Gui.class)
abstract class GuiMixin {
    @Inject(method = "extractAirBubbles", at = @At("HEAD"), cancellable = true)
    private void extractAirBubbles(GuiGraphicsExtractor graphics, Player player, int vehicleHearts, int yLineAir, int xRight, CallbackInfo ci) {
        String origin = player.getAttached(Origins.ORIGIN_ATTACHMENT);
        if (!"merling".equals(origin) || !player.isInWater()) {
            return;
        }

        ci.cancel();
    }
}
