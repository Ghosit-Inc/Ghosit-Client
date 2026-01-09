package de.constt.ghosit_client.client.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface CommandAnnotation {
    String name();
    String description();
    String command();
}
