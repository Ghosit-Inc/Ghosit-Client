package de.constt.ghosit_client.client.roots.modules.world;

import de.constt.ghosit_client.client.annotations.ModuleInfoAnnotation;
import de.constt.ghosit_client.client.mixins.PlayerMoveC2SPacketAccessor;
import de.constt.ghosit_client.client.roots.implementations.CategoryImplementation;
import de.constt.ghosit_client.client.roots.implementations.ModuleImplementation;
import net.minecraft.client.MinecraftClient;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.c2s.play.PlayerMoveC2SPacket;

@ModuleInfoAnnotation(
        name = "No Fall",
        description = "Disables fall damage",
        category = CategoryImplementation.Categories.MISC,
        internalModuleName = "nofall"
)
public class NoFallModule extends ModuleImplementation {
    @Override
    public boolean modifyPacket(Packet<?> packet) {
        if (!(packet instanceof PlayerMoveC2SPacket)) return false;

        var player = MinecraftClient.getInstance().player;
        if (player == null) return false;

        if (player.getAbilities().flying) {
            ((PlayerMoveC2SPacketAccessor) packet).setOnGround(true);
        }
        else {
            // Allow Elytra Flying
            if (player.isFallFlying()) return false;
            // Don't kill the player when NoFall is turned on too late (cancel fall damage)
            if (player.getVelocity().getY() > -0.5) return false;
            ((PlayerMoveC2SPacketAccessor) packet).setOnGround(true);
        }

        return false;
    }
}
