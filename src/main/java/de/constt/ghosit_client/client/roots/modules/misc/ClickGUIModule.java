package de.constt.ghosit_client.client.roots.modules.misc;

import de.constt.ghosit_client.client.annotations.ModuleInfoAnnotation;
import de.constt.ghosit_client.client.roots.gui.ModulesScreen;
import de.constt.ghosit_client.client.roots.implementations.CategoryImplementation;
import de.constt.ghosit_client.client.roots.implementations.ModuleImplementation;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.ingame.InventoryScreen;
import net.minecraft.client.gui.screen.multiplayer.AddServerScreen;
import net.minecraft.client.gui.screen.multiplayer.DirectConnectScreen;
import net.minecraft.client.gui.screen.multiplayer.MultiplayerScreen;

import java.util.Objects;

@ModuleInfoAnnotation(
        name = "Click GUI",
        description = "Opens/Closes the Click GUI",
        category = CategoryImplementation.Categories.MISC,
        internalModuleName = "clickgui"
)

public class ClickGUIModule extends ModuleImplementation {
    @Override
    public void onEnable() {
        super.onEnable();

        MinecraftClient client = MinecraftClient.getInstance();
        if(!(client.currentScreen instanceof MultiplayerScreen || client.currentScreen instanceof AddServerScreen || client.currentScreen instanceof DirectConnectScreen)) {
            if(!Objects.equals(client.currentScreen, new ModulesScreen("Ghosit Client Modules Screen"))) {
                client.setScreen(new ModulesScreen("Ghosit Client Modules Screen"));
            }
        } else return;
        // this.toggle(); - crashes on toggle
    }
}
