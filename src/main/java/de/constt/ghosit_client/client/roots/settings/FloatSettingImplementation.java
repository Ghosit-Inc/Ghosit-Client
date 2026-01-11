package de.constt.ghosit_client.client.roots.settings;

import de.constt.ghosit_client.client.roots.implementations.SettingImplementation;

public class FloatSettingImplementation extends SettingImplementation<Float> {
    public FloatSettingImplementation(String name, float def) {
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
