package de.constt.ghosit_client.client.roots.modules.settings;

import de.constt.ghosit_client.client.roots.modules.Setting;

public class ColorSetting extends Setting<String> {
    public ColorSetting(String name, String defaultHex) {
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
