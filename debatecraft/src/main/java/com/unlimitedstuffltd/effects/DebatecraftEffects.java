package com.unlimitedstuffltd.effects;

import com.unlimitedstuffltd.Debatecraft;
import com.unlimitedstuffltd.attributes.DebatecraftAttributes;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;

public class DebatecraftEffects {
    public static final Holder<MobEffect> QUESTIONING =
            Registry.registerForHolder(
                    BuiltInRegistries.MOB_EFFECT,
                    Identifier.fromNamespaceAndPath(Debatecraft.MOD_ID, "questioning"),
                    new QuestioningEffect().addAttributeModifier(DebatecraftAttributes.WORLD_DEBATE_CHANCE, Identifier.fromNamespaceAndPath(Debatecraft.MOD_ID, "questioning"), 0.2, AttributeModifier.Operation.ADD_VALUE)
            );

    public static final Holder<MobEffect> APPROVAL =
            Registry.registerForHolder(
                    BuiltInRegistries.MOB_EFFECT,
                    Identifier.fromNamespaceAndPath(Debatecraft.MOD_ID, "approval"),
                    new ApprovalEffect().addAttributeModifier(DebatecraftAttributes.WORLD_DEBATE_CHANCE, Identifier.fromNamespaceAndPath(Debatecraft.MOD_ID, "approval"), -0.2, AttributeModifier.Operation.ADD_VALUE)
            );

    public static void init() {
        Debatecraft.LOGGER.info("Initializing Effects");
    }
}
