package com.example.examplemod.network.handlers;

import com.example.examplemod.network.payload.ItemMouseScrolledPayload;
import com.example.examplemod.world.item.ScrollableItem;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.network.handling.IPayloadContext;

public class ServerPayloadHandler {

    public static void handle(ItemMouseScrolledPayload payload, IPayloadContext context) {
        Player player = context.player();
        ItemStack mainStack = player.getItemInHand(InteractionHand.MAIN_HAND);

        if (!mainStack.isEmpty() && mainStack.getItem() instanceof ScrollableItem scrollableItem) {
            scrollableItem.onScrolled((ServerPlayer) player, mainStack, payload.value());
        }

    }
}
