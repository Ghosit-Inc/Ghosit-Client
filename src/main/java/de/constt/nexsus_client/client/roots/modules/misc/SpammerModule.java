package de.constt.nexsus_client.client.roots.modules.misc;

import de.constt.nexsus_client.client.annotations.InfoAnnotation;
import de.constt.nexsus_client.client.roots.implementations.CategoryImplementation;
import de.constt.nexsus_client.client.roots.implementations.ModuleImplementation;
import net.minecraft.client.MinecraftClient;

@InfoAnnotation(
        name = "Spammer",
        description = "Automaticly sends messages from your account to chat",
        category = CategoryImplementation.Categories.MISC

)

public class SpammerModule extends ModuleImplementation {
    private int tickCounter = 0;

    @Override
    public void tick() {
        if(tickCounter == 4) {
            this.tickCounter = 0;
            assert MinecraftClient.getInstance().player != null;
            MinecraftClient.getInstance().player.networkHandler.sendChatMessage("Nexsus client currently free!");
            super.tick();
        }
        this.tickCounter++;
    }
}
