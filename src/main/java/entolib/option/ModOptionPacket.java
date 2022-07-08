package entolib.option;

import com.electronwill.nightconfig.toml.TomlFormat;
import com.electronwill.nightconfig.toml.TomlParser;
import necesse.engine.network.NetworkPacket;
import necesse.engine.network.Packet;
import necesse.engine.network.PacketReader;
import necesse.engine.network.PacketWriter;
import necesse.engine.network.client.Client;

public class ModOptionPacket extends Packet {
    // Toml Config String.
    private final String configStr;

    public ModOptionPacket(byte[] data) {
        super(data);
        PacketReader reader = new PacketReader(this);
        configStr = reader.getNextString();
    }

    public ModOptionPacket(String config) {
        configStr = config;
        PacketWriter writer = new PacketWriter(this);
        writer.putNextString(configStr);
    }

    @Override
    public void processClient(NetworkPacket packet, Client client) {
        TomlParser parser = TomlFormat.instance().createParser();
        ModOptionBuilder.updateServerConfig(parser.parse(configStr));
        ModOptionBuilder.switchToServerConfig();
    }
}
