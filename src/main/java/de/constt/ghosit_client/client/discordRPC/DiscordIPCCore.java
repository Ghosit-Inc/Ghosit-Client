package de.constt.ghosit_client.client.discordRPC;

import com.google.gson.JsonObject;
import com.jagrosh.discordipc.IPCClient;
import com.jagrosh.discordipc.IPCListener;
import com.jagrosh.discordipc.entities.ActivityType;
import com.jagrosh.discordipc.entities.Packet;
import com.jagrosh.discordipc.entities.RichPresence;
import com.jagrosh.discordipc.entities.StatusDisplayType;
import com.jagrosh.discordipc.entities.User;
import de.constt.ghosit_client.client.config.GhositConfigData;
import de.constt.ghosit_client.client.helperFunctions.ChatHelperFunction;
import de.constt.ghosit_client.client.states.AddressState;
import net.minecraft.SharedConstants;

import java.time.OffsetDateTime;

public final class DiscordIPCCore {

    private static IPCClient client;
    private static boolean active;
    private static final long CLIENT_ID = 1450138306141618227L;

    public static void start() {
        if (active) return;

        client = new IPCClient(CLIENT_ID);

        try {
            active = true;
        } catch (Throwable t) {
            active = false;
            return;
        }

        String clientVersion = GhositConfigData.getConfigValue("clientVersion");
        String versionId = SharedConstants.getGameVersion().getId();

        client.setListener(new IPCListener() {
            @Override
            public void onReady(IPCClient c) {
                try {
                    RichPresence.Builder builder = new RichPresence.Builder()
                            .setActivityType(ActivityType.Playing)
                            .setStatusDisplayType(StatusDisplayType.State)
                            .setName(ChatHelperFunction.getPrefix(true) +" Client")
                            .setState("v" + clientVersion)
                            .setDetails(AddressState.getAddress() + " | " + versionId)
                            .setLargeImage("logo")
                            .setStartTimestamp(OffsetDateTime.now().toEpochSecond());

                    c.sendRichPresence(builder.build());
                } catch (Throwable ignored) {
                }
            }

            @Override public void onPacketSent(IPCClient c, Packet p) {}
            @Override public void onPacketReceived(IPCClient c, Packet p) {}
            @Override public void onActivityJoin(IPCClient c, String s) {}
            @Override public void onActivitySpectate(IPCClient c, String s) {}
            @Override public void onActivityJoinRequest(IPCClient c, String s, User u) {}
            @Override public void onClose(IPCClient c, JsonObject j) {}
            @Override public void onDisconnect(IPCClient c, Throwable t) {}
        });

        try {
            client.connect();
        } catch (Throwable t) {
            shutdown();
        }
    }

    public static void shutdown() {
        try {
            if (client != null) {
                client.close();
            }
        } catch (Throwable ignored) {
        } finally {
            client = null;
            active = false;
        }
    }
}
