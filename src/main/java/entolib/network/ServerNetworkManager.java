package entolib.network;

import entolib.option.ModOptionBuilder;
import entolib.option.ModOptionPacket;
import necesse.engine.GameEventListener;
import necesse.engine.GameEvents;
import necesse.engine.events.ServerClientConnectedEvent;
import necesse.engine.events.ServerClientDisconnectEvent;
import necesse.engine.events.ServerStartEvent;
import necesse.engine.events.ServerStopEvent;
import necesse.engine.network.server.Server;
import necesse.engine.network.server.ServerClient;

public class ServerNetworkManager {


    public static Server server = null;

    private static void onClientConnected(ServerClient client) {
        server.network.sendToClientsAt(new ModOptionPacket(ModOptionBuilder.getLocalConfigString()), client);
    }

    private static void onClientDisconnect(ServerClient client) {

    }

    private static void onServerStart(Server server) {
        // Get Server Instance.
        ServerNetworkManager.server = server;
    }

    private static void onServerStop(Server server) {
        ServerNetworkManager.server = null;
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

        // On Server Stop.
        GameEvents.addListener(ServerStopEvent.class, new GameEventListener<ServerStopEvent>() {
            @Override
            public void onEvent(ServerStopEvent event) {
                onServerStop(event.server);
            }
        });
    }
}
