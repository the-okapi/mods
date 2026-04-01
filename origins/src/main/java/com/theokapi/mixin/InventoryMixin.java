package com.theokapi.mixin;

import com.theokapi.Origins;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Inventory.class)
abstract class InventoryMixin {
    @Inject(at = @At("HEAD"), method = "setItem", cancellable = true)
    private void setItem(int slot, ItemStack itemStack, CallbackInfo ci) {
        if (slot < 36 || slot > 39) {
            return;
        }
        Minecraft minecraft = Minecraft.getInstance();

        LocalPlayer player = minecraft.player;
        if (player == null) {
            return;
        }

        String origin = player.getAttached(Origins.ORIGIN_ATTACHMENT);
        if (!"blazeborn".equals(origin) || !(
                itemStack.is(ItemTags.HEAD_ARMOR) ||
                        itemStack.is(ItemTags.CHEST_ARMOR) ||
                        itemStack.is(ItemTags.LEG_ARMOR) ||
                        itemStack.is(ItemTags.FOOT_ARMOR)
                )) {
            return;
        }

        ci.cancel();

        player.addItem(itemStack);
    }
}
