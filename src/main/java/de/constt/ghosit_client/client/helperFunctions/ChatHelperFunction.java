package de.constt.ghosit_client.client.helperFunctions;

import de.constt.ghosit_client.client.config.GhositConfigData;
import net.minecraft.client.MinecraftClient;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

public class ChatHelperFunction {

    public static String prefix;

    public static String getPrefix(boolean isClean) {
        if (prefix == null) return "";
        String result = "§l" + prefix + "§r | ";
        if (isClean) {
            // Remove all Minecraft formatting codes
            result = result.replaceAll("§.", "");
            // Remove trailing separator
            result = result.replaceAll(" \\| $", "");
        }
        return result;
    }

    public static void setPrefix(String newPrefix) {
        prefix = newPrefix;
    }

    private static void playSound(SoundEvent sound, float pitch, boolean isSilent) {
        if (isSilent) return;
        boolean silentMode = Boolean.parseBoolean(
                GhositConfigData.getConfigValue("silentMode")
        );
        if(!silentMode) {
            MinecraftClient mc = MinecraftClient.getInstance();
            if (mc.player != null) {
                mc.player.playSound(sound, 1.0f, pitch);
            }
        }
    }

    public static void sendCSMessageNeutral(String msg, boolean isSilent) {
        MinecraftClient mc = MinecraftClient.getInstance();
        if (mc.inGameHud != null) {
            mc.inGameHud.getChatHud().addMessage(
                    Text.literal(getPrefix(false))
                            .formatted(Formatting.RESET)
                            .append(Text.literal(msg).formatted(Formatting.GRAY))
            );
            playSound(SoundEvents.BLOCK_NOTE_BLOCK_HARP.value(), 1.2f, isSilent);
        }
    }

    public static void sendCSMessageWarning(String msg, boolean isSilent) {
        MinecraftClient mc = MinecraftClient.getInstance();
        if (mc.inGameHud != null) {
            mc.inGameHud.getChatHud().addMessage(
                    Text.literal(getPrefix(false))
                            .formatted(Formatting.RESET)
                            .append(Text.literal(msg).formatted(Formatting.YELLOW))
            );
            playSound(SoundEvents.BLOCK_NOTE_BLOCK_PLING.value(), 0.8f, isSilent);
        }
    }

    public static void sendCSMessageError(String msg, boolean isSilent) {
        MinecraftClient mc = MinecraftClient.getInstance();
        if (mc.inGameHud != null) {
            mc.inGameHud.getChatHud().addMessage(
                    Text.literal(getPrefix(false))
                            .formatted(Formatting.RESET)
                            .append(Text.literal(msg).formatted(Formatting.DARK_RED))
            );
            playSound(SoundEvents.ENTITY_ENDERMAN_TELEPORT, 0.6f, isSilent);
        }
    }
}
