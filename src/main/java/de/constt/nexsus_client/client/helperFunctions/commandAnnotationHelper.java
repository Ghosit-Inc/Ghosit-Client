package de.constt.nexsus_client.client.helperFunctions;

import de.constt.nexsus_client.client.annotations.CommandAnnotation;
import de.constt.nexsus_client.client.annotations.InfoAnnotation;
import de.constt.nexsus_client.client.roots.implementations.CategoryImplementation;

public class commandAnnotationHelper {
    public static String getName(Class<?> clazz) {
        CommandAnnotation info = clazz.getAnnotation(CommandAnnotation.class);
        if (info == null) return null;
        return info.name();
    }

    public static String getDescription(Class<?> clazz) {
        CommandAnnotation info = clazz.getAnnotation(CommandAnnotation.class);
        if (info == null) return null;
        return info.description();
    }
}
