package de.constt.ghosit_client.client.roots.modules.world;

import de.constt.ghosit_client.client.annotations.ModuleInfoAnnotation;
import de.constt.ghosit_client.client.roots.implementations.CategoryImplementation;
import de.constt.ghosit_client.client.roots.implementations.ModuleImplementation;
import net.minecraft.client.MinecraftClient;
import net.minecraft.util.math.Vec3d;

@ModuleInfoAnnotation(
        name = "Freecam",
        description = "Client-side freecam, flies through blocks safely",
        category = CategoryImplementation.Categories.WORLD,
        internalModuleName = "freecam"
)
public class FreecamModule extends ModuleImplementation {

    private Vec3d cameraPos;
    private Vec3d savedPos;
    private boolean active = false;
    private final MinecraftClient mc = MinecraftClient.getInstance();

    @Override
    public void onEnable() {
        if (mc.player == null) return;

        savedPos = mc.player.getPos();
        cameraPos = savedPos;
        mc.setCameraEntity(mc.player); // temporarily the player, we will offset manually
        active = true;
    }

    @Override
    public void onDisable() {
        if (!active) return;

        mc.setCameraEntity(mc.player);
        mc.player.updatePosition(savedPos.x, savedPos.y, savedPos.z);
        active = false;
    }

    @Override
    public void tick() {
        if (!active || mc.player == null) return;

        double speed = 0.5;

        Vec3d forward = mc.player.getRotationVector().multiply(speed);
        Vec3d right = mc.player.getRotationVector().crossProduct(new Vec3d(0, 1, 0)).normalize().multiply(speed);

        if (mc.options.forwardKey.isPressed()) cameraPos = cameraPos.add(forward);
        if (mc.options.backKey.isPressed()) cameraPos = cameraPos.subtract(forward);
        if (mc.options.leftKey.isPressed()) cameraPos = cameraPos.add(right);
        if (mc.options.rightKey.isPressed()) cameraPos = cameraPos.subtract(right);
        if (mc.options.jumpKey.isPressed()) cameraPos = cameraPos.add(0, speed, 0);
        if (mc.options.sneakKey.isPressed()) cameraPos = cameraPos.add(0, -speed, 0);
    }
}
