package de.constt.ghosit_client.client.roots.modules.render;


import de.constt.ghosit_client.client.annotations.ModuleInfoAnnotation;
import de.constt.ghosit_client.client.roots.implementations.CategoryImplementation;
import de.constt.ghosit_client.client.roots.implementations.ModuleImplementation;

@ModuleInfoAnnotation(
        name = "FPS (Hud)",
        description = "Shows your FPS in the HUD",
        category = CategoryImplementation.Categories.RENDER,
        internalModuleName = "fpshud"
)
public class FPSHudModule extends ModuleImplementation {
}
