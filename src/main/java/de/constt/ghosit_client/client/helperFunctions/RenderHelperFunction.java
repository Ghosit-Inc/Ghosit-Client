package de.constt.ghosit_client.client.helperFunctions;

import de.constt.ghosit_client.client.config.GhositConfigData;
import de.constt.ghosit_client.client.roots.implementations.ModuleImplementation;
import de.constt.ghosit_client.client.roots.modules.ModuleManager;
import de.constt.ghosit_client.client.roots.modules.render.ArrayListModule;
import de.constt.ghosit_client.client.roots.modules.render.FPSHudModule;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;

import java.util.Objects;

public class RenderHelperFunction {
    public static void renderHud(DrawContext drawContext) {
        var textRenderer = MinecraftClient.getInstance().textRenderer;
        var player = MinecraftClient.getInstance().player;


        assert player != null;

        boolean watermark = Boolean.parseBoolean(
                GhositConfigData.getConfigValue("watermark")
        );
        if(watermark) drawWatermark(drawContext, textRenderer, player.age);

        if(ModuleManager.isEnabled(ArrayListModule.class)) drawModulesList(drawContext, textRenderer, player.age);

        if(Objects.requireNonNull(ModuleManager.getModule(FPSHudModule.class)).getEnabledStatus()) {
            drawFPS(drawContext, textRenderer, player.age);
        }
    }

    public static void drawWatermark(DrawContext drawContext, TextRenderer textRenderer, int age) {
        String clientVersion = GhositConfigData.getConfigValue("clientVersion");
        var scale = 1f;
        int textColor = 0xFFFFFFFF;

        var matrices = drawContext.getMatrices().peek().getPositionMatrix();
        matrices.scale(scale);

        assert MinecraftClient.getInstance().player != null;
        drawContext.drawText(
                MinecraftClient.getInstance().textRenderer,
                ChatHelperFunction.getPrefix(true)+" [v"+clientVersion+"]",
                5,
                5,
                textColor,
                true
        );

        matrices.scale(1f / scale);
        drawContext.draw();
    }

    public static void drawFPS(DrawContext drawContext, TextRenderer textRenderer, int age) {
        var scale = 1f;
        int textColor = 0xAAAAAA;

        var matrices = drawContext.getMatrices().peek().getPositionMatrix();
        matrices.scale(scale);

        assert MinecraftClient.getInstance().player != null;
        drawContext.drawText(
                MinecraftClient.getInstance().textRenderer,
                MinecraftClient.getInstance().getCurrentFps() + " [FPS]",
                5,
                20,
                textColor,
                true
        );


        matrices.scale(1f / scale);
        drawContext.draw();
    }

    public static void drawModulesList(DrawContext drawContext, TextRenderer textRenderer, int age) {
        int yOffset = 5;

        var scale = 1f;
        int textColor = 0xFFFFFFFF;

        var matrices = drawContext.getMatrices().peek().getPositionMatrix();
        matrices.scale(scale);

        int screenWidth = MinecraftClient.getInstance().getWindow().getScaledWidth();

        for(ModuleImplementation module: ModuleManager.getModules()) {
            if(!Objects.equals(ModuleAnnotationHelperFunction.getName(module.getClass()), "Array List")) {
                if(Objects.requireNonNull(ModuleManager.getModule(module.getClass())).getEnabledStatus()) {
                    drawContext.drawText(
                            MinecraftClient.getInstance().textRenderer,
                            ModuleAnnotationHelperFunction.getName(module.getClass()),
                            screenWidth - MinecraftClient.getInstance().textRenderer.getWidth(ModuleAnnotationHelperFunction.getName(module.getClass())) - 5,
                            yOffset,
                            textColor,
                            true
                    );

                    matrices.scale(1f / scale);
                    drawContext.draw();

                }
            }
            yOffset += 10;
        }
    }


    public static void renderScreenOverlay(DrawContext drawContext) {
        MinecraftClient client = MinecraftClient.getInstance();

        drawContext.drawText(
                client.textRenderer,
                "Loaded "+ChatHelperFunction.getPrefix(true),
                5,
                5,
                0xFFFFFFFF,
                true
        );
    }
}
