package de.constt.ghosit_client.client.config;

import net.fabricmc.loader.api.FabricLoader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class GhositConfigData {

    private static final Path ROOT_FOLDER = FabricLoader.getInstance().getConfigDir().resolve("ghositClient");
    private static final Path CONFIG_PATH = ROOT_FOLDER.resolve("ghosit_client.cfg");
    public static final Path PROFILES_FOLDER = ROOT_FOLDER.resolve("profiles");
    public static final Path FRIENDS_FOLDER = ROOT_FOLDER.resolve("friends");
    public static final Path WAYPOINTS_FOLDER = ROOT_FOLDER.resolve("waypoints");
    public static final Path CONFIGS_FOLDER = ROOT_FOLDER.resolve("configs");
    public static final Path CACHE_FOLDER = ROOT_FOLDER.resolve(".cache");

    private static final Map<String, String> configValues = new HashMap<>();

    public static void init() {
        try {
            // create main folder
            if (!Files.exists(ROOT_FOLDER)) {
                Files.createDirectories(ROOT_FOLDER);
            }

            // create subfolders
            if (!Files.exists(PROFILES_FOLDER)) Files.createDirectories(PROFILES_FOLDER);
            if (!Files.exists(FRIENDS_FOLDER)) Files.createDirectories(FRIENDS_FOLDER);
            if (!Files.exists(WAYPOINTS_FOLDER)) Files.createDirectories(WAYPOINTS_FOLDER);
            if (!Files.exists(CONFIGS_FOLDER)) Files.createDirectories(CONFIGS_FOLDER);
            if (!Files.exists(CACHE_FOLDER)) Files.createDirectories(CACHE_FOLDER);

            // load or create config
            if (Files.exists(CONFIG_PATH)) {
                load();
            } else {
                configValues.put("clientVersion", "0.1");
                configValues.put("silentMode", "false");
                configValues.put("watermark",  "true");
                configValues.put("commandSuggestions", "true");
                save();
            }

        } catch (IOException ignored) {}
    }

    private static void load() {
        try {
            for (String line : Files.readAllLines(CONFIG_PATH)) {
                line = line.trim();
                if (line.isEmpty() || line.startsWith("#")) continue;
                String[] parts = line.split("=", 2);
                if (parts.length == 2) {
                    configValues.put(parts[0].trim(), parts[1].trim());
                }
            }
        } catch (IOException ignored) {}
    }

    private static void save() {
        try {
            List<String> lines = List.of(
                    "# Internal client version identifier",
                    "# Do not edit unless you know exactly what you are doing"
            );
            for (Map.Entry<String, String> entry : configValues.entrySet()) {
                lines = new java.util.ArrayList<>(lines);
                lines.add(entry.getKey() + "=" + entry.getValue());
            }
            Files.write(CONFIG_PATH, lines);
        } catch (IOException ignored) {}
    }

    public static String getConfigValue(String key) {
        return configValues.get(key);
    }

    public static void setConfigValue(String key, String value) {
        configValues.put(key, value);
        save();
    }
}
