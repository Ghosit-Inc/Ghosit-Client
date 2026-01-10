package de.constt.ghosit_client.client.roots.commands.modules;

import de.constt.ghosit_client.client.annotations.CommandAnnotation;
import de.constt.ghosit_client.client.helperFunctions.ChatHelperFunction;
import de.constt.ghosit_client.client.helperFunctions.ModuleAnnotationHelperFunction;
import de.constt.ghosit_client.client.roots.implementations.CommandImplementation;
import de.constt.ghosit_client.client.roots.modules.ModuleManager;
import de.constt.ghosit_client.client.roots.implementations.SettingImplementation;

import java.util.Objects;
import java.util.stream.Collectors;

@CommandAnnotation(
        name = "Set Setting",
        description = "Change module settings",
        command = "setsetting"
)
public class SetSettingComand extends CommandImplementation {

    @Override
    public void executeCommand(String[] parts) {
        if (parts.length < 2) {
            ChatHelperFunction.sendCSMessageNeutral(
                    "Usage: *setsetting <module> <setting> <value>",
                    false
            );
            return;
        }

        String moduleArg = parts[1].toLowerCase();

        ModuleManager.getModules().forEach(module -> {
            if (!Objects.equals(ModuleAnnotationHelperFunction
                    .getInternalModuleName(module.getClass()), moduleArg)) return;

            // If only module name is given -> show all available settings
            if (parts.length < 3) {
                String options = module.getSettings()
                        .stream()
                        .map(SettingImplementation::getName)
                        .collect(Collectors.joining(", "));

                ChatHelperFunction.sendCSMessageNeutral(
                        "Available settings for " + moduleArg + ": " + options,
                        false
                );
                return;
            }

            // If module + setting + value is given -> set the value
            String settingArg = parts[2].toUpperCase();

            SettingImplementation<?> setting = module.getSetting(settingArg);
            if (setting == null) {
                ChatHelperFunction.sendCSMessageError(
                        "Unknown setting: " + settingArg,
                        false
                );
                return;
            }

            if (parts.length < 4) {
                ChatHelperFunction.sendCSMessageNeutral(
                        "Usage: *setsetting " + moduleArg + " " + settingArg + " <value>",
                        false
                );
                return;
            }

            String valueArg = parts[3];
            Object oldValue = setting.get();

            try {
                setting.setFromString(valueArg);

                ChatHelperFunction.sendCSMessageNeutral(
                        settingArg + " changed from " + oldValue + " to " + setting.get(),
                        false
                );
            } catch (Exception e) {
                ChatHelperFunction.sendCSMessageError(
                        "Invalid value for " + settingArg,
                        false
                );
            }
        });
    }
}
