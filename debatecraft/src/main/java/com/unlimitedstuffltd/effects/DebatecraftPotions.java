package com.unlimitedstuffltd.effects;

import com.unlimitedstuffltd.Debatecraft;
import net.fabricmc.fabric.api.registry.FabricPotionBrewingBuilder;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.Potions;

public class DebatecraftPotions {
    public static final Holder<Potion> QUESTIONING_POTION =
            Registry.registerForHolder(
                    BuiltInRegistries.POTION,
                    Identifier.fromNamespaceAndPath(Debatecraft.MOD_ID, "questioning"),
                    new Potion("questioning",
                            new MobEffectInstance(
                                    DebatecraftEffects.QUESTIONING,
                                    3600,
                                    0
                            )
                    )
            );
/*
    public static void init() {
        FabricPotionBrewingBuilder.BUILD.register(builder -> {
           builder.addMix(Potions.WATER, Items.)
        });
    }*/
}
