package de.constt.nexsus_client.client.events.render;

import de.constt.nexsus_client.client.helperFunctions.RenderHelperFunction;
import de.constt.nexsus_client.client.roots.gui.ModulesScreen;
import net.fabricmc.fabric.api.client.screen.v1.ScreenEvents;
import net.minecraft.client.gui.screen.ChatScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.ingame.CreativeInventoryScreen;
import net.minecraft.client.gui.screen.ingame.HopperScreen;
import net.minecraft.client.gui.screen.ingame.InventoryScreen;
import net.minecraft.client.gui.screen.ingame.ShulkerBoxScreen;

public final class ScreenRenderCallbackEvent {

    public static void register() {

        ScreenEvents.BEFORE_INIT.register((client, screen, scaledWidth, scaledHeight) -> {
            if (screen instanceof InventoryScreen || screen instanceof CreativeInventoryScreen || screen instanceof HopperScreen || screen instanceof ShulkerBoxScreen || screen instanceof ModulesScreen || screen instanceof ChatScreen) return;

            ScreenEvents.afterRender((Screen) screen).register(
                    (s, drawContext, mouseX, mouseY, delta) -> {
                        RenderHelperFunction.renderScreenOverlay(drawContext);
                    }
            );
        });
    }
}
