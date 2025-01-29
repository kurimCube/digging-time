package com.example.examplemod.data.client;

import com.example.examplemod.ExampleMod;
import com.example.examplemod.registries.ModItems;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.client.model.generators.ItemModelBuilder;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredItem;

import java.util.Objects;

public class ModItemModelProvider extends ItemModelProvider {

    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, ExampleMod.MODID, existingFileHelper);
    }

    private ItemModelBuilder basicItem(DeferredItem<? extends Item> item){
        return this.basicItem(item.asItem());
    }

    private ItemModelBuilder handheldItem(DeferredItem<? extends Item> item){
        return this.handheldItem(item.asItem());
    }

    private ItemModelBuilder handheldItem(Item item){
        return this.handheldItem(Objects.requireNonNull(BuiltInRegistries.ITEM.getKey(item)));
    }
    private ItemModelBuilder handheldItem(ResourceLocation item){
        return this.getBuilder(item.toString())
                .parent(new ModelFile.UncheckedModelFile("item/handheld"))
                .texture("layer0", new ResourceLocation(item.getNamespace(), "item/" + item.getPath()));
    }

    @Override
    protected void registerModels() {
        // handheldはツールを持った時に、三人称視点で見ると持ち方が変わる
        // generated/basicItemは素材を持った時の持ち方になる
        this.handheldItem(ModItems.LONG_PICKAXE);
    }
}
