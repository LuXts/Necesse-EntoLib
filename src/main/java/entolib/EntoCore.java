package entolib;

import entolib.network.ClientNetworkManager;
import entolib.network.ServerNetworkManager;
import entolib.option.ModOptionBuilder;
import entolib.option.ModOptionPacket;
import necesse.engine.GameLog;
import necesse.engine.modLoader.annotations.ModEntry;
import necesse.engine.registries.PacketRegistry;

import java.io.IOException;

@ModEntry
public class EntoCore {

    public void preInit() throws IOException {
        GameLog.out.println("Ento Lib Initializing.");
        ModOptionBuilder.init();
        ServerNetworkManager.init();
        ClientNetworkManager.init();
        PacketRegistry.registerPacket(ModOptionPacket.class);
    }

    public void dispose() {
        ModOptionBuilder.dispose();
    }


}
