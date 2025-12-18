package de.constt.nexsus_client.client.helperFunctions;

import de.constt.nexsus_client.client.annotations.InfoAnnotation;
import de.constt.nexsus_client.client.roots.implementations.CategoryImplementation;

public class moduleAnnotationHelperFunction {
    public static String getName(Class<?> clazz) {
        InfoAnnotation info = clazz.getAnnotation(InfoAnnotation.class);
        if (info == null) return null;
        return info.name();
    }

    public static String getDescription(Class<?> clazz) {
        InfoAnnotation info = clazz.getAnnotation(InfoAnnotation.class);
        if (info == null) return null;
        return info.description();
    }

    public static CategoryImplementation.Categories getCategory(Class<?> clazz) {
        InfoAnnotation info = clazz.getAnnotation(InfoAnnotation.class);
        if (info == null) return null;
        return info.category();
    }
}
