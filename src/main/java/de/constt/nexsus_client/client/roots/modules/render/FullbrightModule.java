package de.constt.nexsus_client.client.roots.modules.render;

import de.constt.nexsus_client.client.annotations.InfoAnnotation;
import de.constt.nexsus_client.client.roots.implementations.CategoryImplementation;
import de.constt.nexsus_client.client.roots.implementations.ModuleImplementation;
import net.minecraft.network.packet.Packet;

@InfoAnnotation(
        name = "Fullbright",
        description = "Changes the gamma to be always max bright",
        category = CategoryImplementation.Categories.RENDER
)
public class FullbrightModule extends ModuleImplementation {
    @Override
    public boolean modifyPacket(Packet<?> packet) {
        return false;
    }
}
