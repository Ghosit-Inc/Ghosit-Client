package de.constt.ghosit_client.client.roots.commands.client;

import de.constt.ghosit_client.client.annotations.CommandAnnotation;
import de.constt.ghosit_client.client.helperFunctions.ChatHelperFunction;
import de.constt.ghosit_client.client.helperFunctions.TitleHelperFunction;
import de.constt.ghosit_client.client.roots.implementations.CommandImplementation;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.Mouse;
import org.lwjgl.glfw.GLFW;

@CommandAnnotation(
        name = "Lock Mouse",
        description = "Locks or unlocks your mouse and prevents camera rotation",
        command = "lockmouse"
)
public class LockMouseCommand extends CommandImplementation {

    @Override
    public void executeCommand(String[] parts) {
        ChatHelperFunction.sendCSMessageError("Not finished yet!", false);
    }
}
