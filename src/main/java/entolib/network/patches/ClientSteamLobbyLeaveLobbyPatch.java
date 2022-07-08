package entolib.network.patches;

import entolib.events.ClientClientDisconnectEvent;
import necesse.engine.GameEvents;
import necesse.engine.GameLog;
import necesse.engine.modLoader.annotations.ModMethodPatch;
import necesse.engine.network.client.Client;
import necesse.engine.network.client.ClientClient;
import necesse.engine.network.client.ClientSteamLobby;
import net.bytebuddy.asm.Advice;

import java.lang.reflect.Field;

@ModMethodPatch(target = ClientSteamLobby.class, name = "leaveLobby", arguments = {})
public class ClientSteamLobbyLeaveLobbyPatch {
    @Advice.OnMethodEnter()
    static void onEnter(@Advice.This ClientSteamLobby thisLobby) {
        try {
            // Get ClientClient Instance
            Class<ClientSteamLobby> clazz = (Class<ClientSteamLobby>) thisLobby.getClass();
            Field client = clazz.getDeclaredField("client");
            client.setAccessible(true);
            ClientClient clientClient = ((Client) client.get(thisLobby)).getClient();

            if (clientClient != null) {
                GameLog.out.println(clientClient.getClass());

                // Trigger ClientClientDisconnectEvent
                GameEvents.triggerEvent(new ClientClientDisconnectEvent(clientClient));
            }
        } catch (Exception e) {
            GameLog.err.println("e");
        }
    }
}
