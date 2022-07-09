package entolib.events;

import necesse.engine.events.GameEvent;
import necesse.engine.network.client.ClientClient;

// When you leave a server
public class ClientClientDisconnectEvent extends GameEvent {
    public final ClientClient client;

    public ClientClientDisconnectEvent(ClientClient client) {
        this.client = client;
    }
}
