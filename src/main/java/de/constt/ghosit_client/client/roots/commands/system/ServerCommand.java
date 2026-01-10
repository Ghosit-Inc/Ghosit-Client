package de.constt.ghosit_client.client.roots.commands.system;

import de.constt.ghosit_client.client.annotations.CommandAnnotation;
import de.constt.ghosit_client.client.roots.implementations.CommandImplementation;

@CommandAnnotation(
        name = "Server",
        description = "Shows info about the server",
        command = "server"
)
public class ServerCommand extends CommandImplementation {

    public ServerCommand() {
        this.args = new String[]{"general-info", "plugins", "tps", "ports"};
    }
}
