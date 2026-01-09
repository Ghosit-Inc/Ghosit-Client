package de.constt.ghosit_client.client.helperFunctions;

import de.constt.ghosit_client.client.roots.implementations.ModuleImplementation;
import de.constt.ghosit_client.client.roots.modules.ModuleManager;
import net.fabricmc.loader.api.FabricLoader;
import org.lwjgl.glfw.GLFW;

import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Properties;

public class ConfigManagerHelperFunction {

    private static final Path CONFIGS_FOLDER =
            FabricLoader.getInstance().getConfigDir()
                    .resolve("ghositClient")
                    .resolve("configs");

    private static final Path DEFAULT_CONFIG =
            CONFIGS_FOLDER.resolve("default.cfg");

    private static Properties load() {
        try {
            Files.createDirectories(CONFIGS_FOLDER);
            Properties props = new Properties();
            if (Files.exists(DEFAULT_CONFIG)) {
                try (InputStream in = Files.newInputStream(DEFAULT_CONFIG)) {
                    props.load(in);
                }
            }
            return props;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static void save(Properties props) {
        try {
            Files.createDirectories(CONFIGS_FOLDER);
            try (OutputStream out = Files.newOutputStream(DEFAULT_CONFIG)) {
                props.store(out, null);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void addKeybind(ModuleImplementation module, int keyCode) {
        if (keyCode == GLFW.GLFW_KEY_UNKNOWN) return;
        Properties props = load();
        props.setProperty(ModuleAnnotationHelperFunction.getInternalModuleName(module.getClass()), String.valueOf(keyCode));
        save(props);
    }

    public static int getKeybind(ModuleImplementation module) {
        Properties props = load();
        String v = props.getProperty(ModuleAnnotationHelperFunction.getInternalModuleName(module.getClass()));
        if (v == null) return GLFW.GLFW_KEY_UNKNOWN;
        try {
            return Integer.parseInt(v);
        } catch (NumberFormatException e) {
            return GLFW.GLFW_KEY_UNKNOWN;
        }
    }

    public static void removeKeybind(ModuleImplementation module) {
        Properties props = load();
        props.remove(ModuleAnnotationHelperFunction.getInternalModuleName(module.getClass()));
        save(props);
    }

    public static void loadAllKeybinds() {
        ModuleManager.getModules().forEach(module -> {
            int key = getKeybind(module);
            if (key != GLFW.GLFW_KEY_UNKNOWN) {
                module.keyBindingCode = key;
            }
        });
    }

}
