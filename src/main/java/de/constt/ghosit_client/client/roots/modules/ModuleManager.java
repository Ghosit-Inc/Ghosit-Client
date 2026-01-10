package de.constt.ghosit_client.client.roots.modules;

import de.constt.ghosit_client.client.roots.implementations.ModuleImplementation;

import de.constt.ghosit_client.client.roots.modules.events.ChatCalculatorModule;
import de.constt.ghosit_client.client.roots.modules.exploits.BowBombModule;
import de.constt.ghosit_client.client.roots.modules.exploits.WorldGuardBypassModule;
import de.constt.ghosit_client.client.roots.modules.misc.ClickGUIModule;
import de.constt.ghosit_client.client.roots.modules.misc.DebuggerModule;
import de.constt.ghosit_client.client.roots.modules.misc.PacketLoggerModule;
import de.constt.ghosit_client.client.roots.modules.misc.SpammerModule;
import de.constt.ghosit_client.client.roots.modules.movement.FlightModule;
import de.constt.ghosit_client.client.roots.modules.movement.GUIMoveModule;
import de.constt.ghosit_client.client.roots.modules.player.NoHungerModule;
import de.constt.ghosit_client.client.roots.modules.render.*;
import de.constt.ghosit_client.client.roots.modules.world.NoFallModule;
import de.constt.ghosit_client.client.roots.modules.world.XRayModule;

import java.util.ArrayList;
import java.util.List;

public class ModuleManager {
    private static final List<ModuleImplementation> MODULES = new ArrayList<>();

    public static void init() {
        // MODULES

        // Events
        MODULES.add(new ChatCalculatorModule());

        // Exploits
        MODULES.add(new BowBombModule());
        MODULES.add(new WorldGuardBypassModule());

        // Misc
        MODULES.add(new ClickGUIModule());
        MODULES.add(new DebuggerModule());
        MODULES.add(new PacketLoggerModule());
        MODULES.add(new SpammerModule());

        // Movement
        MODULES.add(new FlightModule());
        MODULES.add(new GUIMoveModule());

        // Player
        MODULES.add(new NoHungerModule());

        // Render
        MODULES.add(new ArrayListModule());
        MODULES.add(new FPSHudModule());
        MODULES.add(new FullbrightModule());
        MODULES.add(new NoWheaterModule());
        MODULES.add(new StorageESPModule());

        // Skyblock

        // World
        MODULES.add(new NoFallModule());
        MODULES.add(new XRayModule());
    }


    /**
     * Gets a list of loaded modules
     * @return a list of modules
     */
    public static List<ModuleImplementation> getModules() {
        return MODULES;
    }

    public static void setBind(ModuleImplementation module, int keyBinding) {
        module.keyBindingCode = keyBinding;
    }


    public static int numModules() {
        return getModules().size();
    }

    /**
     * Get the module from {@code MODULES} that matches the class {@code moduleClass}
     * @param 	moduleClass the module to get
     * @return	the matching module from {@code MODULES}
     * @param 	<T> extends ModuleImplementation
     */
    public static <T extends ModuleImplementation> T getModule(Class<T> moduleClass) {
        for (var module : getModules()) {
            if (module.getClass() == moduleClass) {
                return moduleClass.cast(module);
            }
        }
        return null;
    }


    /**
     * Checks if the module with class {@code moduleClass} is enabled
     * @param 	moduleClass the class to search for
     * @return	whether the module is enabled
     */
    public static boolean isEnabled(Class<? extends ModuleImplementation> moduleClass) {
        var module = getModule(moduleClass);
        return module != null && module.getEnabledStatus();
    }

    /**
     * Toggles enabled for the module with class {@code moduleClass}
     * @param moduleClass the class to toggle
     */
    public static void toggle(Class<? extends ModuleImplementation> moduleClass) {
        var module = getModule(moduleClass);
        if (module != null) {
            module.toggle();
        }
    }

    public static void tempToggleModules() {
        // TEMP: MODULE TOGGLE
        /* Old Temp Toggled Modules
        ModuleManager.toggle(NoFallModule.class);
        ModuleManager.toggle(NoHungerModule.class);
        */
    }
}

