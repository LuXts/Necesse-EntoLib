package entolib.network.patches;


import com.codedisaster.steamworks.SteamID;
import com.codedisaster.steamworks.SteamResult;
import entolib.events.ClientClientConnectedEvent;
import necesse.engine.GameEvents;
import necesse.engine.GameLog;
import necesse.engine.modLoader.annotations.ModMethodPatch;
import necesse.engine.network.client.Client;
import necesse.engine.network.client.ClientClient;
import necesse.engine.network.client.ClientSteamLobby;
import net.bytebuddy.asm.Advice;

import java.lang.reflect.Field;

@ModMethodPatch(target = ClientSteamLobby.class, name = "onLobbyCreated", arguments = {SteamResult.class, SteamID.class})
public class ClientSteamLobbyOnLobbyCreatedPatch {
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

                // Trigger ClientClientConnectedEvent
                GameEvents.triggerEvent(new ClientClientConnectedEvent(clientClient));
            }
        } catch (Exception e) {
            GameLog.err.println("e");
        }
    }
}
