package com.example.examplemod.data.recipes;

import com.example.examplemod.registries.ModItems;
import com.example.examplemod.tags.ModItemTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.world.item.Items;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends RecipeProvider {

    public ModRecipeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries);
    }

    @Override
    protected void buildRecipes(@NotNull RecipeOutput recipeOutput) {
        // コインのレシピ
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.COIN_TIER1, 1)
                .define('#', ModItemTags.EXCHANGE_BLOCKS)
                .pattern("###")
                .pattern("###")
                .pattern("###")
                .group("exchange_blocks")
                .unlockedBy("has_exchangeable_blocks", has(ModItemTags.EXCHANGE_BLOCKS))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.COIN_TIER2, 1)
                .define('#', ModItems.COIN_TIER1)
                .pattern("###")
                .pattern("###")
                .pattern("###")
                .unlockedBy("has_coin_tier1", has(ModItems.COIN_TIER1))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.COIN_TIER3, 1)
                .define('#', ModItems.COIN_TIER2)
                .pattern("###")
                .pattern("###")
                .pattern("###")
                .unlockedBy("has_coin_tier2", has(ModItems.COIN_TIER2))
                .save(recipeOutput);

        // 消耗品レシピ
        // save時にpidを指定して、既存レシピと共存させている。
        // 実装方法があっているか不明。
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, Items.IRON_PICKAXE, 1)
                .define('#', ModItems.COIN_TIER1)
                .pattern("###")
                .pattern(" # ")
                .pattern(" # ")
                .unlockedBy("has_coin_tier1", has(ModItems.COIN_TIER1))
                .save(recipeOutput, "example_mod_iron_pickaxe");

        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, Items.IRON_AXE, 1)
                .define('#', ModItems.COIN_TIER1)
                .pattern("##")
                .pattern("##")
                .pattern(" #")
                .unlockedBy("has_coin_tier1", has(ModItems.COIN_TIER1))
                .save(recipeOutput, "example_mod_iron_axe");

        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, Items.IRON_SHOVEL, 1)
                .define('#', ModItems.COIN_TIER1)
                .pattern("#")
                .pattern("#")
                .pattern("#")
                .unlockedBy("has_coin_tier1", has(ModItems.COIN_TIER1))
                .save(recipeOutput, "example_mod_iron_shovel");

        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, Items.BREAD, 1)
                .define('#', ModItems.COIN_TIER1)
                .pattern("#")
                .unlockedBy("has_coin_tier1", has(ModItems.COIN_TIER1))
                .save(recipeOutput, "example_mod_bread");

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, Items.TORCH, 64)
                .define('#', ModItems.COIN_TIER2)
                .pattern("#")
                .unlockedBy("has_coin_tier2", has(ModItems.COIN_TIER2))
                .save(recipeOutput, "example_mod_torch");

        // 報酬品レシピ
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.LONG_PICKAXE, 1)
                .define('#', ModItems.COIN_TIER3)
                .pattern("###")
                .pattern("###")
                .pattern("###")
                .unlockedBy("has_coin_tier3", has(ModItems.COIN_TIER3))
                .save(recipeOutput);
    }
}
