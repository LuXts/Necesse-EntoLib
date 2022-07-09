package entolib.events;

import necesse.engine.events.GameEvent;
import necesse.engine.network.client.ClientClient;

// When you join a server
public class ClientClientConnectedEvent extends GameEvent {
    public final ClientClient client;

    public ClientClientConnectedEvent(ClientClient client) {
        this.client = client;
    }
}
