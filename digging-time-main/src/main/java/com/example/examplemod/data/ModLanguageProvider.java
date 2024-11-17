package com.example.examplemod.data;

import com.example.examplemod.ExampleMod;
import com.example.examplemod.registries.ModItems;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.LanguageProvider;

public abstract class ModLanguageProvider extends LanguageProvider {

    public ModLanguageProvider(PackOutput output, String locale) {
        super(output, ExampleMod.MODID, locale);
    }

    @Override
    protected abstract void addTranslations();

    // ゆっくりたく 11/17: アイテム名はまだ仮、後にみんなで決めたい
    public static class En extends ModLanguageProvider{

        public En(PackOutput output) {
            super(output, "en_us");
        }

        @Override
        protected void addTranslations() {
            this.addItem(ModItems.LONG_PICKAXE, "Long Pickaxe");
        }
    }

    public static class Ja extends ModLanguageProvider {

        public Ja(PackOutput output) {
            super(output, "ja_jp");
        }

        @Override
        protected void addTranslations() {
            this.addItem(ModItems.LONG_PICKAXE, "長いツルハシ");
        }
    }
}
