package de.constt.ghosit_client.client.roots.modules.settings;

import de.constt.ghosit_client.client.roots.modules.Setting;

public class NumberSetting extends Setting<Integer> {
    public NumberSetting(String name, int def) {
        super(name, def);
    }

    @Override
    public Integer parse(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid number: " + input);
        }
    }
}
