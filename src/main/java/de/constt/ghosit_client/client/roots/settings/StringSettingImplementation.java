package de.constt.ghosit_client.client.roots.settings;

import de.constt.ghosit_client.client.roots.implementations.SettingImplementation;

public class StringSettingImplementation extends SettingImplementation<String> {
    public StringSettingImplementation(String name, String def) {
        super(name, def);
    }

    @Override
    public String parse(String input) {
        if (input == null) {
            throw new IllegalArgumentException("String cannot be null");
        }
        return input;
    }
}
