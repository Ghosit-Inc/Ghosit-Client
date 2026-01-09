package de.constt.ghosit_client.client.roots.modules;

public abstract class Setting<T> {
    private final String name;
    private T value;

    protected Setting(String name, T defaultValue) {
        this.name = name.toUpperCase();
        this.value = defaultValue;
    }

    public String getName() {
        return name;
    }

    public T get() {
        return value;
    }

    public void set(T value) {
        this.value = value;
    }

    public abstract T parse(String input);

    public final void setFromString(String input) {
        set(parse(input));
    }

}
