package com.unlimitedstuffltd.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import com.unlimitedstuffltd.screen.DebateScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.layouts.GridLayout;
import net.minecraft.client.gui.screens.PauseScreen;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PauseScreen.class)
abstract class PauseScreenMixin extends Screen {
    protected PauseScreenMixin(Component title) {
        super(title);
    }

    @Inject(method = "createPauseMenu", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/layouts/GridLayout$RowHelper;addChild(Lnet/minecraft/client/gui/layouts/LayoutElement;I)Lnet/minecraft/client/gui/layouts/LayoutElement;"))
    private void func(CallbackInfo ci, @Local(name = "helper") GridLayout.RowHelper helper) {
        helper.addChild(Button.builder(Component.literal("Debate Menu"), _ ->
            Minecraft.getInstance().setScreen(
                    new DebateScreen(Component.empty())
            )
        ).width(204).build(), 2);
    }
}
