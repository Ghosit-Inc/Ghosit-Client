package de.constt.nexsus_client.client.roots.gui;

import de.constt.nexsus_client.client.helperFunctions.ModuleAnnotationHelperFunction;
import de.constt.nexsus_client.client.helperFunctions.ChatHelperFunction;
import de.constt.nexsus_client.client.roots.gui.widgets.BlackButtonWidget;
import de.constt.nexsus_client.client.roots.implementations.ModuleImplementation;
import de.constt.nexsus_client.client.roots.modules.ModuleManager;
import de.constt.nexsus_client.client.roots.modules.misc.DebuggerModule;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.text.Text;

import java.util.Objects;

public class ModulesScreen extends Screen {
    public ModulesScreen(String title) {
        super(Text.literal(title));
    }

    @Override
    protected void init() {
        int x = 10;
        int y = 10;
        int spacing = 20;

        for (ModuleImplementation module : ModuleManager.getModules()) {
            this.addDrawableChild(new BlackButtonWidget(x, y, 100, 15, ModuleAnnotationHelperFunction.getName(module.getClass()), button -> {
                module.toggle();
                String status = module.getEnabledStatus() ? "on" : "off";
                String statusColorCoded = status;
                if(status.equals("on")) {
                    statusColorCoded = "§aon";
                } else {
                    statusColorCoded = "§coff";
                }

                if(Objects.requireNonNull(ModuleManager.getModule(DebuggerModule.class)).getEnabledStatus()) {
                    if (MinecraftClient.getInstance().player != null) {
                        ChatHelperFunction.sendCSMessageNeutral("§8Toggled§r "+ ModuleAnnotationHelperFunction.getName(module.getClass()) + " (" + statusColorCoded + ")");
                    }
                }
            }));

            y += spacing;
        }
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        this.renderBackground(context, mouseX, mouseY, delta);
        super.render(context, mouseX, mouseY, delta);
    }
}
