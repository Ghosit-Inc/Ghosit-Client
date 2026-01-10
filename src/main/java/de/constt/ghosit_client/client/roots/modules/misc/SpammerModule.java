package de.constt.ghosit_client.client.roots.modules.misc;

import de.constt.ghosit_client.client.annotations.ModuleInfoAnnotation;
import de.constt.ghosit_client.client.roots.implementations.CategoryImplementation;
import de.constt.ghosit_client.client.roots.implementations.ModuleImplementation;
import net.minecraft.client.MinecraftClient;

@ModuleInfoAnnotation(
        name = "Spammer",
        description = "Automaticly sends messages from your account to chat",
        category = CategoryImplementation.Categories.MISC,
        internalModuleName = "spammer"
)
public class SpammerModule extends ModuleImplementation {

    private int tickCounter = 0;

    @Override
    public void tick() {
        MinecraftClient mc = MinecraftClient.getInstance();
        if (mc == null || mc.player == null || mc.world == null || mc.player.networkHandler == null) {
            tickCounter = 0;
            return;
        }

        if (++tickCounter >= 4) {
            tickCounter = 0;
            mc.player.networkHandler.sendChatMessage("Ghosit client currently free!");
        }

        super.tick();
    }
}
