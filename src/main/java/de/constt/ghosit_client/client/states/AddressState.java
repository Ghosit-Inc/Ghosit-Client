package de.constt.ghosit_client.client.states;

import net.minecraft.client.MinecraftClient;

public final class AddressState {

    public static String address = "Unknown";

    public static void updateAddress() {
        MinecraftClient mc = MinecraftClient.getInstance();

        if (mc.getServer() != null && mc.getServer().isSingleplayer()) {
            address = "Singleplayer";
        } else if (mc.getCurrentServerEntry() != null) {
            address = mc.getCurrentServerEntry().address;
        } else {
            address = "Unknown";
        }
    }

    public static String getAddress() {
        return address;
    }

}
