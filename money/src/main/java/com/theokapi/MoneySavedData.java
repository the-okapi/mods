package com.theokapi;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.saveddata.SavedData;
import net.minecraft.world.level.saveddata.SavedDataType;

public class MoneySavedData extends SavedData {
    public MoneySavedData() {
    }

    public MoneySavedData(int d, Item i, int c) {
        dollars = d;
        item = i;
        count = c;
    }

    private int dollars = 1;
    private Item item = Items.EMERALD;
    private int count = 5;

    private static final Codec<MoneySavedData> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            Codec.INT.fieldOf("dollars").forGetter(MoneySavedData::getDollars),
            BuiltInRegistries.ITEM.byNameCodec().fieldOf("item").forGetter(MoneySavedData::getItem),
            Codec.INT.fieldOf("count").forGetter(MoneySavedData::getCount)
    ).apply(instance, MoneySavedData::new));

    private static final SavedDataType<MoneySavedData> TYPE = new SavedDataType<MoneySavedData>(
            Identifier.fromNamespaceAndPath(Money.MOD_ID, "money_saved_data"),
            MoneySavedData::new,
            CODEC,
            null
    );

    public int getDollars() {
        return dollars;
    }

    public Item getItem() {
        return item;
    }

    public int getCount() {
        return count;
    }

    public void setExchangeRate(int d, Item i, int c) {
        dollars = d;
        item = i;
        count = c;

        setDirty();
    }

    public static MoneySavedData getMoneySavedData(MinecraftServer server) {
        ServerLevel level = server.getLevel(ServerLevel.OVERWORLD);

        if (level == null) {
            return new MoneySavedData();
        }

        return level.getDataStorage().computeIfAbsent(TYPE);
    }
}
