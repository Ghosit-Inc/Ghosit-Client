package de.constt.ghosit_client.client.roots.modules.events;

import de.constt.ghosit_client.client.annotations.ModuleInfoAnnotation;
import de.constt.ghosit_client.client.roots.implementations.CategoryImplementation;
import de.constt.ghosit_client.client.roots.implementations.ModuleImplementation;

@ModuleInfoAnnotation(
        name = "Chat Calculator",
        description = "Automatically calculates equations in chat",
        internalModuleName = "chatcalculator",
        category = CategoryImplementation.Categories.EVENTS
)

public class ChatCalculatorModule extends ModuleImplementation {
}
