package de.constt.nexsus_client.client.roots.modules.misc;

import de.constt.nexsus_client.client.annotations.InfoAnnotation;
import de.constt.nexsus_client.client.helperFunctions.chatHelperFunction;
import de.constt.nexsus_client.client.roots.implementations.CategoryImplementation;
import de.constt.nexsus_client.client.roots.implementations.ModuleImplementation;
import net.minecraft.network.packet.Packet;

@InfoAnnotation(
        name = "Packet-Logger",
        description = "Logs packets in chat",
        category = CategoryImplementation.Categories.MISC
)
public class PacketLoggerModule extends ModuleImplementation {

    @Override
    public boolean modifyPacket(Packet<?> packet) {
        if (!enabled) return false;

        String packetInfo = packet.getClass().getSimpleName();
        chatHelperFunction.sendCSMessageNeutral("Packet sent/received: " + packetInfo);

        return false;
    }
}
