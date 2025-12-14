package de.constt.nexsus_client.client.roots.modules;

import de.constt.nexsus_client.client.helperFunctions.chatHelperFunction;
import de.constt.nexsus_client.client.roots.implementations.ModuleImplementation;
import net.minecraft.client.MinecraftClient;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.c2s.play.ClientCommandC2SPacket;
import net.minecraft.network.packet.c2s.play.PlayerMoveC2SPacket;

public class NoHungerModule extends ModuleImplementation {

    private boolean lastOnGround = true;
    private boolean ignorePacket = false;

    private final boolean sprintSpoof = true;   // toggle for sprint packet spoofing
    private final boolean onGroundSpoof = true; // toggle for onGround spoofing

    @Override
    public boolean modifyPacket(Packet<?> packet) {
        MinecraftClient mc = MinecraftClient.getInstance();
        if (mc.player == null) return false;

        if (mc.player.hasVehicle() || mc.player.isTouchingWater() || mc.player.isSubmergedInWater())
            return false;

        // Handle sprint spoofing
        if (packet instanceof ClientCommandC2SPacket p && sprintSpoof) {
            if (p.getMode() == ClientCommandC2SPacket.Mode.START_SPRINTING) {
                chatHelperFunction.sendCSMessageWarning("Canceled START_SPRINTING packet to reduce hunger!");
                return true; // cancel packet
            }
        }

        // Handle onGround spoofing
        if (packet instanceof PlayerMoveC2SPacket movePacket && onGroundSpoof && mc.player.isOnGround() && mc.player.fallDistance <= 0.0) {
            if (!mc.interactionManager.isBreakingBlock()) {
                try {
                    // Use reflection or mixin to set onGround = false
                    PlayerMoveC2SPacketAccessor accessor = (PlayerMoveC2SPacketAccessor) movePacket;
                    accessor.meteor$setOnGround(false);
                } catch (ClassCastException ignored) {}
            }
        }

        return false;
    }

    @Override
    public void onTick() {
        MinecraftClient mc = MinecraftClient.getInstance();
        if (mc.player == null) return;

        // Track last onGround state to prevent fall damage issues
        if (mc.player.isOnGround() && !lastOnGround && onGroundSpoof) {
            ignorePacket = true; // skip the next movement packet if needed
        }
        lastOnGround = mc.player.isOnGround();
    }
}
