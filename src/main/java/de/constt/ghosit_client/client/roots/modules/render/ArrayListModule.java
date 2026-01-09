package de.constt.ghosit_client.client.roots.modules.render;

import de.constt.ghosit_client.client.annotations.ModuleInfoAnnotation;
import de.constt.ghosit_client.client.roots.implementations.CategoryImplementation;
import de.constt.ghosit_client.client.roots.implementations.ModuleImplementation;

@ModuleInfoAnnotation(
        name = "Array List",
        description = "Shows toggled modules on your screen",
        internalModuleName = "arraylist",
        category = CategoryImplementation.Categories.RENDER
)

public class ArrayListModule extends ModuleImplementation {
}
