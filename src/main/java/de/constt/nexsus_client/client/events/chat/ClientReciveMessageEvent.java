package de.constt.nexsus_client.client.events.chat;

import de.constt.nexsus_client.client.helperFunctions.chatHelperFunction;
import net.fabricmc.fabric.api.client.message.v1.ClientSendMessageEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;

public class ClientReciveMessageEvent {

    public static void register() {
        ClientSendMessageEvents.ALLOW_CHAT.register((message) -> {
            ClientPlayerEntity player = MinecraftClient.getInstance().player;
            assert player != null;
            if (!message.startsWith("/") && message.startsWith("*")) {
                if(message.equals("*help")) {
                    chatHelperFunction.sendCSMessageNeutral("Help message");
                    return false;
                }
            }
            return true;
        });
    }
}