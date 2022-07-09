package entolib.option;

import com.electronwill.nightconfig.core.Config;
import com.electronwill.nightconfig.core.file.FileConfig;
import com.electronwill.nightconfig.toml.TomlFormat;
import com.electronwill.nightconfig.toml.TomlWriter;
import entolib.events.ModOptionChangedEvent;
import necesse.engine.GameEvents;
import necesse.engine.GameLog;
import necesse.engine.GlobalData;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ModOptionBuilder {
    protected static Config config = null;
    protected static boolean isLocal;
    private static FileConfig localConfig = null;
    private static Config serverConfig = null;

    public static void init() throws IOException {
        Path configFilePath = Paths.get(GlobalData.cfgPath() + "ModOptions.toml");
        GameLog.debug.println(configFilePath.toUri());
        if (!Files.exists(configFilePath)) {
            Files.createFile(configFilePath);
        }
        localConfig = FileConfig.of(configFilePath.toFile());
        localConfig.load();
        config = localConfig;
        serverConfig = Config.inMemory();
        isLocal = true;
    }

    public static void dispose() {
        localConfig.save();
    }

    public static ModOption build(String modId) {
        return new ModOption(modId);
    }

    protected static void updateServerConfig(Config newConfig) {
        serverConfig = newConfig;
    }

    public static void switchToServerConfig() {
        isLocal = false;
        config = serverConfig;
        GameEvents.triggerEvent(new ModOptionChangedEvent(false));
    }

    public static void switchToLocalConfig() {
        isLocal = true;
        config = localConfig;
        GameEvents.triggerEvent(new ModOptionChangedEvent(true));
    }

    public static String getLocalConfigString() {
        TomlWriter tomlWriter = TomlFormat.instance().createWriter();
        return tomlWriter.writeToString(localConfig);
    }

    public static void reloadModOptions() {
        if (localConfig != null) {
            localConfig.load();
        }
    }

}
