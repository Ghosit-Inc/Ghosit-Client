package de.constt.ghosit_client.client.utilities;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class Ghosit_clientEnglishLangProvider extends FabricLanguageProvider {
    public Ghosit_clientEnglishLangProvider(FabricDataOutput dataGenerator, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataGenerator, "en_us", registryLookup);
    }

    @Override
    public void generateTranslations(RegistryWrapper.WrapperLookup registryLookup, TranslationBuilder translationBuilder) {
        translationBuilder.add("modules.title", "Ghosit Client");

        translationBuilder.add("key.ghosit_client.menu", "Open menu");
    }
}
