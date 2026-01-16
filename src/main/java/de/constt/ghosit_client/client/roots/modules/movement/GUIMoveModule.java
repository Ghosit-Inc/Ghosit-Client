package de.constt.ghosit_client.client.roots.modules.movement;

import de.constt.ghosit_client.client.annotations.ModuleInfoAnnotation;
import de.constt.ghosit_client.client.roots.implementations.CategoryImplementation;
import de.constt.ghosit_client.client.roots.implementations.ModuleImplementation;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.MinecraftClient;

@ModuleInfoAnnotation(
        name = "GUI Move",
        description = "Lets you move while in GUIs",
        category = CategoryImplementation.Categories.MOVEMENT,
        internalModuleName = "guimove"
)
public class GUIMoveModule extends ModuleImplementation {

    @Override
    public void onEnable() {
        ClientTickEvents.END_CLIENT_TICK.register(this::tick);
    }

    private void tick(MinecraftClient client) {
        if (client.player == null) return;

        if (client.currentScreen != null) {
            var options = client.options;

            options.forwardKey.setPressed(options.forwardKey.isPressed());
            options.backKey.setPressed(options.backKey.isPressed());
            options.leftKey.setPressed(options.leftKey.isPressed());
            options.rightKey.setPressed(options.rightKey.isPressed());
            options.jumpKey.setPressed(options.jumpKey.isPressed());
            options.sneakKey.setPressed(options.sneakKey.isPressed());
        }
    }
}
