package com.theokapi.datagen;

import com.theokapi.Origins;
import com.theokapi.item.OriginsItems;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.world.item.Items;
import org.jspecify.annotations.NonNull;

import java.util.concurrent.CompletableFuture;

public class ItemTagProvider extends FabricTagsProvider.ItemTagsProvider {
    public ItemTagProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registryLookupFuture) {
        super(output, registryLookupFuture);
    }

    @Override
    protected void addTags(HolderLookup.@NonNull Provider provider) {
        valueLookupBuilder(Origins.ORBS)
                .add(OriginsItems.BLAZEBORN_ORB)
                .add(OriginsItems.AVIAN_ORB)
                .add(OriginsItems.ENDERIAN_ORB)
                .add(OriginsItems.ELYTRIAN_ORB)
                .add(OriginsItems.MERLING_ORB)
                .add(OriginsItems.WARDEN_ORB)
                .add(OriginsItems.ARACHNID_ORB)
                .add(OriginsItems.FELINE_ORB)
                .add(OriginsItems.SHULK_ORB)
                .add(OriginsItems.BREEZEBORN_ORB)
                .setReplace(true);

        valueLookupBuilder(Origins.ELYTRIAN_NOT_ALLOWED)
                .add(Items.IRON_HELMET)
                .add(Items.IRON_CHESTPLATE)
                .add(Items.IRON_LEGGINGS)
                .add(Items.IRON_BOOTS)
                .add(Items.DIAMOND_HELMET)
                .add(Items.DIAMOND_CHESTPLATE)
                .add(Items.DIAMOND_LEGGINGS)
                .add(Items.DIAMOND_BOOTS)
                .add(Items.NETHERITE_HELMET)
                .add(Items.NETHERITE_CHESTPLATE)
                .add(Items.NETHERITE_LEGGINGS)
                .add(Items.NETHERITE_BOOTS)
                .setReplace(true);

        valueLookupBuilder(Origins.FOODS)
                .add(Items.APPLE)
                .add(Items.BAKED_POTATO)
                .add(Items.BEETROOT)
                .add(Items.BEETROOT_SOUP)
                .add(Items.BREAD)
                .add(Items.CARROT)
                .add(Items.CHORUS_FRUIT)
                .add(Items.COOKED_CHICKEN)
                .add(Items.COOKED_COD)
                .add(Items.COOKED_MUTTON)
                .add(Items.COOKED_PORKCHOP)
                .add(Items.COOKED_RABBIT)
                .add(Items.COOKED_SALMON)
                .add(Items.COOKIE)
                .add(Items.DRIED_KELP)
                .add(Items.GLOW_BERRIES)
                .add(Items.MELON_SLICE)
                .add(Items.MUSHROOM_STEW)
                .add(Items.POISONOUS_POTATO)
                .add(Items.POTATO)
                .add(Items.PUFFERFISH)
                .add(Items.PUFFERFISH)
                .add(Items.RABBIT_STEW)
                .add(Items.BEEF)
                .add(Items.CHICKEN)
                .add(Items.COD)
                .add(Items.MUTTON)
                .add(Items.PORKCHOP)
                .add(Items.RABBIT)
                .add(Items.SALMON)
                .add(Items.ROTTEN_FLESH)
                .add(Items.COOKED_BEEF)
                .add(Items.SUSPICIOUS_STEW)
                .add(Items.SWEET_BERRIES)
                .add(Items.TROPICAL_FISH)
                .setReplace(true);
    }
}