package de.constt.ghosit_client.client.roots.implementations;

import de.constt.ghosit_client.client.helperFunctions.ModuleAnnotationHelperFunction;
import de.constt.ghosit_client.client.roots.modules.Setting;
import de.constt.ghosit_client.client.roots.modules.settings.BooleanSetting;
import net.minecraft.network.packet.Packet;
import org.lwjgl.glfw.GLFW;

import java.util.*;

public abstract class ModuleImplementation {
    protected boolean enabled = false;
    public int keyBindingCode = GLFW.GLFW_KEY_UNKNOWN;;
    private final Map<String, Setting<?>> settings = new HashMap<>();

    protected ModuleImplementation() {
        registerSetting(new BooleanSetting("DISABLE_ON_TOGGLE", false));
    }

    protected void registerSetting(Setting<?> setting) {
        settings.put(setting.getName(), setting);
    }

    public Setting<?> getSetting(String name) {
        return settings.get(name.toUpperCase());
    }

    public Collection<Setting<?>> getSettings() {
        return settings.values();
    }

    /**
     * Toggle the module
     *
     */
    public void toggle() {
        if (getSetting("DISABLE_ON_TOGGLE") instanceof BooleanSetting s) {
            if (s.get()) {
                this.toggle();
                return;
            }
        }
        this.enabled = !this.enabled;
        onEnable();
    }

    /**
     * Get the keybinding code of the module
     * @return Keybinding Code
     */
    public int getKeybindingCode() {
        return keyBindingCode;
    }


    /**
     * Return the name of the module
     * @return the name of the module
     */
    public String getTranslatableText() {
        return ModuleAnnotationHelperFunction.getName(this.getClass());
    };

    /**
     * Check the status of the module
     * @return  true if the module is enabled, else false
     */
    public boolean getEnabledStatus() {
        return enabled;
    }

    /**
     * Is called at {@code START_CLIENT_TICK}
     */
    public void tick() { }

    /**
     * Is called at {@code END_CLIENT_TICK}
     */
    public void postTick() { }

    /**
     * Is called when the module gets enabled
     */
    public void onEnable() { }

    /**
     * Is called when the module gets disabled
     */
    public void onDisable() { }

    /**
     * Modify minecraft packets from the module
     * @param packet Packet
     * @return False
     */
    public boolean modifyPacket(Packet<?> packet) {
        return false;
    };
}
