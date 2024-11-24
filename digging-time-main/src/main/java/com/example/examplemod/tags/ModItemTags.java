package com.example.examplemod.tags;

import com.example.examplemod.ExampleMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public class ModItemTags {

    public static final TagKey<Item> EXCHANGE_BLOCKS = bind("exchange_blocks");

    private static TagKey<Item> bind(String name){
        return TagKey.create(Registries.ITEM, new ResourceLocation(ExampleMod.MODID, name));
    }
}
