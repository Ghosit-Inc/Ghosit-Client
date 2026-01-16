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
        boolean addTime;

        public Toast(Text message, long duration, int textColor, int bgColor, boolean addTime) {
            this.message = message;
            this.startTime = System.currentTimeMillis();
            this.duration = duration;
            this.textColor = textColor;
            this.bgColor = bgColor;
            this.addTime = addTime;
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
            if (progress < 0.1f) return progress / 0.1f;
            if (progress > 0.9f) return (1f - progress) / 0.1f;
            return 1f;
        }

        public String getDisplayMessage() {
            if (!addTime) return message.getString();
            long elapsed = System.currentTimeMillis() - startTime;
            if (elapsed < 1000) return message.getString() + " [" + elapsed + "ms]";
            float seconds = elapsed / 1000f;
            return message.getString() + " [" + String.format("%.1f", seconds) + "s]";
        }
    }

    private static final List<Toast> toasts = new ArrayList<>();

    public static void showToast(String message, long durationMillis, int textColor, int bgColor, boolean addTime) {
        toasts.add(new Toast(Text.of(message), durationMillis, textColor, bgColor, addTime));
    }

    public static void showNeutral(String message, long durationMillis) {
        showNeutral(message, durationMillis, false);
    }

    public static void showNeutral(String message, long durationMillis, boolean addTime) {
        showToast(message, durationMillis, 0xFFFFFFFF, 0xAA000000, addTime);
    }

    public static void showWarning(String message, long durationMillis) {
        showWarning(message, durationMillis, false);
    }

    public static void showWarning(String message, long durationMillis, boolean addTime) {
        showToast(message, durationMillis, 0xFFFFFF00, 0xAA000000, addTime);
    }

    public static void showError(String message, long durationMillis) {
        showError(message, durationMillis, false);
    }

    public static void showError(String message, long durationMillis, boolean addTime) {
        showToast(message, durationMillis, 0xFFFF5555, 0xAA000000, addTime);
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

            int bgA = (int)((toast.bgColor >> 24 & 0xFF) * alpha);
            int bgR = (toast.bgColor >> 16) & 0xFF;
            int bgG = (toast.bgColor >> 8) & 0xFF;
            int bgB = toast.bgColor & 0xFF;
            int finalBg = (bgA << 24) | (bgR << 16) | (bgG << 8) | bgB;
            drawContext.fill(x, y, x + toast.width, y + toast.height, finalBg);

            int txtA = (int)((toast.textColor >> 24 & 0xFF) * alpha);
            int txtR = (toast.textColor >> 16) & 0xFF;
            int txtG = (toast.textColor >> 8) & 0xFF;
            int txtB = toast.textColor & 0xFF;
            int finalText = (txtA << 24) | (txtR << 16) | (txtG << 8) | txtB;

            drawContext.drawText(textRenderer, Text.of(toast.getDisplayMessage()), x + 5, y + 2, finalText, true);

            index++;
        }
    }
}
