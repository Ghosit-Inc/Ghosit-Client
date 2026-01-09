package de.constt.ghosit_client.client.events.chat;

import de.constt.ghosit_client.client.helperFunctions.ChatHelperFunction;
import de.constt.ghosit_client.client.helperFunctions.CommandAnnotationHelper;
import de.constt.ghosit_client.client.roots.commands.CommandManager;
import de.constt.ghosit_client.client.roots.modules.ModuleManager;
import de.constt.ghosit_client.client.roots.modules.events.ChatCalculatorModule;
import de.constt.ghosit_client.client.roots.modules.misc.DebuggerModule;
import net.fabricmc.fabric.api.client.message.v1.ClientSendMessageEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;

import java.util.Arrays;
import java.util.Objects;
import java.util.regex.Pattern;

public class ClientReciveMessageEvent {

    public static void register() {
        ClientSendMessageEvents.ALLOW_CHAT.register((chatMSG) -> {
            ClientPlayerEntity player = MinecraftClient.getInstance().player;

            assert player != null;

            if (!chatMSG.startsWith("/") && chatMSG.startsWith("*")) {
                String[] parts = chatMSG.split(" ");
                String command = parts[0].substring(1);

                if(ModuleManager.isEnabled(DebuggerModule.class)) {
                    ChatHelperFunction.sendCSMessageWarning("Command: "+command, true);
                    ChatHelperFunction.sendCSMessageWarning("Parts: "+ Arrays.toString(parts), true);
                }

                CommandManager.getCommands().forEach(commandFL -> {
                    if(command.equals(CommandAnnotationHelper.getCommand(commandFL.getClass()))) {
                        Objects.requireNonNull(CommandManager.getCommand(commandFL.getClass())).executeCommand(parts);
                    }
                });
                return false;
            }

            if(ModuleManager.isEnabled(ChatCalculatorModule.class)) {
                int calculatedNumber = 0;
                int n1, n2;
                String operator = null;

                // Detect operator
                if(chatMSG.contains("+")) operator = "+";
                else if(chatMSG.contains("-")) operator = "-";
                else if(chatMSG.contains("*")) operator = "*";
                else if(chatMSG.contains("/")) operator = "/";

                if(operator != null) {
                    String[] parts = chatMSG.split(Pattern.quote(operator));
                    if(parts.length == 2) {
                        try {
                            n1 = Integer.parseInt(parts[0].trim());
                            n2 = Integer.parseInt(parts[1].trim());

                            switch (operator) {
                                case "*" -> calculatedNumber = n1 * n2;
                                case "+" -> calculatedNumber = n1 + n2;
                                case "-" -> calculatedNumber = n1 - n2;
                                case "/" -> {
                                    if(n2 != 0) calculatedNumber = n1 / n2;
                                    else {
                                        ChatHelperFunction.sendCSMessageError("Division by zero! (" + n1 + " " + operator + " " + n2 + ")!", false);
                                    }
                                }
                            }
                            ChatHelperFunction.sendCSMessageNeutral("Calculation: " + calculatedNumber + " (" + n1 + " " + operator + " " + n2 + ")!", false);

                        } catch(NumberFormatException e) {
                            ChatHelperFunction.sendCSMessageError("Invalid numbers! (" + chatMSG + ")", false);
                        }
                    } else {
                        ChatHelperFunction.sendCSMessageError("Invalid format! (" + chatMSG + ")", false);
                    }
                } else {
                    ChatHelperFunction.sendCSMessageError("No operator found! (" + chatMSG + ")", false);
                }
            }


            return true; // allow normal messages
        });
    }
}