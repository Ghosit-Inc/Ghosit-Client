package de.constt.ghosit_client.client.helperFunctions;

import de.constt.ghosit_client.client.annotations.CommandAnnotation;

public class CommandAnnotationHelper {
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

    public static String getCommand(Class<?> clazz) {
        CommandAnnotation info = clazz.getAnnotation(CommandAnnotation.class);
        if (info == null) return null;
        return info.command();
    }
}
