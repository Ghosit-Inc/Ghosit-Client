package de.constt.nexsus_client.client.roots.modules.player;

import de.constt.nexsus_client.client.annotations.ModuleInfoAnnotation;
import de.constt.nexsus_client.client.roots.implementations.CategoryImplementation;
import de.constt.nexsus_client.client.roots.implementations.ModuleImplementation;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.c2s.play.ClientCommandC2SPacket;

@ModuleInfoAnnotation(
        name = "No Hunger",
        description = "Reduces the hunger taken by e.g. sprinting (Does not remove hunger)",
        category = CategoryImplementation.Categories.PLAYER,
        internalModuleName = "nohunger"
)
public class NoHungerModule extends ModuleImplementation {
    @Override
    public boolean modifyPacket(Packet<?> packet) {
        if (!(packet instanceof ClientCommandC2SPacket commandPacket)) return false;

        return commandPacket.getMode() == ClientCommandC2SPacket.Mode.START_SPRINTING;
    }
}
