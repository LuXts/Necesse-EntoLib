## Introduction

A Shared Library mod for Necesse.

[Steam Workshop](https://steamcommunity.com/sharedfiles/filedetails/?id=2832163679)

I think providing a shared library would avoid a lot of duplication of work. So this project was born.

This project currently offers the following features.

* Mod configuration and autoload.
* Reload Mod configuration command.
* Events for client connections and Mod configure reload.

### New Events

* ModOptionChangedEvent
* ClientClientDisconnectEvent
* ClientClientConnectedEvent

### Mod Option Path

TOML config path:

* Linux: `~/.config/Necesse/cfg/ModOptions.toml`
* Windows: `C:/Users/USER_NAME/AppData/Roaming/Necesse/cfg/ModOptions.toml`

## How to use

### Glimpse:

```java
public class ExampleMod {

    public void init() {
        ModOption option = ModOptionBuilder.build("EntoLibExample");
        GameLog.out.println(option.getString("Example", "Hello World!"));

        GameEvents.addListener(ServerStartEvent.class, new GameEventListener<ServerStartEvent>() {
            @Override
            public void onEvent(ServerStartEvent event) {
                option.setValue("Example", "Value Changed!");
            }
        });

        GameEvents.addListener(ModOptionChangedEvent.class, new GameEventListener<ModOptionChangedEvent>() {
            @Override
            public void onEvent(ModOptionChangedEvent event) {
                if (event.isLocalConfig) {
                    GameLog.out.println("Local Option: " + option.getString("Example", "Hello World!"));
                } else {
                    GameLog.out.println("Server Option: " + option.getString("Example", "Hello World!"));
                }
            }
        });
    }
}

```

**Beta interface is unstable.** I did my best to write. But maybe there are better ones.

## Contributions

Some of my code should be very bad. Contributions are welcome.

You can communicate with me in English, although I need to use translation software in many places. Using translated
Chinese to communicate with me is not as good as having me read English.

## TODO:

* Add more events.

Check out the [modding wiki page](https://necessewiki.com/Modding) for more.