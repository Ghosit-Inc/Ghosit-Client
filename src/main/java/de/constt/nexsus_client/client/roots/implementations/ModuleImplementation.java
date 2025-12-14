package de.constt.nexsus_client.client.roots.implementations;

import net.minecraft.network.packet.Packet;

public abstract class ModuleImplementation {
    public static String name;
    protected static boolean enabled = false;
    public static String description;
    public static String keybind;

    /**
     * Toggle the module
     */
    public static void toggle() {
        enabled = !enabled;
    }

    /**
     * Return the name of the module
     * @return the name of the module
     */
    public static String getTranslatableText() {
        return name;
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

    public abstract boolean modifyPacket(Packet<?> packet);

    public abstract void onTick();
}
