package com.theokapi.item;

import com.theokapi.Origins;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

import java.util.function.BiConsumer;

public class OrbItem extends Item {
    private final String origin;
    private final BiConsumer<Level, Player> action;

    public OrbItem(Properties properties, String o, @Nullable BiConsumer<Level, Player> a) {
        origin = o;
        action = a;
        super(properties);
    }

    @Override
    public @NonNull InteractionResult use(@NonNull Level level, Player player, @NonNull InteractionHand hand) {
        player.playSound(SoundEvents.END_PORTAL_SPAWN);

        player.setAttached(Origins.ORIGIN_ATTACHMENT, origin);

        if (action != null) {
            action.accept(level, player);
        }

        player.getActiveItem().setCount(0);

        return InteractionResult.SUCCESS;
    }
}
