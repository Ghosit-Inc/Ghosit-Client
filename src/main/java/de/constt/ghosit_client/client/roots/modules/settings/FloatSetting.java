package de.constt.ghosit_client.client.roots.modules.settings;

import de.constt.ghosit_client.client.roots.modules.Setting;

public class FloatSetting extends Setting<Float> {
    public FloatSetting(String name, float def) {
        super(name, def);
    }

    @Override
    public Float parse(String input) {
        try {
            return Float.parseFloat(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid float: " + input);
        }
    }
}
