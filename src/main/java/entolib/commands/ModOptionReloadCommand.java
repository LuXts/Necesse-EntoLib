package entolib.commands;

import entolib.option.ModOptionBuilder;
import entolib.option.ModOptionPacket;
import necesse.engine.commands.CommandLog;
import necesse.engine.commands.ModularChatCommand;
import necesse.engine.commands.PermissionLevel;
import necesse.engine.network.client.Client;
import necesse.engine.network.server.Server;
import necesse.engine.network.server.ServerClient;

public class ModOptionReloadCommand extends ModularChatCommand {

    public ModOptionReloadCommand() {
        super("reloadModOptions", "Reload all mod option", PermissionLevel.ADMIN, false);
    }

    @Override
    public void runModular(Client client, Server server, ServerClient serverClient, Object[] objects, String[] strings, CommandLog commandLog) {
        ModOptionBuilder.reloadModOptions();
        server.network.sendToAllClients(new ModOptionPacket(ModOptionBuilder.getLocalConfigString()));
    }
}
