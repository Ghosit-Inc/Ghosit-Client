package de.constt.nexsus_client.client.events.hud;

import de.constt.nexsus_client.client.helperFunctions.renderHelperFunction;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;

public class HudRenderCallbackEvent {

    public static void register() {
        HudRenderCallback.EVENT.register((drawContext, delta) -> {
            if (MinecraftClient.getInstance().getDebugHud().shouldShowDebugHud()) return;

            renderHelperFunction.renderHud(drawContext);
        });
    }
}
