package com.example.examplemod.world.item;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;

public interface ScrollableItem {

    void onScrolled(ServerPlayer player, ItemStack stack, double deltaY);
}
