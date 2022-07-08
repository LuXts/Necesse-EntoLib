package entolib.network;

import entolib.option.ModOptionBuilder;
import entolib.option.ModOptionPacket;
import necesse.engine.GameEventListener;
import necesse.engine.GameEvents;
import necesse.engine.events.ServerClientConnectedEvent;
import necesse.engine.events.ServerClientDisconnectEvent;
import necesse.engine.events.ServerStartEvent;
import necesse.engine.network.server.Server;
import necesse.engine.network.server.ServerClient;

public class ServerNetworkManager {

    private static Server server;

    public static void onClientConnected(ServerClient client) {
        server.network.sendToClientsAt(new ModOptionPacket(ModOptionBuilder.getLocalConfigString()), client);
    }

    public static void onClientDisconnect(ServerClient client) {

    }

    public static void onServerStart(Server server) {
        // Get Server Instance.
        ServerNetworkManager.server = server;
    }

    public static void init() {

        // On Server Start.
        GameEvents.addListener(ServerStartEvent.class, new GameEventListener<ServerStartEvent>() {
            @Override
            public void onEvent(ServerStartEvent event) {
                onServerStart(event.server);
            }
        });

        // On Client Connected.
        GameEvents.addListener(ServerClientConnectedEvent.class, new GameEventListener<ServerClientConnectedEvent>() {
            @Override
            public void onEvent(ServerClientConnectedEvent event) {
                onClientConnected(event.client);
            }
        });

        // On Client Disconnect.
        GameEvents.addListener(ServerClientDisconnectEvent.class, new GameEventListener<ServerClientDisconnectEvent>() {
            @Override
            public void onEvent(ServerClientDisconnectEvent event) {
                onClientDisconnect(event.client);
            }
        });
    }
}
