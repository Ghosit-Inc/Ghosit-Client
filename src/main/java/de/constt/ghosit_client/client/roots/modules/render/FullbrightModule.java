package de.constt.ghosit_client.client.roots.modules.render;

import de.constt.ghosit_client.client.annotations.ModuleInfoAnnotation;
import de.constt.ghosit_client.client.roots.implementations.CategoryImplementation;
import de.constt.ghosit_client.client.roots.implementations.ModuleImplementation;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;

@ModuleInfoAnnotation(
        name = "Fullbright",
        description = "Changes the gamma to be always max bright",
        category = CategoryImplementation.Categories.RENDER,
        internalModuleName = "fullbright"
)
public class FullbrightModule extends ModuleImplementation {
    @Override
    public void onEnable() {
        enableNightVision();
        super.onEnable();
    }

    @Override
    public void onDisable() {
        disableNightVision();
        super.onDisable();
    }

    public static void enableNightVision() {
        MinecraftClient client = MinecraftClient.getInstance();
        if (client.player != null) {
            client.player.addStatusEffect(new StatusEffectInstance(StatusEffects.NIGHT_VISION, 1_000_000, 0, false, false, false));
        }
    }

    public static void disableNightVision() {
        MinecraftClient client = MinecraftClient.getInstance();
        if (client.player != null) {
            client.player.removeStatusEffect(StatusEffects.NIGHT_VISION);
        }
    }
}
