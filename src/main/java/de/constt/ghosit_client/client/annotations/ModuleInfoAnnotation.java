package de.constt.ghosit_client.client.annotations;

import de.constt.ghosit_client.client.roots.implementations.CategoryImplementation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface ModuleInfoAnnotation {
    String name();
    String description();
    CategoryImplementation.Categories category();
    String internalModuleName();
}
