package com.theokapi.mixin;

import com.theokapi.Origins;
import net.minecraft.client.Minecraft;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
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

        Player player = minecraft.player;
        if (player == null) {
            return;
        }

        String origin = player.getAttached(Origins.ORIGIN_ATTACHMENT);

        if ("shulk".equals(origin)) {
            if (itemStack.getItem() == Items.SHIELD && slot == 40) {
                Origins.giveItem(player, itemStack);
            }
            return;
        }

        if (("blazeborn".equals(origin) || "breezeborn".equals(origin) || "golem".equals(origin)) && (
                itemStack.is(ItemTags.HEAD_ARMOR) ||
                        itemStack.is(ItemTags.CHEST_ARMOR) ||
                        itemStack.is(ItemTags.LEG_ARMOR) ||
                        itemStack.is(ItemTags.FOOT_ARMOR)
                )) {
            ci.cancel();
            Origins.giveItem(player, itemStack);
        }
        if ("elytrian".equals(origin) && itemStack.is(Origins.ELYTRIAN_NOT_ALLOWED)) {
            ci.cancel();
            Origins.giveItem(player, itemStack);
        }

    }
}
