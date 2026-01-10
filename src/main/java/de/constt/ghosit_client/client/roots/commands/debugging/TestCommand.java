package de.constt.ghosit_client.client.roots.commands.debugging;

import de.constt.ghosit_client.client.annotations.CommandAnnotation;
import de.constt.ghosit_client.client.helperFunctions.ChatHelperFunction;
import de.constt.ghosit_client.client.helperFunctions.MacroInputHelperFunction;
import de.constt.ghosit_client.client.helperFunctions.TitleHelperFunction;
import de.constt.ghosit_client.client.roots.implementations.CommandImplementation;
import de.constt.ghosit_client.client.roots.modules.ModuleManager;
import de.constt.ghosit_client.client.roots.modules.misc.DebuggerModule;
import net.minecraft.client.MinecraftClient;

import java.util.concurrent.atomic.AtomicInteger;

@CommandAnnotation(
        name = "Test",
        description = "Start/Stop chat and title function tests",
        command = "test"
)
public class TestCommand extends CommandImplementation {

    @Override
    public void executeCommand(String[] parts) {
        super.executeCommand(parts);

        if (parts == null || parts.length < 2) {
            ChatHelperFunction.sendCSMessageError("Please provide an action: start / stop", false);
            return;
        }

        String actionArg = parts[1].toLowerCase();

        switch (actionArg) {
            case "start":
                runChatAndTitleTests();
                break;

            case "stop":
                TitleHelperFunction.sendCSTitleWarning("Stopped Tests!", false);
                break;

            default:
                ChatHelperFunction.sendCSMessageError("Please provide an action: start / stop", false);
                break;
        }
    }

    private void runChatAndTitleTests() {
        TitleHelperFunction.sendCSTitleWarning("Starting Chat & Title Tests!", false);

        boolean debuggerEnabled = ModuleManager.isEnabled(DebuggerModule.class);
        AtomicInteger testsPassed = new AtomicInteger();
        AtomicInteger testsFailed = new AtomicInteger();

        MinecraftClient mc = MinecraftClient.getInstance();
        long startTime = System.currentTimeMillis();

        new Thread(() -> {
            try {
                // Chat functions
                testChatFunction(() -> ChatHelperFunction.sendCSMessageNeutral("Test: sendCSMessageNeutral", false), "sendCSMessageNeutral", debuggerEnabled, testsPassed, testsFailed);
                testChatFunction(() -> ChatHelperFunction.sendCSMessageWarning("Test: sendCSMessageWarning", false), "sendCSMessageWarning", debuggerEnabled, testsPassed, testsFailed);
                testChatFunction(() -> ChatHelperFunction.sendCSMessageError("Test: sendCSMessageError", false), "sendCSMessageError", debuggerEnabled, testsPassed, testsFailed);
                testChatFunction(() -> ChatHelperFunction.sendCSMessageNeutral("Test: sendCSMessage", false), "sendCSMessage", debuggerEnabled, testsPassed, testsFailed);

                // Title functions
                testTitleFunction(() -> mc.execute(() -> TitleHelperFunction.sendCSTitleWarning("Test: sendCSTitleWarning", false)), "sendCSTitleWarning", debuggerEnabled, testsPassed, testsFailed);
                testTitleFunction(() -> mc.execute(() -> TitleHelperFunction.sendCSTitleWarning("Test: sendCSTitleNeutral", false)), "sendCSTitleNeutral", debuggerEnabled, testsPassed, testsFailed);
                testTitleFunction(() -> mc.execute(() -> TitleHelperFunction.sendCSTitleWarning("Test: sendCSTitleError", false)), "sendCSTitleError", debuggerEnabled, testsPassed, testsFailed);

            } catch (Exception ignored) {}
            finally {
                long duration = System.currentTimeMillis() - startTime;
                mc.execute(() -> TitleHelperFunction.sendCSTitleWarning("Chat & Title Tests Completed", false));
                ChatHelperFunction.sendCSMessageNeutral(
                        "Chat & Title Test Results: Passed: " + testsPassed +
                                " | Failed: " + testsFailed +
                                " | Duration: " + duration + "ms" +
                                (debuggerEnabled ? " | Debugger enabled" : ""), false
                );
            }
        }).start();
    }

    private void testChatFunction(Runnable chatAction, String name, boolean debuggerEnabled, AtomicInteger passed, AtomicInteger failed) {
        try {
            chatAction.run();
            passed.getAndIncrement();
            if (debuggerEnabled)
                ChatHelperFunction.sendCSMessageNeutral("Debugger: " + name + " executed", false);
        } catch (Exception e) {
            failed.getAndIncrement();
            if (debuggerEnabled)
                ChatHelperFunction.sendCSMessageNeutral("Debugger: " + name + " failed", false);
        }
    }

    private void testTitleFunction(Runnable titleAction, String name, boolean debuggerEnabled, AtomicInteger passed, AtomicInteger failed) {
        try {
            titleAction.run();
            passed.getAndIncrement();
            if (debuggerEnabled)
                ChatHelperFunction.sendCSMessageNeutral("Debugger: " + name + " executed", false);
        } catch (Exception e) {
            failed.getAndIncrement();
            if (debuggerEnabled)
                ChatHelperFunction.sendCSMessageNeutral("Debugger: " + name + " failed", false);
        }
    }
}
