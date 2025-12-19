package de.constt.nexsus_client.client.roots.commands;

import de.constt.nexsus_client.client.roots.commands.misc.HelpCommand;
import de.constt.nexsus_client.client.roots.commands.modules.BindCommand;
import de.constt.nexsus_client.client.roots.commands.modules.ToggleCommand;
import de.constt.nexsus_client.client.roots.commands.modules.UnbindCommand;
import de.constt.nexsus_client.client.roots.implementations.CommandImplementation;

import java.util.ArrayList;
import java.util.List;

public class CommandManager {
    private static final List<CommandImplementation> COMMANDS = new ArrayList<>();

    public static void init() {
        COMMANDS.add(new BindCommand());
        COMMANDS.add(new HelpCommand());
        COMMANDS.add(new UnbindCommand());
        COMMANDS.add(new ToggleCommand());
    }

    /**
     * Gets a list of loaded commands
     * @return a list of commands
     */
    public static List<CommandImplementation> getCommands() {
        return COMMANDS;
    }

    /**
     * Get the command from {@code COMMANDS} that matches the class {@code commandClass}
     * @param 	commandClass the command to get
     * @return	the matching command from {@code COMMANDS}
     * @param 	<T> extends CommandImplementation
     */
    public static <T extends CommandImplementation> T getCommand(Class<T> commandClass) {
        for (var command : getCommands()) {
            if (command.getClass() == commandClass) {
                return commandClass.cast(command);
            }
        }
        return null;
    }


    public static int numCommands() {
        return getCommands().size();
    }
}
