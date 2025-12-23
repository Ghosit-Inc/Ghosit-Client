package de.constt.nexsus_client.client.roots.modules.movement;

import de.constt.nexsus_client.client.annotations.ModuleInfoAnnotation;
import de.constt.nexsus_client.client.mixins.PlayerMoveC2SPacketAccessor;
import de.constt.nexsus_client.client.roots.implementations.CategoryImplementation;
import de.constt.nexsus_client.client.roots.implementations.ModuleImplementation;
import de.constt.nexsus_client.client.settings.FloatSetting;
import de.constt.nexsus_client.client.settings.NumberSetting;
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

    private final FloatSetting flightSpeed =
            new FloatSetting(1, 100, 5);

    @Override
    public void toggle() {
        var player = MinecraftClient.getInstance().player;
        super.toggle();

        if (player == null) return;

        player.getAbilities().allowFlying = this.enabled;
        player.getAbilities().setFlySpeed(flightSpeed.getValue());
        if (!this.enabled) {
            player.getAbilities().flying = false;
        }

        this.previousY = player.getY();
        this.floatingTicks = 0;
    }

    @Override
    public void setSettings() {
        var player = MinecraftClient.getInstance().player;

        assert player != null;
        player.getAbilities().setFlySpeed(flightSpeed.getValue());
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
