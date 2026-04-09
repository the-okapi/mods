package com.unlimitedstuffltd.mixin;

import com.unlimitedstuffltd.Debatecraft;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.LogoRenderer;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.resources.Identifier;
import net.minecraft.util.ARGB;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LogoRenderer.class)
abstract class LogoRendererMixin {
    @Shadow
    @Final
    private boolean keepLogoThroughFade;

    @Inject(method = "extractRenderState(Lnet/minecraft/client/gui/GuiGraphicsExtractor;IFI)V", at = @At("HEAD"), cancellable = true)
    private void extractRenderState(GuiGraphicsExtractor graphics, int width, float alpha, int heightOffset, CallbackInfo ci) {
        int logoX = width / 2 - 128;
        float effectiveAlpha = this.keepLogoThroughFade ? 1.0f : alpha;
        int color = ARGB.white(effectiveAlpha);
        graphics.blit(
                RenderPipelines.GUI_TEXTURED, Identifier.fromNamespaceAndPath(Debatecraft.MOD_ID, "textures/gui/screen/minecraft.png"), logoX, heightOffset, 0.0f, 0.0f, 256, 44, 256, 64, color
        );
        ci.cancel();
    }
}
