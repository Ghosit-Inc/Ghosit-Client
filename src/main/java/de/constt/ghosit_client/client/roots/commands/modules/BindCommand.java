package de.constt.ghosit_client.client.roots.commands.modules;

import de.constt.ghosit_client.client.annotations.CommandAnnotation;
import de.constt.ghosit_client.client.helperFunctions.ChatHelperFunction;
import de.constt.ghosit_client.client.helperFunctions.ConfigManagerHelperFunction;
import de.constt.ghosit_client.client.helperFunctions.ModuleAnnotationHelperFunction;
import de.constt.ghosit_client.client.roots.implementations.CommandImplementation;
import de.constt.ghosit_client.client.roots.modules.ModuleManager;
import de.constt.ghosit_client.client.roots.modules.misc.DebuggerModule;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

import java.util.Objects;

@CommandAnnotation(
        name = "Bind",
        description = "Binds a specific module to a keybind",
        command = "bind"
)

public class BindCommand extends CommandImplementation {
    @Override
    public void executeCommand(String[] parts) {
        if (parts == null || parts.length < 2) {
            ChatHelperFunction.sendCSMessageError(
                    "Usage: *bind <module> [key]", false
            );
            return;
        }

        String moduleArg = parts[1].toLowerCase();

        ModuleManager.getModules().forEach(module -> {
            if (!Objects.equals(
                    ModuleAnnotationHelperFunction.getInternalModuleName(module.getClass()),
                    moduleArg
            )) return;

            // Case: key not provided
            if (parts.length < 3) {
                ChatHelperFunction.sendCSMessageNeutral(
                        "Provide a key to bind. Example: *bind " + moduleArg + " r" , false
                );

                if (module.keyBindingCode != GLFW.GLFW_KEY_UNKNOWN) {
                    ChatHelperFunction.sendCSMessageNeutral(
                            moduleArg + " is already bounded to " +
                                    InputUtil.fromKeyCode(module.keyBindingCode, 0).getLocalizedText().getString(),
                            false
                    );
                }
                return;
            }

            // Case: key provided
            String keybindArg = parts[2].toUpperCase();
            int keyCode = getKeyCode(keybindArg);

            if (keyCode == GLFW.GLFW_KEY_UNKNOWN) {
                ChatHelperFunction.sendCSMessageError("Invalid key: " + keybindArg, false);
                return;
            }

            int oldKey = module.keyBindingCode;
            module.keyBindingCode = keyCode;
            ConfigManagerHelperFunction.addKeybind(module, keyCode);

            ChatHelperFunction.sendCSMessageNeutral(
                    "Bound " + moduleArg + " to " + keybindArg, false
            );

            if (ModuleManager.isEnabled(DebuggerModule.class)) {
                ChatHelperFunction.sendCSMessageWarning(
                        "Bind code: " + keyCode, true
                );
            }
        });
    }


    private static int getKeyCode(String keybindArg) {
        int keyCode = 0;

        if (keybindArg.length() == 1) {
            char c = keybindArg.charAt(0);
            if (c >= 'A' && c <= 'Z') keyCode = GLFW.GLFW_KEY_A + (c - 'A');
            else if (c >= '0' && c <= '9') keyCode = GLFW.GLFW_KEY_0 + (c - '0');
        } else if (keybindArg.equals("SPACE")) keyCode = GLFW.GLFW_KEY_SPACE;

        else if (keybindArg.equals("SHIFT")) keyCode = GLFW.GLFW_KEY_LEFT_SHIFT;
        return keyCode;
    }
}

