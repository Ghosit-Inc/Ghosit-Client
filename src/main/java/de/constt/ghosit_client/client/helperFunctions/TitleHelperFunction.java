package de.constt.ghosit_client.client.helperFunctions;

import de.constt.ghosit_client.client.config.GhositConfigData;
import net.minecraft.client.MinecraftClient;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

public class TitleHelperFunction {

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

    private static void sendTitle(Text title, Text subtitle) {
        MinecraftClient mc = MinecraftClient.getInstance();
        if (mc.inGameHud != null) {
            mc.inGameHud.setTitle(title);
            mc.inGameHud.setSubtitle(subtitle);
            mc.inGameHud.setTitleTicks(10, 60, 10);
        }
    }

    public static void sendCSTitleNeutral(String msg, boolean isSilent) {
        sendTitle(
                Text.literal("").formatted(Formatting.RESET),
                Text.literal(msg).formatted(Formatting.GRAY)
        );
        playSound(SoundEvents.BLOCK_NOTE_BLOCK_HARP.value(), 1.2f, isSilent);
    }

    public static void sendCSTitleWarning(String msg, boolean isSilent) {
        sendTitle(
                Text.literal("").formatted(Formatting.RESET),
                Text.literal(msg).formatted(Formatting.YELLOW)
        );
        playSound(SoundEvents.BLOCK_NOTE_BLOCK_PLING.value(), 0.8f, isSilent);
    }

    public static void sendCSTitleError(String msg, boolean isSilent) {
        sendTitle(
                Text.literal("").formatted(Formatting.RESET),
                Text.literal(msg).formatted(Formatting.DARK_RED)
        );
        playSound(SoundEvents.ENTITY_ENDERMAN_TELEPORT, 0.6f, isSilent);
    }
}
