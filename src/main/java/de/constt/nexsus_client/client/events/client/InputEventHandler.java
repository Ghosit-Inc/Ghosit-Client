package de.constt.nexsus_client.client.events.client;

import de.constt.nexsus_client.client.roots.implementations.ModuleImplementation;
import de.constt.nexsus_client.client.roots.modules.ModuleManager;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.MinecraftClient;
import org.lwjgl.glfw.GLFW;

import java.util.HashMap;
import java.util.Map;

public class InputEventHandler {

    private static final Map<ModuleImplementation, Boolean> keyStates = new HashMap<>();

    public static void register() {
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            long window = MinecraftClient.getInstance().getWindow().getHandle();

            for (ModuleImplementation module : ModuleManager.getModules()) {
                int key = module.keyBindingCode;

                if (key <= 0) continue;

                boolean isPressed = GLFW.glfwGetKey(window, key) == GLFW.GLFW_PRESS;
                boolean wasPressed = keyStates.getOrDefault(module, false);

                if (isPressed && !wasPressed) {
                    module.toggle();
                }

                keyStates.put(module, isPressed);
            }
        });
    }
}
