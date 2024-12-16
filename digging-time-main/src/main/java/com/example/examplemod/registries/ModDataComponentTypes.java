package com.example.examplemod.registries;

import com.example.examplemod.ExampleMod;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.util.ExtraCodecs;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

/**
 * データコンポーネント 今までのアイテムnbtの置き換えバージョン
 */
public class ModDataComponentTypes {

    public static final DeferredRegister.DataComponents DATA_COMPONENT_TYPES = DeferredRegister.createDataComponents(ExampleMod.MODID);

    public static final DeferredHolder<DataComponentType<?>, DataComponentType<Integer>> WALL_HEIGHT = DATA_COMPONENT_TYPES.registerComponentType("wall_height",
            builder -> builder.persistent(ExtraCodecs.POSITIVE_INT).networkSynchronized(ByteBufCodecs.VAR_INT));

}
