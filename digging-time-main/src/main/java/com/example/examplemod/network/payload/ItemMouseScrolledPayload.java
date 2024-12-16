package com.example.examplemod.network.payload;

import com.example.examplemod.ExampleMod;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;

/**
 * マウスホイールをくるくるした時に呼び出すペイロード
 * アイテム関連の設定(例えばアイテムコンポーネント)を変えたい場合、クライアントからサーバーにパケットを送信する必要がある
 *
 * @param value
 */
public record ItemMouseScrolledPayload(double value) implements CustomPacketPayload {

    public static final CustomPacketPayload.Type<ItemMouseScrolledPayload> TYPE = new Type<>(new ResourceLocation(ExampleMod.MODID, "item_mouse_scrolled"));
    public static final StreamCodec<RegistryFriendlyByteBuf, ItemMouseScrolledPayload> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.DOUBLE,
            ItemMouseScrolledPayload::value,
            ItemMouseScrolledPayload::new
    );

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
