package de.constt.nexsus_client.client.roots.gui.widgets;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;
import java.awt.*;

public class BlackButtonWidget extends ButtonWidget {
    private boolean toggled = false;

    public BlackButtonWidget(int x, int y, int width, int height, String text, PressAction onPress) {
        super(x, y, width, height, Text.literal(text), onPress, DEFAULT_NARRATION_SUPPLIER);
    }

    @Override
    public void renderWidget(DrawContext context, int mouseX, int mouseY, float delta) {
        boolean hovered = isMouseOver(mouseX, mouseY);

        int backgroundColor = toggled
                ? new Color(50, 50, 50, 220).getRGB()
                : (hovered ? new Color(20, 20, 20, 220).getRGB() : new Color(0, 0, 0, 180).getRGB());
        int borderColor = hovered ? new Color(255, 255, 255, 150).getRGB() : new Color(255, 255, 255, 80).getRGB();


        context.fill(getX(), getY(), getX() + getWidth(), getY() + getHeight(), backgroundColor);
        context.drawBorder(getX(), getY(), getWidth(), getHeight(), borderColor);

        context.drawCenteredTextWithShadow(
                MinecraftClient.getInstance().textRenderer,
                getMessage(),
                getX() + getWidth() / 2,
                getY() + (getHeight() - 8) / 2,
                0xFFFFFF
        );
    }

    public void toggle() {
        this.toggled = !this.toggled;
    }
}
