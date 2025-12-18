package de.constt.nexsus_client.client.roots.modules.misc;

import de.constt.nexsus_client.client.annotations.InfoAnnotation;
import de.constt.nexsus_client.client.roots.implementations.CategoryImplementation;
import de.constt.nexsus_client.client.roots.implementations.ModuleImplementation;

@InfoAnnotation(
        name = "Debugger",
        description = "Debug modules and the client",
        category = CategoryImplementation.Categories.MISC
)

public class DebuggerModule extends ModuleImplementation { }
