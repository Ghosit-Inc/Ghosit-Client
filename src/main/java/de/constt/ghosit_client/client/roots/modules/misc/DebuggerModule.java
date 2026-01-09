package de.constt.ghosit_client.client.roots.modules.misc;

import de.constt.ghosit_client.client.annotations.ModuleInfoAnnotation;
import de.constt.ghosit_client.client.roots.implementations.CategoryImplementation;
import de.constt.ghosit_client.client.roots.implementations.ModuleImplementation;

import java.awt.*;

@ModuleInfoAnnotation(
        name = "Debugger",
        description = "Debug modules and the client",
        category = CategoryImplementation.Categories.MISC,
        internalModuleName = "debugger"
)

public class DebuggerModule extends ModuleImplementation { }
