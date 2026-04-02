package com.theokapi.mixin;

import com.theokapi.Origins;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Inventory.class)
abstract class InventoryMixin {
    @Inject(at = @At("HEAD"), method = "setItem", cancellable = true)
    private void setItem(int slot, ItemStack itemStack, CallbackInfo ci) {
        if (slot < 36 || slot > 40) {
            return;
        }
        Minecraft minecraft = Minecraft.getInstance();

        LocalPlayer player = minecraft.player;
        if (player == null) {
            return;
        }

        String origin = player.getAttached(Origins.ORIGIN_ATTACHMENT);

        Inventory inventory = player.getInventory();

        if ("shulk".equals(origin)) {
            if (itemStack.getItem() == Items.SHIELD && slot == 40) {
                int freeSlot = inventory.getFreeSlot();
                if (freeSlot != -1) {
                    inventory.add(freeSlot, itemStack);
                } else {
                    player.drop(itemStack, true);
                }
            }
            return;
        }

        if (("blazeborn".equals(origin) || "breezeborn".equals(origin)) && (
                itemStack.is(ItemTags.HEAD_ARMOR) ||
                        itemStack.is(ItemTags.CHEST_ARMOR) ||
                        itemStack.is(ItemTags.LEG_ARMOR) ||
                        itemStack.is(ItemTags.FOOT_ARMOR)
                )) {
            ci.cancel();
            int freeSlot = inventory.getFreeSlot();
            if (freeSlot != -1) {
                inventory.add(freeSlot, itemStack);
            } else {
                player.drop(itemStack, true);
            }
        }
        if ("elytrian".equals(origin) && itemStack.is(Origins.ELYTRIAN_NOT_ALLOWED)) {
            ci.cancel();
            int freeSlot = inventory.getFreeSlot();
            if (freeSlot != -1) {
                inventory.add(freeSlot, itemStack);
            } else {
                player.drop(itemStack, true);
            }
        }

    }
}
