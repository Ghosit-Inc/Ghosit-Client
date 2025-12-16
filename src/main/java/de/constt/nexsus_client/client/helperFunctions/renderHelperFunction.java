package de.constt.nexsus_client.client.helperFunctions;

import de.constt.nexsus_client.client.config.NexsusConfigData;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.util.math.MathHelper;

public class renderHelperFunction {
    public static void renderHud(DrawContext drawContext) {
        var textRenderer = MinecraftClient.getInstance().textRenderer;
        var player = MinecraftClient.getInstance().player;


        assert player != null;
        drawWatermark(drawContext, textRenderer, player.age);
    }

    public static void drawWatermark(DrawContext drawContext, TextRenderer textRenderer, int age) {
        String clientVersion = NexsusConfigData.getConfigValue("clientVersion");
        var scale = 1f;
        int textColor = 0xFFFFFFFF;

        var matrices = drawContext.getMatrices().peek().getPositionMatrix();
        matrices.scale(scale);

        textRenderer.draw(
                chatHelperFunction.getPrefix()+" [v"+clientVersion+"]",
                5, 5,
                textColor, true,
                matrices,
                drawContext.getVertexConsumers(),
                TextRenderer.TextLayerType.NORMAL,
                0, 0xF000F0
        );

        matrices.scale(1f / scale);
        drawContext.draw();
    }
}
