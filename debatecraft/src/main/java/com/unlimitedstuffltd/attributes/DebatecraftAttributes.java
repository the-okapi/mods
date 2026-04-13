package com.unlimitedstuffltd.attributes;

import com.unlimitedstuffltd.Debatecraft;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.RangedAttribute;

public class DebatecraftAttributes {
    public static final Holder<Attribute> WORLD_DEBATE_CHANCE = Registry.registerForHolder(
            BuiltInRegistries.ATTRIBUTE,
            Identifier.fromNamespaceAndPath(Debatecraft.MOD_ID, "world_debate_chance"),
            new RangedAttribute(
                    Identifier.fromNamespaceAndPath(Debatecraft.MOD_ID, "world_debate_change").toLanguageKey(),
                    1.0,
                    0.0,
                    1.0
            ).setSyncable(true)
    );

    public static void init() {
        Debatecraft.LOGGER.info("Initializing Attributes");
    }
}
