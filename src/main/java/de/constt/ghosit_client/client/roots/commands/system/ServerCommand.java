package de.constt.ghosit_client.client.roots.commands.system;

import de.constt.ghosit_client.client.annotations.CommandAnnotation;
import de.constt.ghosit_client.client.helperFunctions.ChatHelperFunction;
import de.constt.ghosit_client.client.roots.implementations.CommandImplementation;
import net.minecraft.client.MinecraftClient;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@CommandAnnotation(
        name = "Server",
        description = "Shows info about the server",
        command = "server"
)
public class ServerCommand extends CommandImplementation {

    public ServerCommand() {
        this.args = new String[]{"general-info", "plugins", "tps", "ports"};
    }

    MinecraftClient mc = MinecraftClient.getInstance();

    private static final Set<String> ANTICHEAT_LIST = Set.of("nocheatplus", "negativity", "warden", "horizon", "illegalstack", "coreprotect", "exploitsx", "vulcan", "abc", "spartan", "kauri", "anticheatreloaded", "witherac", "godseye", "matrix", "wraith", "antixrayheuristics", "grimac", "themis", "foxaddition", "guardianac", "ggintegrity", "lightanticheat", "anarchyexploitfixes");
    private static final Set<String> VERSION_ALIASES = Set.of("version", "ver", "about", "bukkit:version", "bukkit:ver", "bukkit:about");

    @Override
    public void executeCommand(String[] parts) {
        if (parts == null || parts.length < 2) {
            ChatHelperFunction.sendCSMessageError(
                    "Please provide an argument: " + String.join(" / ", args),
                    false
            );
            return;
        }

        if (mc.player == null || mc.getCurrentServerEntry() == null) {
            ChatHelperFunction.sendCSMessageError("Not connected to a multiplayer server", false);
            return;
        }

        var server = mc.getCurrentServerEntry();
        String actionArg = parts[1].toLowerCase();



        switch (actionArg) {
            case "general-info":
                ChatHelperFunction.sendCSMessageNeutral("Name: " + server.name, true);
                ChatHelperFunction.sendCSMessageNeutral("Address: " + server.address, true);
                ChatHelperFunction.sendCSMessageNeutral("Label: " + server.label, true);
                ChatHelperFunction.sendCSMessageNeutral("Version Name: " + server.version.getString(), true);
                ChatHelperFunction.sendCSMessageNeutral("Ping: " + server.ping + " ms", true);
                ChatHelperFunction.sendCSMessageNeutral("Local: " + server.isLocal(), true);
                ChatHelperFunction.sendCSMessageNeutral("Protocol Version: " + server.protocolVersion, true);
                ChatHelperFunction.sendCSMessageNeutral("Icon Present: " + (server.getFavicon() != null), true);
                break;

            case "plugins":
                // Common plugin names to check for in the MOTD
                String[] knownPlugins = {
                        "worldguard", "essentials", "luckperms", "vault", "worldedit",
                        "dynmap", "griefprevention", "mcMMO", "fastasyncworldedit"
                };

                List<String> detectedPlugins = new ArrayList<>();
                String motd = server.label.getString().toLowerCase();

                for(String plugin : knownPlugins) {
                    if(motd.contains(plugin)) {
                        detectedPlugins.add(plugin);
                    }
                }

                if(!detectedPlugins.isEmpty()) {
                    ChatHelperFunction.sendCSMessageNeutral("Detected Plugins: " + String.join(", ", detectedPlugins), true);
                } else {
                    ChatHelperFunction.sendCSMessageNeutral("No known plugins detected in MOTD.", true);
                }
                if(!detectedPlugins.isEmpty()) {
                    ChatHelperFunction.sendCSMessageNeutral("Detected Plugins: " + String.join(", ", detectedPlugins), true);
                }
                break;

            case "tps":
                ChatHelperFunction.sendCSMessageNeutral("TPS data unavailable.", true);
                break;

            case "ports":
                ChatHelperFunction.sendCSMessageNeutral("No port information available.", true);
                break;
            default:
                ChatHelperFunction.sendCSMessageError("Unknown argument: " + actionArg, false);
                break;
        }

        super.executeCommand(parts);
    }

}
