package de.constt.ghosit_client.client.roots.settings;

import de.constt.ghosit_client.client.roots.implementations.SettingImplementation;

public class NumberSettingImplementation extends SettingImplementation<Integer> {
    public NumberSettingImplementation(String name, int def) {
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
