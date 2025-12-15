package de.constt.nexsus_client.client.roots.modules;

import de.constt.nexsus_client.client.helperFunctions.chatHelperFunction;
import de.constt.nexsus_client.client.roots.implementations.ModuleImplementation;
import net.minecraft.client.MinecraftClient;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.c2s.play.ClientCommandC2SPacket;
import net.minecraft.network.packet.c2s.play.PlayerMoveC2SPacket;

public class NoHungerModule extends ModuleImplementation {
    // In progress

    @Override
    public boolean modifyPacket(Packet<?> packet) {
        return false;
    }

    @Override
    public void onTick() {

    }
}
