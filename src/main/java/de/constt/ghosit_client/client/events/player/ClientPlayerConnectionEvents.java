package de.constt.ghosit_client.client.events.player;

import de.constt.ghosit_client.client.states.AddressState;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents;

public class ClientPlayerConnectionEvents {
    public static void register() {
        ClientPlayConnectionEvents.JOIN.register((handler, sender, client) -> {
            AddressState.updateAddress();
        });

        ClientPlayConnectionEvents.DISCONNECT.register((handler, client) -> {
            AddressState.updateAddress();
        });
    }
}
