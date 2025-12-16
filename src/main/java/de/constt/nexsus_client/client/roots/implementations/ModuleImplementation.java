package de.constt.nexsus_client.client.roots.implementations;

import net.minecraft.network.packet.Packet;

public abstract class ModuleImplementation {
    protected boolean enabled = false;


    /**
     * Toggle the module
     *
     */
    public void toggle() {
        this.enabled = !this.enabled;
    }

    /**
     * Return the name of the module
     * @return the name of the module
     */
    public static String getTranslatableText() {
        return "temp";
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


    public boolean modifyPacket(Packet<?> packet) {
        return false;
    };
}
