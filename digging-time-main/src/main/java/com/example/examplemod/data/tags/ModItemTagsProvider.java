package com.example.examplemod.data.tags;

import com.example.examplemod.ExampleMod;
import com.example.examplemod.tags.ModItemTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModItemTagsProvider extends ItemTagsProvider {

    public ModItemTagsProvider(PackOutput output,
                               CompletableFuture<HolderLookup.Provider> lookupProvider,
                               CompletableFuture<TagLookup<Block>> blockTags,
                               @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, blockTags, ExampleMod.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        this.tag(ModItemTags.EXCHANGE_BLOCKS)
                .add(Items.COBBLESTONE,
                        Items.STONE,
                        Items.DIRT,
                        Items.GRANITE,
                        Items.DIORITE,
                        Items.ANDESITE,
                        Items.DEEPSLATE,
                        Items.COBBLED_DEEPSLATE);
    }
}
