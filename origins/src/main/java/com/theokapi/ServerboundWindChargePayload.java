package com.theokapi;

import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.Identifier;
import org.jspecify.annotations.NonNull;

public record ServerboundWindChargePayload(String player) implements CustomPacketPayload {
    public static final Identifier WIND_CHARGE_PAYLOAD_ID = Identifier.fromNamespaceAndPath(Origins.MOD_ID, "wind_charge");
    public static final CustomPacketPayload.Type<ServerboundWindChargePayload> TYPE = new CustomPacketPayload.Type<>(WIND_CHARGE_PAYLOAD_ID);
    public static final StreamCodec<RegistryFriendlyByteBuf, ServerboundWindChargePayload> CODEC = StreamCodec.composite(ByteBufCodecs.STRING_UTF8, ServerboundWindChargePayload::player, ServerboundWindChargePayload::new);

    @Override
    public @NonNull Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
