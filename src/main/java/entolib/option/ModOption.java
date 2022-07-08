package entolib.option;

public class ModOption {
    public final String modId;

    protected ModOption(String modId) {
        this.modId = modId;
    }

    public void setValue(String key, Object value) {
        if (ModOptionBuilder.isLocal) {
            ModOptionBuilder.config.set(modId + "." + key, value);
        }
    }

    public String getString(String key, String defaultValue) {
        Object value = ModOptionBuilder.config.get(modId + "." + key);
        if (value instanceof String) {
            return (String) value;
        }
        this.setValue(key, defaultValue);
        return defaultValue;
    }

    public Long getLong(String key, Long defaultValue) {
        Object value = ModOptionBuilder.config.get(modId + "." + key);
        if (value instanceof Long) {
            return (Long) value;
        }
        this.setValue(key, defaultValue);
        return defaultValue;
    }

    public Double getDouble(String key, Double defaultValue) {
        Object value = ModOptionBuilder.config.get(modId + "." + key);
        if (value instanceof Double) {
            return (Double) value;
        }
        this.setValue(key, defaultValue);
        return defaultValue;
    }

    public Boolean getBoolean(String key, Boolean defaultValue) {
        Object value = ModOptionBuilder.config.get(modId + "." + key);
        if (value instanceof Boolean) {
            return (Boolean) value;
        }
        this.setValue(key, defaultValue);
        return defaultValue;
    }


}
