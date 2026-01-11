package de.constt.ghosit_client.client.roots.settings;

import de.constt.ghosit_client.client.roots.implementations.SettingImplementation;

public class ColorSettingImplementation extends SettingImplementation<String> {
    public ColorSettingImplementation(String name, String defaultHex) {
        super(name, defaultHex);
    }

    @Override
    public String parse(String input) {
        if (!input.matches("^#?[0-9A-Fa-f]{6}$")) {
            throw new IllegalArgumentException("Invalid hex color: " + input);
        }
        // Ensure leading # for consistency
        return input.startsWith("#") ? input : "#" + input;
    }
}
