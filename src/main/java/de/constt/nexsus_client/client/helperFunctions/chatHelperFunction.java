package de.constt.nexsus_client.client.helperFunctions;

import net.minecraft.client.MinecraftClient;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

public class chatHelperFunction {

    public static String prefix;

    public static String getPrefix() {
        return prefix + ' ';
    }

    public static void setPrefix(String newPrefix) {
        prefix = newPrefix;
    }

    public static void sendCSMessageNeutral(String msg) {
        MinecraftClient mc = MinecraftClient.getInstance();
        if (mc.inGameHud != null) {
            mc.inGameHud.getChatHud().addMessage(
                    Text.literal(getPrefix())
                            .formatted(Formatting.RESET)
                            .append(Text.literal(msg).formatted(Formatting.GRAY))
            );
        }

    }

    public static void sendCSMessageWarning(String msg) {
        MinecraftClient mc = MinecraftClient.getInstance();
        if (mc.inGameHud != null) {
            mc.inGameHud.getChatHud().addMessage(
                    Text.literal(getPrefix())
                            .formatted(Formatting.RESET)
                            .append(Text.literal(msg).formatted(Formatting.YELLOW))
            );
        }

    }

    public static void sendCSMessageError(String msg) {
        MinecraftClient mc = MinecraftClient.getInstance();
        if (mc.inGameHud != null) {
            mc.inGameHud.getChatHud().addMessage(
                    Text.literal(getPrefix())
                            .formatted(Formatting.RESET)
                            .append(Text.literal(msg).formatted(Formatting.DARK_RED))
            );
        }

    }
}