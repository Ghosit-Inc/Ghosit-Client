package de.constt.nexsus_client.client.roots.commands.modules;

import de.constt.nexsus_client.client.annotations.CommandAnnotation;
import de.constt.nexsus_client.client.helperFunctions.ChatHelperFunction;
import de.constt.nexsus_client.client.helperFunctions.ModuleAnnotationHelperFunction;
import de.constt.nexsus_client.client.roots.implementations.CommandImplementation;
import de.constt.nexsus_client.client.roots.modules.ModuleManager;
import de.constt.nexsus_client.client.roots.modules.misc.DebuggerModule;
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
        if (parts.length < 2) {
            ChatHelperFunction.sendCSMessageNeutral("Usage: *bind <module> <key>");
        } else {
            if (parts.length < 3) {
                ChatHelperFunction.sendCSMessageNeutral("Usage: *bind <module> <key>");
            }

            String moduleArg = parts[1].toLowerCase();
            String keybindArg = parts[2].toUpperCase();

            ModuleManager.getModules().forEach(module -> {
                if (Objects.equals(ModuleAnnotationHelperFunction.getInternalModuleName(module.getClass()), moduleArg)) {
                    if(module.keyBindingCode != GLFW.GLFW_KEY_UNKNOWN) {
                        ChatHelperFunction.sendCSMessageWarning("Module is already bounded §7(§f"+ InputUtil.fromKeyCode(module.keyBindingCode, 0)+"§r§7)");
                    } else {
                        int keyCode = getKeyCode(keybindArg);

                        ModuleManager.setBind(module, keyCode);

                        ChatHelperFunction.sendCSMessageNeutral("Bound " + moduleArg + " to " + keybindArg);

                        if(ModuleManager.isEnabled(DebuggerModule.class)) {
                            ChatHelperFunction.sendCSMessageWarning("Bind: "+module.getKeybindingCode());
                        }
                    }
                }
            });

        }
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
