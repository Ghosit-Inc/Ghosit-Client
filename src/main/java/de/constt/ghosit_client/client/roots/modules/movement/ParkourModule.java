package de.constt.ghosit_client.client.roots.modules.movement;

import de.constt.ghosit_client.client.annotations.ModuleInfoAnnotation;
import de.constt.ghosit_client.client.roots.implementations.CategoryImplementation;
import de.constt.ghosit_client.client.roots.implementations.ModuleImplementation;

@ModuleInfoAnnotation(
        name = "Parkour",
        description = "Automatically jumps for you on certain conditions",
        category = CategoryImplementation.Categories.MOVEMENT,
        internalModuleName = "parkour"
)

public class ParkourModule extends ModuleImplementation {
}
