package de.constt.nexsus_client.client.roots.commands.modules;

import de.constt.nexsus_client.client.annotations.CommandAnnotation;
import de.constt.nexsus_client.client.helperFunctions.ChatHelperFunction;
import de.constt.nexsus_client.client.helperFunctions.ModuleAnnotationHelperFunction;
import de.constt.nexsus_client.client.roots.implementations.CommandImplementation;
import de.constt.nexsus_client.client.roots.modules.ModuleManager;

import java.util.Objects;

@CommandAnnotation(
        name = "Toggle",
        description = "Toggles a specificed module",
        command = "toggle"
)

public class ToggleCommand extends CommandImplementation {
    @Override
    public void executeCommand(String[] parts) {
        super.executeCommand(parts);
        String moduleArg = parts[1].toLowerCase();

        ModuleManager.getModules().forEach(module -> {
            if(Objects.equals(ModuleAnnotationHelperFunction.getInternalModuleName(module.getClass()), moduleArg)) {
                ModuleManager.toggle(module.getClass());
                if(module.getEnabledStatus()) {
                    ChatHelperFunction.sendCSMessageNeutral(ModuleAnnotationHelperFunction.getName(module.getClass())+" §7(§aon§r§7)");
                } else {
                    ChatHelperFunction.sendCSMessageNeutral(ModuleAnnotationHelperFunction.getName(module.getClass())+" §7(§coff§r§7)");
                }
            };
        });
    }
}
