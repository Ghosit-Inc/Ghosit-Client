package de.constt.nexsus_client.client.roots.modules.movement;

import de.constt.nexsus_client.client.annotations.ModuleInfoAnnotation;
import de.constt.nexsus_client.client.helperFunctions.ChatHelperFunction;
import de.constt.nexsus_client.client.roots.implementations.CategoryImplementation;
import de.constt.nexsus_client.client.roots.implementations.ModuleImplementation;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.ingame.InventoryScreen;
import net.minecraft.util.math.Vec3d;

@ModuleInfoAnnotation(
        name = "GUI Move",
        description = "Lets you move while being in guis",
        category = CategoryImplementation.Categories.MOVEMENT,
        internalModuleName = "guimove"
)
public class GUIMoveModule extends ModuleImplementation {
    @Override
    public void tick() {
        var client = MinecraftClient.getInstance();
        var player = client.player;
        if (player == null) return;

        if (client.currentScreen instanceof InventoryScreen) {
            var options = client.options;

            float forward = 0;
            float sideways = 0;

            if (options.forwardKey.isPressed()) forward += 1;
            if (options.backKey.isPressed()) forward -= 1;
            if (options.leftKey.isPressed()) sideways += 1;
            if (options.rightKey.isPressed()) sideways -= 1;

            player.input.movementForward = forward;
            player.input.movementSideways = sideways;
            player.input.jumping = options.jumpKey.isPressed();
            player.input.sneaking = options.sneakKey.isPressed();
        }
    }
}
