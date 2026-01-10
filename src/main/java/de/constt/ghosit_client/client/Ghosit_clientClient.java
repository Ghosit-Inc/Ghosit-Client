package de.constt.ghosit_client.client;

import de.constt.ghosit_client.client.config.GhositConfigData;
import de.constt.ghosit_client.client.discordRPC.DiscordIPCCore;
import de.constt.ghosit_client.client.events.chat.ClientReciveMessageEvent;
import de.constt.ghosit_client.client.events.client.ClientTickEventsEvent;
import de.constt.ghosit_client.client.events.client.InputEventHandler;
import de.constt.ghosit_client.client.events.hud.HudRenderCallbackEvent;
import de.constt.ghosit_client.client.events.player.ClientPlayerConnectionEvents;
import de.constt.ghosit_client.client.events.render.ScreenRenderCallbackEvent;
import de.constt.ghosit_client.client.helperFunctions.ChatHelperFunction;
import de.constt.ghosit_client.client.helperFunctions.ConfigManagerHelperFunction;
import de.constt.ghosit_client.client.helperFunctions.WindowHelperFunction;
import de.constt.ghosit_client.client.roots.commands.CommandManager;
import de.constt.ghosit_client.client.roots.modules.ModuleManager;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.ModInitializer;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.texture.NativeImage;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWImage;
import org.lwjgl.system.MemoryStack;
import org.lwjgl.system.MemoryUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

import static de.constt.ghosit_client.client.helperFunctions.WindowHelperFunction.setWindowIcon;

public class Ghosit_clientClient implements ClientModInitializer {
    public static final Logger C_LOGGER = LoggerFactory.getLogger("ghosit_client");
    public static final String MOD_ID = "ghosit_client";

    @Override
    public void onInitializeClient() {
        // CONFIG: Load
        GhositConfigData.init();

        // CHAT HELPER FUNCTION: Set Prefix
        ChatHelperFunction.setPrefix("Ghosit");

        // DISCORD IPC: Start
        DiscordIPCCore.start();

        // EVENTS REGISTRATION
        ClientReciveMessageEvent.register();
        HudRenderCallbackEvent.register();
        ClientPlayerConnectionEvents.register();
        ClientTickEventsEvent.register();
        ScreenRenderCallbackEvent.register();
        InputEventHandler.register();

        // ModuleManager
        ModuleManager.init();

        // CONFIG MANAGER HELPER FUNCTION: LOAD ALL KEYBINDS
        ConfigManagerHelperFunction.loadAllKeybinds();

        // CommandManager
        CommandManager.init();

        // Temporarily toggle Modules
        ModuleManager.tempToggleModules();
    }

    /*
    public static void setWindowIcon(String pathInJar) throws IOException {
        try (
                InputStream inputStream = MinecraftClient.class.getResourceAsStream(pathInJar);
                MemoryStack stack = MemoryStack.stackPush()
        ) {
            if (inputStream == null) throw new IOException("missing: " + pathInJar);
            NativeImage image = NativeImage.read(inputStream);
            ByteBuffer pixelbuffer = MemoryUtil.memAlloc(image.getWidth() * image.getHeight() * 4);
            pixelbuffer.asIntBuffer().put(image.copyPixelsRgba());

            GLFWImage.Buffer buf = GLFWImage.malloc(1, stack);
            buf.position(0);
            buf.width(image.getWidth());
            buf.height(image.getHeight());
            buf.pixels(pixelbuffer);

            GLFW.glfwSetWindowIcon(MinecraftClient.getInstance().getWindow().getHandle(), buf.position(0));
            image.close();
            MemoryUtil.memFree(pixelbuffer);
        }
    }

     */
}
