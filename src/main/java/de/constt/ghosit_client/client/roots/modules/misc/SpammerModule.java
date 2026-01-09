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
        if(tickCounter == 4) {
            this.tickCounter = 0;
            assert MinecraftClient.getInstance().player != null;
            MinecraftClient.getInstance().player.networkHandler.sendChatMessage("ghosit client currently free!");
            super.tick();
        }
        this.tickCounter++;
    }
}
