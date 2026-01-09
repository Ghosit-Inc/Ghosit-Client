package de.constt.ghosit_client.client.roots.modules.misc;

import de.constt.ghosit_client.client.annotations.ModuleInfoAnnotation;
import de.constt.ghosit_client.client.helperFunctions.ChatHelperFunction;
import de.constt.ghosit_client.client.roots.implementations.CategoryImplementation;
import de.constt.ghosit_client.client.roots.implementations.ModuleImplementation;
import net.minecraft.network.packet.Packet;

@ModuleInfoAnnotation(
        name = "Packet Logger",
        description = "Logs packets in chat",
        category = CategoryImplementation.Categories.MISC,
        internalModuleName = "packetlogger"
)
public class PacketLoggerModule extends ModuleImplementation {

    @Override
    public boolean modifyPacket(Packet<?> packet) {
        if (!enabled) return false;

        String packetInfo = packet.getClass().getSimpleName();
        ChatHelperFunction.sendCSMessageNeutral("Packet sent/received: " + packetInfo, true);

        return false;
    }
}
