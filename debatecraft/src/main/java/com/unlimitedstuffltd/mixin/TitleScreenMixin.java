package com.unlimitedstuffltd.mixin;

import com.unlimitedstuffltd.Debatecraft;
import net.minecraft.client.gui.screens.TitleScreen;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.resources.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(TitleScreen.class)
abstract class TitleScreenMixin {
    @Inject(method = "registerTextures", at = @At("HEAD"), cancellable = true)
    private static void registerTextures(TextureManager textureManager, CallbackInfo callbackInfo) {
        textureManager.registerForNextReload(Identifier.fromNamespaceAndPath(Debatecraft.MOD_ID, "textures/gui/title/minecraft.png"));
        textureManager.registerForNextReload(Identifier.fromNamespaceAndPath(Debatecraft.MOD_ID, "textures/gui/title/edition.png"));
        callbackInfo.cancel();
    }
}
