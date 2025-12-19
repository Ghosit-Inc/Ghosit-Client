package de.constt.nexsus_client.client.roots.commands.misc;

import de.constt.nexsus_client.client.annotations.CommandAnnotation;
import de.constt.nexsus_client.client.roots.implementations.CommandImplementation;

@CommandAnnotation(
        name = "Help",
        description = "Shows a help message",
        command = "help"
)

public class HelpCommand extends CommandImplementation {
    @Override
    public void executeCommand(String[] parts) {
        super.executeCommand(parts);
    }
}
