package de.constt.ghosit_client.client.events.hud;

import de.constt.ghosit_client.client.helperFunctions.RenderHelperFunction;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;

public class HudRenderCallbackEvent {

    public static void register() {
        HudRenderCallback.EVENT.register((drawContext, delta) -> {
            if (MinecraftClient.getInstance().getDebugHud().shouldShowDebugHud()) return;

            RenderHelperFunction.renderHud(drawContext);
        });
    }
}
