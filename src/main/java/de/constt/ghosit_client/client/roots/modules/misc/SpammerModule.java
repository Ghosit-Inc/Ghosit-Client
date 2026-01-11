package de.constt.ghosit_client.client.roots.modules.misc;

import de.constt.ghosit_client.client.annotations.ModuleInfoAnnotation;
import de.constt.ghosit_client.client.roots.implementations.CategoryImplementation;
import de.constt.ghosit_client.client.roots.implementations.ModuleImplementation;
import de.constt.ghosit_client.client.roots.settings.BooleanSettingImplementation;
import de.constt.ghosit_client.client.roots.settings.NumberSettingImplementation;
import de.constt.ghosit_client.client.roots.settings.StringSettingImplementation;
import net.minecraft.client.MinecraftClient;

@ModuleInfoAnnotation(
        name = "Spammer",
        description = "Automatically sends messages from your account to chat",
        category = CategoryImplementation.Categories.MISC,
        internalModuleName = "spammer"
)
public class SpammerModule extends ModuleImplementation {
    StringSettingImplementation message;
    NumberSettingImplementation tickDelay;

    public SpammerModule() {
        message = new StringSettingImplementation("MESSAGE", "MESSAGE");
        tickDelay = new NumberSettingImplementation("TICKS-DELAY", 4);

        registerSetting(message);
        registerSetting(tickDelay);
    }

    private int tickCounter = 0;

    @Override
    public void tick() {
        MinecraftClient mc = MinecraftClient.getInstance();
        if (mc == null || mc.player == null || mc.world == null || mc.player.networkHandler == null) {
            tickCounter = 0;
            return;
        }

        if (++tickCounter >= tickDelay.get()) {
            tickCounter = 0;
            mc.player.networkHandler.sendChatMessage(message.get());
        }

        super.tick();
    }
}
