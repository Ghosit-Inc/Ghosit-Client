package de.constt.nexsus_client.client.roots.modules;

import de.constt.nexsus_client.client.roots.implementations.ModuleImplementation;
import net.minecraft.network.packet.Packet;

public class PacketLoggerModule extends ModuleImplementation {

    @Override
    public boolean modifyPacket(Packet<?> packet) {
        return false;
    }

    @Override
    public void onTick() {

    }
}
