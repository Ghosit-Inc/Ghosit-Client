package de.constt.nexsus_client.client.roots.modules.render;

import de.constt.nexsus_client.client.annotations.ModuleInfoAnnotation;
import de.constt.nexsus_client.client.roots.implementations.CategoryImplementation;
import de.constt.nexsus_client.client.roots.implementations.ModuleImplementation;
import net.minecraft.network.packet.Packet;

@ModuleInfoAnnotation(
        name = "No Wheater",
        description = "Toggle specific Wheater",
        category = CategoryImplementation.Categories.RENDER,
        internalModuleName = "nowheater"
)
public class NoWheaterModule extends ModuleImplementation {

    @Override
    public boolean modifyPacket(Packet<?> packet) {
        return false;
    }
}
