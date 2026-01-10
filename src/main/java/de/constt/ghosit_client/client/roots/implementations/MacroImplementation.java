package de.constt.ghosit_client.client.roots.implementations;

public abstract class MacroImplementation implements Runnable {

    protected volatile boolean running;

    public void start() {
        if (running) return;
        running = true;
        Thread thread = new Thread(this);
        thread.start();
    }

    public void stop() {
        running = false;
    }

    @Override
    public final void run() {
        execute();
        running = false;
    }

    protected abstract void execute();
}
