package com.theokapi.block;

import com.theokapi.Money;
import com.theokapi.MoneySavedData;
import com.theokapi.item.MoneyItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.network.chat.Component;
import net.minecraft.server.MinecraftServer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import org.jspecify.annotations.NonNull;

import java.util.List;

public class ATMBlock extends Block {
    public ATMBlock(Properties properties) {
        super(properties);
    }

    private List<ItemStack> getItemsToGive(int dollars) {
        int one_hundred = 0;
        while (dollars >= 100) {
            one_hundred++;
            dollars -= 100;
        }
        int fifty = 0;
        while (dollars >= 50) {
            fifty++;
            dollars -= 50;
        }
        int twenty = 0;
        while (dollars >= 20) {
            twenty++;
            dollars -= 20;
        }
        int ten = 0;
        while (dollars >= 10) {
            ten++;
            dollars -= 10;
        }
        int five = 0;
        while (dollars >= 5) {
            five++;
            dollars -= 5;
        }
        int one = 0;
        while (dollars >= 1) {
            one++;
            dollars--;
        }
        return List.of(
                new ItemStack(MoneyItems.ONE_HUNDRED_DOLLARS, one_hundred),
                new ItemStack(MoneyItems.FIFTY_DOLLARS, fifty),
                new ItemStack(MoneyItems.TWENTY_DOLLARS, twenty),
                new ItemStack(MoneyItems.TEN_DOLLARS, ten),
                new ItemStack(MoneyItems.FIVE_DOLLARS, five),
                new ItemStack(MoneyItems.ONE_DOLLAR, one)
        );
    }

    private int[] canTakeAwayGive(int numItems, int count, int dollars) {
        int left = numItems % count;
        int num = (numItems - left) / count;

        return new int[]{left, num * dollars};
    }

    private int getEmptySlots(NonNullList<ItemStack> items) {
        int slotsLeft = 36;

        for (ItemStack item : items) {
            if (item != ItemStack.EMPTY) {
                slotsLeft--;
            }
        }

        return slotsLeft;
    }

    private void givePlayer(Player player, List<ItemStack> items) {
        for (ItemStack item : items) {
            int slotsLeft = getEmptySlots(player.getInventory().getNonEquipmentItems());
            if (slotsLeft > 0) {
                Money.LOGGER.info("1");
                player.addItem(item);
            } else {
                Money.LOGGER.info("0");
                player.drop(item, false);
            }
        }
    }

    @Override
    protected @NonNull InteractionResult useItemOn(@NonNull ItemStack itemStack, @NonNull BlockState state, @NonNull Level level, @NonNull BlockPos pos, @NonNull Player player, @NonNull InteractionHand hand, @NonNull BlockHitResult hitResult) {
        if (level.isClientSide()) {
            return InteractionResult.SUCCESS;
        }

        MinecraftServer server = level.getServer();

        if (server == null) {
            return InteractionResult.PASS;
        }

        MoneySavedData moneySavedData = MoneySavedData.getMoneySavedData(server);

        int dollars = moneySavedData.getDollars();
        Item item = moneySavedData.getItem();
        int count = moneySavedData.getCount();

        ItemStack activeItem = player.getActiveItem();


        player.playSound(SoundEvents.ITEM_PICKUP);

        if (moneySavedData.getItem() != activeItem.getItem() || activeItem.getCount() < count) {
            player.sendOverlayMessage(Component.literal("Exchange rate is " + count + " " + new ItemStack(item, 1).getItemName().getString() + (count > 1 ? "s " : " ") + "for $" + dollars));
            return InteractionResult.SUCCESS;
        }

        int[] takeAwayGive = canTakeAwayGive(activeItem.count(), count, dollars);

        activeItem.setCount(takeAwayGive[0]);

        givePlayer(player, getItemsToGive(takeAwayGive[1]));

        return InteractionResult.SUCCESS;
    }
}
