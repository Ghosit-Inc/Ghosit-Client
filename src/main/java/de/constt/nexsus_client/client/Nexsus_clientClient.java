package de.constt.nexsus_client.client;

import de.constt.nexsus_client.client.helperFunctions.chatHelperFunction;
import de.constt.nexsus_client.client.roots.implementations.ModuleImplementation;
import de.constt.nexsus_client.client.roots.modules.*;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.text.Text;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class Nexsus_clientClient implements ClientModInitializer {
    public static final Logger LOGGER = LoggerFactory.getLogger("nexsus_client");
    private static final List<ModuleImplementation> MODULES = new ArrayList<>();

    @Override
    public void onInitializeClient() {
        chatHelperFunction.setPrefix("Nexsus Client");

        MODULES.add(new FlightModule());
        MODULES.add(new FullbrightModule());
        MODULES.add(new NoFallModule());
        MODULES.add(new NoHungerModule());
        MODULES.add(new NoWheaterModule());
        MODULES.add(new PacketLoggerModule());

        ClientTickEvents.START_CLIENT_TICK.register(client ->
                MODULES.forEach(module -> {
                    if (module.getEnabledStatus())
                        module.tick();
                })
        );

        ClientTickEvents.END_CLIENT_TICK.register(client ->
                MODULES.forEach(module -> {
                    if (module.getEnabledStatus())
                        module.postTick();
                })
        );

        NoHungerModule.toggle();
    }

    /**
     * Gets a list of loaded hacks
     * @return a list of hacks
     */
    public static List<ModuleImplementation> getHacks() {
        return MODULES;
    }

    public static int numHacks() {
        return getHacks().size();
    }
}
