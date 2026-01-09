package de.constt.ghosit_client.client.helperFunctions;

import de.constt.ghosit_client.client.annotations.ModuleInfoAnnotation;
import de.constt.ghosit_client.client.roots.implementations.CategoryImplementation;

public class ModuleAnnotationHelperFunction {
    public static String getName(Class<?> clazz) {
        ModuleInfoAnnotation info = clazz.getAnnotation(ModuleInfoAnnotation.class);
        if (info == null) return null;
        return info.name();
    }

    public static String getDescription(Class<?> clazz) {
        ModuleInfoAnnotation info = clazz.getAnnotation(ModuleInfoAnnotation.class);
        if (info == null) return null;
        return info.description();
    }

    public static CategoryImplementation.Categories getCategory(Class<?> clazz) {
        ModuleInfoAnnotation info = clazz.getAnnotation(ModuleInfoAnnotation.class);
        if (info == null) return null;
        return info.category();
    }

    public static String getInternalModuleName(Class<?> clazz) {
        ModuleInfoAnnotation info = clazz.getAnnotation(ModuleInfoAnnotation.class);
        if (info == null) return null;
        return info.internalModuleName();
    }
}
