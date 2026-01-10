package de.constt.ghosit_client.client.roots.modules.movement;

import de.constt.ghosit_client.client.annotations.ModuleInfoAnnotation;
import de.constt.ghosit_client.client.helperFunctions.ChatHelperFunction;
import de.constt.ghosit_client.client.mixins.PlayerMoveC2SPacketAccessor;
import de.constt.ghosit_client.client.roots.implementations.CategoryImplementation;
import de.constt.ghosit_client.client.roots.implementations.ModuleImplementation;
import net.minecraft.client.MinecraftClient;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.c2s.play.PlayerMoveC2SPacket;

@ModuleInfoAnnotation(
        name = "Flight",
        description = "Enables flying for the player",
        category = CategoryImplementation.Categories.MOVEMENT,
        internalModuleName = "flight"
)
public class FlightModule extends ModuleImplementation {
    private static final double FALL_DIST = 0.4;
    private static final int MAX_FLOATING_TICKS = 10;
    private double previousY;
    private int floatingTicks;

    @Override
    public void toggle() {
        MinecraftClient client = MinecraftClient.getInstance();
        if (client.player == null || client.world == null) {
            ChatHelperFunction.sendCSMessageWarning(
                    "Flight cannot be toggled while not in-game", false
            );
            return;
        }

        var player = client.player;

        if (player.isCreative()) {
            ChatHelperFunction.sendCSMessageWarning(
                    "You are in creative! Please switch your gamemode to any besides creative to use flight", false
            );
            return;
        }

        super.toggle();

        player.getAbilities().allowFlying = this.enabled;
        player.getAbilities().setFlySpeed(2);

        if (!this.enabled) {
            player.getAbilities().flying = false;
        }

        this.previousY = player.getY();
        this.floatingTicks = 0;
    }


    @Override
    public boolean modifyPacket(Packet<?> packet) {
        if (!(packet instanceof PlayerMoveC2SPacket)) return false;

        // Go down by {@FALL_DIST} to avoid getting kicked for flying
        if (floatingTicks >= MAX_FLOATING_TICKS) {
            ((PlayerMoveC2SPacketAccessor) packet).setY(this.previousY - FALL_DIST);

            floatingTicks = 0;
        }

        return false;
    }

    @Override
    public void tick() {
        var player = MinecraftClient.getInstance().player;
        if (player == null) return;

        player.getAbilities().allowFlying = this.enabled;

        if (!player.getAbilities().flying) return;

        if (player.getY() >= this.previousY - FALL_DIST)
            this.floatingTicks++;

        this.previousY = player.getY();
    }
}
