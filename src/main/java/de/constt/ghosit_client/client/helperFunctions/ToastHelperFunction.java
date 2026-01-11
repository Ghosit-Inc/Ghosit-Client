package de.constt.ghosit_client.client.helperFunctions;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.text.Text;
import net.minecraft.util.math.MathHelper;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ToastHelperFunction {

    private static class Toast {
        Text message;
        long startTime;
        long duration;
        int width = 200;
        int height = 20;
        int textColor;
        int bgColor;

        public Toast(Text message, long duration, int textColor, int bgColor) {
            this.message = message;
            this.startTime = System.currentTimeMillis();
            this.duration = duration;
            this.textColor = textColor;
            this.bgColor = bgColor;
        }

        public float getProgress() {
            long elapsed = System.currentTimeMillis() - startTime;
            return MathHelper.clamp((float) elapsed / duration, 0f, 1f);
        }

        public boolean isExpired() {
            return System.currentTimeMillis() - startTime > duration;
        }

        public float getAlpha() {
            float progress = getProgress();
            if (progress < 0.1f) return progress / 0.1f;      // fade in
            if (progress > 0.9f) return (1f - progress) / 0.1f; // fade out
            return 1f;
        }
    }

    private static final List<Toast> toasts = new ArrayList<>();

    public static void showToast(String message, long durationMillis, int textColor, int bgColor) {
        toasts.add(new Toast(Text.of(message), durationMillis, textColor, bgColor));
    }

    public static void showNeutral(String message, long durationMillis) {
        showToast(message, durationMillis, 0xFFFFFFFF, 0xAA000000);
    }

    public static void showWarning(String message, long durationMillis) {
        showToast(message, durationMillis, 0xFFFFFF00, 0xAA000000);
    }

    public static void showError(String message, long durationMillis) {
        showToast(message, durationMillis, 0xFFFF5555, 0xAA000000);
    }

    public static void renderToasts(DrawContext drawContext, TextRenderer textRenderer) {
        MinecraftClient mc = MinecraftClient.getInstance();
        if (mc == null) return;

        int screenWidth = mc.getWindow().getScaledWidth();
        Iterator<Toast> it = toasts.iterator();
        int index = 0;

        while (it.hasNext()) {
            Toast toast = it.next();

            if (toast.isExpired()) {
                it.remove();
                continue;
            }

            float alpha = toast.getAlpha();
            int x = screenWidth - toast.width - 10;
            int y = 10 + index * (toast.height + 5);

            // Background with alpha
            int bgA = (int)((toast.bgColor >> 24 & 0xFF) * alpha);
            int bgR = (toast.bgColor >> 16) & 0xFF;
            int bgG = (toast.bgColor >> 8) & 0xFF;
            int bgB = toast.bgColor & 0xFF;
            int finalBg = (bgA << 24) | (bgR << 16) | (bgG << 8) | bgB;
            drawContext.fill(x, y, x + toast.width, y + toast.height, finalBg);

            // Text with alpha
            int txtA = (int)((toast.textColor >> 24 & 0xFF) * alpha);
            int txtR = (toast.textColor >> 16) & 0xFF;
            int txtG = (toast.textColor >> 8) & 0xFF;
            int txtB = toast.textColor & 0xFF;
            int finalText = (txtA << 24) | (txtR << 16) | (txtG << 8) | txtB;
            drawContext.drawText(textRenderer, toast.message, x + 5, y + 2, finalText, true);

            index++;
        }
    }
}
