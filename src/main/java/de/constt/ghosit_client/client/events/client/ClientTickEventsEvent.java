package de.constt.ghosit_client.client.events.client;

import de.constt.ghosit_client.client.config.GhositConfigData;
import de.constt.ghosit_client.client.helperFunctions.ChatHelperFunction;
import de.constt.ghosit_client.client.helperFunctions.WindowHelperFunction;
import de.constt.ghosit_client.client.roots.commands.CommandManager;
import de.constt.ghosit_client.client.roots.modules.ModuleManager;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientLifecycleEvents;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;

import java.io.IOException;

import static de.constt.ghosit_client.client.helperFunctions.WindowHelperFunction.setWindowIcon;

public class ClientTickEventsEvent {
    public static void register() {
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            String clientVersion = GhositConfigData.getConfigValue("clientVersion");
            if (client.getWindow() != null) {
                boolean watermark = Boolean.parseBoolean(
                        GhositConfigData.getConfigValue("watermark")
                );
                if(watermark) client.getWindow().setTitle(ChatHelperFunction.getPrefix(true) +" Client "+clientVersion+" | MC 1.21.1 Fabric");
            }
        });

        ClientTickEvents.START_CLIENT_TICK.register(client ->
                ModuleManager.getModules().forEach(module -> {
                    if (module.getEnabledStatus())
                        module.tick();
                })
        );

        ClientTickEvents.END_CLIENT_TICK.register(client ->
                ModuleManager.getModules().forEach(module -> {
                    if (module.getEnabledStatus())
                        module.postTick();
                })
        );

        ClientTickEvents.START_CLIENT_TICK.register(client ->
                CommandManager.getCommands().forEach(command -> {
                    if (command.getEnabledStatus()) {
                        command.tick();
                    }
                })
        );

        ClientLifecycleEvents.CLIENT_STARTED.register(client -> {
            try {
                WindowHelperFunction.setWindowIcon("/assets/ghosit_client/textures/logo-32.png");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
