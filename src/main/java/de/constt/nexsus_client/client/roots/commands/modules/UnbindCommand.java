package de.constt.nexsus_client.client.roots.commands.modules;

import de.constt.nexsus_client.client.annotations.CommandAnnotation;
import de.constt.nexsus_client.client.roots.implementations.CommandImplementation;

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
