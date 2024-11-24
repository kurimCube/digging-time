package com.example.examplemod.tags;

import com.example.examplemod.ExampleMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

public class ModBlockTags {

    private static TagKey<Block> bind(String name){
        return TagKey.create(Registries.BLOCK, new ResourceLocation(ExampleMod.MODID, name));
    }
}
