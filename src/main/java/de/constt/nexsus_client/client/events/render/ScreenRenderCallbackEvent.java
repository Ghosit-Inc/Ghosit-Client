package de.constt.nexsus_client.client.events.render;

import de.constt.nexsus_client.client.helperFunctions.renderHelperFunction;
import de.constt.nexsus_client.client.roots.gui.ModulesScreen;
import net.fabricmc.fabric.api.client.screen.v1.ScreenEvents;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.ingame.InventoryScreen;

public final class ScreenRenderCallbackEvent {

    public static void register() {

        ScreenEvents.BEFORE_INIT.register((client, screen, scaledWidth, scaledHeight) -> {
            if (screen instanceof InventoryScreen || screen instanceof ModulesScreen) return;

            ScreenEvents.afterRender((Screen) screen).register(
                    (s, drawContext, mouseX, mouseY, delta) -> {
                        renderHelperFunction.renderScreenOverlay(drawContext);
                    }
            );
        });
    }
}
