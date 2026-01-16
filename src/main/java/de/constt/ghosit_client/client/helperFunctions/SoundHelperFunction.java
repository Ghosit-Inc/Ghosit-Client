package de.constt.ghosit_client.client.helperFunctions;

import de.constt.ghosit_client.client.config.GhositConfigData;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.sound.PositionedSoundInstance;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

public class SoundHelperFunction {
    private static final MinecraftClient mc = MinecraftClient.getInstance();

    public static void playSound(SoundEvent sound, float pitch, boolean isSilent) {
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

    // ----------------------
    // Custom client-side sound from path
    // ----------------------
    /**
     * Play a custom client-side sound from your assets' folder.
     *
     * @param path relative path in assets/ghosit/, e.g., "sounds/warning.ogg"
     * @param pitch 0.5F - 2.0F
     */
    public static void playCustomSound(String path, float pitch, boolean silentMode) {
        if (mc == null || mc.getSoundManager() == null) return;
        Identifier soundId = Identifier.of("ghosit_client", path);
        if(!silentMode) {
            mc.getSoundManager().play(
                    PositionedSoundInstance.master(SoundEvent.of(soundId), 1.0F, pitch)
            );
        }
    }
}
