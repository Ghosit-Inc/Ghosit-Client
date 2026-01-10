package de.constt.ghosit_client.client.helperFunctions;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

public final class MacroInputHelperFunction {

    private static final MinecraftClient mc = MinecraftClient.getInstance();

    public static void pressKey(KeyBinding keyBind, boolean pressed) {
        keyBind.setPressed(pressed); 
    }

    public static void sleep(long ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException ignored) {
        }
    }
}
