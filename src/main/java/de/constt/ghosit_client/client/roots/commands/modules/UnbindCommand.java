package de.constt.ghosit_client.client.roots.commands.modules;

import de.constt.ghosit_client.client.annotations.CommandAnnotation;
import de.constt.ghosit_client.client.roots.implementations.CommandImplementation;

@CommandAnnotation(
        name = "Unbind",
        description = "Unbinds a specific module from a keybind",
        command = "unbind"
)

public class UnbindCommand extends CommandImplementation {
    @Override
    public void executeCommand(String[] parts) {
        super.executeCommand(parts);
    }
}
