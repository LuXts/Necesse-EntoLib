package entolib.network;

import entolib.events.ClientClientConnectedEvent;
import entolib.events.ClientClientDisconnectEvent;
import entolib.option.ModOptionBuilder;
import necesse.engine.GameEventListener;
import necesse.engine.GameEvents;
import necesse.engine.network.client.ClientClient;

public class ClientNetworkManager {

    public static void onServerConnected(ClientClient client) {
    }

    public static void onServerDisconnect(ClientClient client) {
        ModOptionBuilder.switchToLocalConfig();
    }

    public static void init() {
        // Client connection to the server.
        GameEvents.addListener(ClientClientConnectedEvent.class, new GameEventListener<ClientClientConnectedEvent>() {
            @Override
            public void onEvent(ClientClientConnectedEvent event) {
                // GameLog.debug.println("Client connection to the server.");
                onServerConnected(event.client);
            }
        });
        // Client disconnects from the server.
        GameEvents.addListener(ClientClientDisconnectEvent.class, new GameEventListener<ClientClientDisconnectEvent>() {
            @Override
            public void onEvent(ClientClientDisconnectEvent event) {
                // GameLog.debug.println("Client disconnects from the server");
                onServerDisconnect(event.client);
            }
        });
    }
}
