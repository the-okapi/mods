package com.unlimitedstuffltd.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.unlimitedstuffltd.attributes.DebatecraftAttributes;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(Player.class)
abstract class PlayerMixin {
    @ModifyReturnValue(method = "createAttributes", at = @At("RETURN"))
    private static AttributeSupplier.Builder createAttributes(AttributeSupplier.Builder original) {
        return original.add(DebatecraftAttributes.WORLD_DEBATE_CHANCE, 0.4);
    }
}
