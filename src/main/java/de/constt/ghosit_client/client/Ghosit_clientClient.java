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
import de.constt.ghosit_client.client.roots.commands.CommandManager;
import de.constt.ghosit_client.client.roots.modules.ModuleManager;
import net.fabricmc.api.ClientModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Ghosit_clientClient implements ClientModInitializer {
    public static final Logger LOGGER = LoggerFactory.getLogger("ghosit_client");
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
}
