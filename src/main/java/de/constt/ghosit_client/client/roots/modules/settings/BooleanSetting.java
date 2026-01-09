package de.constt.ghosit_client.client.roots.modules.settings;

import de.constt.ghosit_client.client.roots.modules.Setting;

public class BooleanSetting extends Setting<Boolean> {
    public BooleanSetting(String name, boolean def) {
        super(name, def);
    }

    @Override
    public Boolean parse(String input) {
        String lower = input.toLowerCase();
        if (lower.equals("true") || lower.equals("1") || lower.equals("yes")) return true;
        if (lower.equals("false") || lower.equals("0") || lower.equals("no")) return false;
        throw new IllegalArgumentException("Invalid boolean: " + input);
    }
}

