package de.constt.ghosit_client.client.roots.implementations;

import de.constt.ghosit_client.client.annotations.CommandAnnotation;

public class CommandImplementation {
    protected boolean enabled = false;

    public void executeCommand(String[] parts) {
        toggle();
    }

    public void tick() {}

    public void toggle() {
        enabled = !enabled;
    }

    public boolean getEnabledStatus() {
        return enabled;
    }
}