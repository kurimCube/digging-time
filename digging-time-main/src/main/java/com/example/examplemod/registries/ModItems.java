package com.example.examplemod.registries;

import com.example.examplemod.ExampleMod;
import com.example.examplemod.world.item.LongPickaxeItem;
import com.example.examplemod.world.item.SandWallPlacerItem;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.item.Tiers;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {


    // Create a Deferred Register to hold Items which will all be registered under the "examplemod" namespace
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(ExampleMod.MODID);

    // Creates a new BlockItem with the id "examplemod:example_block", combining the namespace and path
    public static final DeferredItem<BlockItem> EXAMPLE_BLOCK_ITEM = ITEMS.registerSimpleBlockItem("example_block", ModBlocks.EXAMPLE_BLOCK);

    // Creates a new food item with the id "examplemod:example_id", nutrition 1 and saturation 2
    public static final DeferredItem<Item> EXAMPLE_ITEM = ITEMS.registerSimpleItem("example_item", new Item.Properties().food(new FoodProperties.Builder()
            .alwaysEdible().nutrition(1).saturationModifier(2f).build()));

    public static final DeferredItem<LongPickaxeItem> LONG_PICKAXE = ITEMS.registerItem("long_pickaxe",
            properties -> new LongPickaxeItem(5, Tiers.IRON, properties.attributes(PickaxeItem.createAttributes(Tiers.IRON, 1.0f, -2.8f))));

    public static final DeferredItem<SandWallPlacerItem> SAND_WALL_PLACER = ITEMS.registerItem("sand_wall_placer",
            properties -> new SandWallPlacerItem(properties, 32));

    public static final DeferredItem<Item> COIN_TIER1 = ITEMS.registerSimpleItem("coin_tier1");
    public static final DeferredItem<Item> COIN_TIER2 = ITEMS.registerSimpleItem("coin_tier2");
    public static final DeferredItem<Item> COIN_TIER3 = ITEMS.registerSimpleItem("coin_tier3");


}
