package com.unlimitedstuffltd.effects;

import com.unlimitedstuffltd.Debatecraft;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.effect.MobEffect;

public class DebatecraftEffects {
    public static final Holder<MobEffect> QUESTIONING =
            Registry.registerForHolder(
                    BuiltInRegistries.MOB_EFFECT,
                    Identifier.fromNamespaceAndPath(Debatecraft.MOD_ID, "questioning"),
                    new QuestioningEffect()
            );

    public static final Holder<MobEffect> APPROVAL =
            Registry.registerForHolder(
                    BuiltInRegistries.MOB_EFFECT,
                    Identifier.fromNamespaceAndPath(Debatecraft.MOD_ID, "approval"),
                    new ApprovalEffect()
            );

    public static void init() {
        Debatecraft.LOGGER.info("Initializing Effects");
    }
}
