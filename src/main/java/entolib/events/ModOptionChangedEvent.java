package entolib.events;

import necesse.engine.events.GameEvent;

public class ModOptionChangedEvent extends GameEvent {
    public final boolean isLocalConfig;

    public ModOptionChangedEvent(boolean isLocalConfig) {
        this.isLocalConfig = isLocalConfig;
    }
}
