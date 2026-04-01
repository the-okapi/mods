package com.theokapi.item;

import com.theokapi.Origins;
import com.theokapi.OriginsFunctions;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import org.jspecify.annotations.NonNull;


public class OrbItem extends Item {
    private final String origin;

    public OrbItem(Properties properties, String o) {
        origin = o;
        super(properties);
    }

    @Override
    public @NonNull InteractionResult use(@NonNull Level level, Player player, @NonNull InteractionHand hand) {
        player.playSound(SoundEvents.END_PORTAL_SPAWN);

        OriginsFunctions.callCleanupFunction(player.getAttached(Origins.ORIGIN_ATTACHMENT), player);

        player.setAttached(Origins.ORIGIN_ATTACHMENT, origin);

        OriginsFunctions.callInitFunction(origin, player);

        player.getActiveItem().setCount(0);

        return InteractionResult.SUCCESS;
    }
}
