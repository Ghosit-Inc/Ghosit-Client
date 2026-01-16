package de.constt.ghosit_client.client.roots.modules.skyblock;

import de.constt.ghosit_client.client.annotations.ModuleInfoAnnotation;
import de.constt.ghosit_client.client.roots.implementations.CategoryImplementation;
import de.constt.ghosit_client.client.roots.implementations.ModuleImplementation;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.slot.Slot;
import net.minecraft.screen.slot.SlotActionType;
import java.util.Random;

@ModuleInfoAnnotation(
        name = "ExperimentationHelper",
        description = "Auto-clicks the Experimentation Table like a human",
        category = CategoryImplementation.Categories.SKYBLOCK,
        internalModuleName = "experimentationhelper"
)
public class AutoExperimentationModule extends ModuleImplementation {

    private final MinecraftClient mc = MinecraftClient.getInstance();
    private final Random random = new Random();
    private long nextClickTime = 0;

    @Override
    public void tick() {
        Screen screen = mc.currentScreen;
        if (!isExperimentation(screen)) return;

        HandledScreen<?> hs = (HandledScreen<?>) screen;

        for (int i = 0; i < hs.getScreenHandler().slots.size(); i++) {
            if (shouldClickSlot(hs.getScreenHandler().getSlot(i)) && System.currentTimeMillis() >= nextClickTime) {
                clickSlot(hs, i);
                nextClickTime = System.currentTimeMillis() + getRandomDelay();
                return; // one click per tick cycle
            }
        }
    }

    private boolean isExperimentation(Screen screen) {
        if (!(screen instanceof HandledScreen<?> hs)) return false;
        String title = hs.getTitle().getString().toLowerCase();
        return title.contains("experiment");
    }

    private boolean shouldClickSlot(Slot slot) {
        if (slot == null || !slot.hasStack()) return false;

        ItemStack stack = slot.getStack();
        String name = stack.getName().getString().toLowerCase();

        if (name.contains("chronomatron") || name.contains("clock") || name.contains("orange") || name.contains("red"))
            return true;

        if (name.contains("note") || name.contains("light"))
            return true;

        return name.contains("card") && stack.hasEnchantments();
    }

    private void clickSlot(HandledScreen<?> screen, int slot) {
        mc.interactionManager.clickSlot(
                screen.getScreenHandler().syncId,
                slot,
                0,
                SlotActionType.PICKUP,
                mc.player
        );
    }

    private long getRandomDelay() {
        // Random delay between 150ms and 400ms to simulate human clicking
        return 150 + random.nextInt(250);
    }
}
