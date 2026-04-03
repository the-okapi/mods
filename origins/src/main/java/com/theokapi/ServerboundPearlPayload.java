package com.theokapi;

import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.Identifier;
import org.jspecify.annotations.NonNull;

public record ServerboundPearlPayload(String player) implements CustomPacketPayload {
    public static final Identifier PEARL_PAYLOAD_ID = Identifier.fromNamespaceAndPath(Origins.MOD_ID, "pearl");
    public static final CustomPacketPayload.Type<ServerboundPearlPayload> TYPE = new CustomPacketPayload.Type<>(PEARL_PAYLOAD_ID);
    public static final StreamCodec<RegistryFriendlyByteBuf, ServerboundPearlPayload> CODEC = StreamCodec.composite(ByteBufCodecs.STRING_UTF8, ServerboundPearlPayload::player, ServerboundPearlPayload::new);

    @Override
    public @NonNull Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
