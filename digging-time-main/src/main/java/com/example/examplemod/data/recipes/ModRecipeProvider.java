package com.example.examplemod.data.recipes;

import com.example.examplemod.registries.ModItems;
import com.example.examplemod.tags.ModItemTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;

import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends RecipeProvider {

    public ModRecipeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries);
    }

    @Override
    protected void buildRecipes(RecipeOutput recipeOutput) {
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.COIN, 1)
                .define('#', ModItemTags.EXCHANGE_BLOCKS)
                .pattern("###")
                .pattern("###")
                .pattern("###")
                .group("exchange_blocks")
                .unlockedBy("has_exchangeable_blocks", has(ModItemTags.EXCHANGE_BLOCKS))
                .save(recipeOutput);


    }
}
