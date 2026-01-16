package de.constt.ghosit_client.client.roots.modules.skyblock;

import de.constt.ghosit_client.client.annotations.ModuleInfoAnnotation;
import de.constt.ghosit_client.client.helperFunctions.SoundHelperFunction;
import de.constt.ghosit_client.client.helperFunctions.TitleHelperFunction;
import de.constt.ghosit_client.client.helperFunctions.ToastHelperFunction;
import de.constt.ghosit_client.client.roots.implementations.CategoryImplementation;
import de.constt.ghosit_client.client.roots.implementations.ModuleImplementation;
import net.minecraft.block.Blocks;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.AxeItem;
import net.minecraft.item.HoeItem;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;

@ModuleInfoAnnotation(
        name = "Anti Macro Check",
        description = "Alerts you if you get macro checked",
        category = CategoryImplementation.Categories.SKYBLOCK,
        internalModuleName = "antimacrocheck"
)
public class AntiMacroCheckModule extends ModuleImplementation {

    private double lastX, lastY, lastZ;
    private long movingStart = -1;

    private long feetBedrockStart = -1;

    private boolean cantMoveAlerted = false;
    private boolean feetBedrockAlerted = false;
    private boolean surroundedBedrockAlerted = false;

    private final int surroundedRadius = 5;

    @Override
    public void onEnable() {
        MinecraftClient mc = MinecraftClient.getInstance();
        PlayerEntity player = mc.player;
        if (player != null) {
            lastX = player.getX();
            lastY = player.getY();
            lastZ = player.getZ();
        }
        movingStart = -1;
        feetBedrockStart = -1;
        cantMoveAlerted = false;
        feetBedrockAlerted = false;
        surroundedBedrockAlerted = false;
    }

    @Override
    public void tick() {
        MinecraftClient mc = MinecraftClient.getInstance();
        PlayerEntity player = mc.player;
        if (player == null || mc.world == null) return;

        double deltaX = Math.abs(player.getX() - lastX);
        double deltaZ = Math.abs(player.getZ() - lastZ);

        boolean isMoving = deltaX > 0.01 || deltaZ > 0.01;
        long currentTime = System.currentTimeMillis();

        // -------------------
        // 1. Can't move alert
        // -------------------
        if (isMoving) {
            if (movingStart == -1) movingStart = currentTime;
            cantMoveAlerted = false;
        } else {
            if (movingStart != -1 && currentTime - movingStart >= 3000 && !cantMoveAlerted) {
                // Check if player is actually HOLDING the tool in hand
                ItemStack mainHand = player.getMainHandStack();
                if (mainHand.getItem() instanceof HoeItem || mainHand.getItem() instanceof AxeItem) {
                    // SoundHelperFunction.playSound(SoundEvents.BLOCK_NOTE_BLOCK_PLING.value(), 1.0F, false);
                    SoundHelperFunction.playCustomSound("sounds/warning.ogg", 100.0F, false);
                    TitleHelperFunction.sendCSTitleWarning("Macro Check! (Cant Move)", false);
                    ToastHelperFunction.showWarning("Macro Check! (Cant Move)", 5000, true);
                    cantMoveAlerted = true;
                }
            }
        }

        // -------------------
        // 2. Feet bedrock alert
        // -------------------
        BlockPos posBelow = BlockPos.ofFloored(player.getX(), player.getY() - 0.1, player.getZ());
        boolean standingOnBedrock = mc.world.getBlockState(posBelow).isOf(Blocks.BEDROCK);

        if (standingOnBedrock) {
            if (!feetBedrockAlerted && currentTime - feetBedrockStart >= 1000) {
                // SoundHelperFunction.playSound(SoundEvents.BLOCK_NOTE_BLOCK_PLING.value(), 1.0F, false);
                SoundHelperFunction.playCustomSound("sounds/warning.ogg", 100.0F, false);
                TitleHelperFunction.sendCSTitleWarning("Macro Check! (Likely)", false);
                ToastHelperFunction.showWarning("Macro Check! (Likely)", 5000, true);
                feetBedrockAlerted = true;
            }
            if (feetBedrockStart == -1) feetBedrockStart = currentTime;
        } else {
            feetBedrockStart = -1;
            feetBedrockAlerted = false;
        }

        // -------------------
        // 3. Surrounded bedrock alert
        // -------------------
        boolean surrounded = false;
        outer:
        for (int x = -surroundedRadius; x <= surroundedRadius; x++) {
            for (int y = -surroundedRadius; y <= surroundedRadius; y++) {
                for (int z = -surroundedRadius; z <= surroundedRadius; z++) {
                    BlockPos checkPos = posBelow.add(x, y, z);
                    if (mc.world.getBlockState(checkPos).isOf(Blocks.BEDROCK)) {
                        surrounded = true;
                        break outer;
                    }
                }
            }
        }

        if (surrounded && !surroundedBedrockAlerted) {
            // SoundHelperFunction.playSound(SoundEvents.BLOCK_NOTE_BLOCK_PLING.value(), 1.0F, false);
            SoundHelperFunction.playCustomSound("sounds/warning.ogg", 100.0F, false);
            TitleHelperFunction.sendCSTitleWarning("Macro Check! (Most likely)", false);
            ToastHelperFunction.showWarning("Macro Check! (Most likely)", 5000, true);
            surroundedBedrockAlerted = true;
        }

        if (!surrounded) {
            surroundedBedrockAlerted = false;
        }

        lastX = player.getX();
        lastY = player.getY();
        lastZ = player.getZ();
    }
}
