package de.constt.nexsus_client.client.roots.modules.movement;

import de.constt.nexsus_client.client.annotations.InfoAnnotation;
import de.constt.nexsus_client.client.roots.implementations.CategoryImplementation;
import de.constt.nexsus_client.client.roots.implementations.ModuleImplementation;
import net.minecraft.network.packet.Packet;

@InfoAnnotation(
        name = "Flight",
        description = "Enables flying for the player",
        category = CategoryImplementation.Categories.MOVEMENT
)
public class FlightModule extends ModuleImplementation {
}
