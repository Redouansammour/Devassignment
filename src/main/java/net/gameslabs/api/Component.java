package net.gameslabs.api;

import java.util.*;

/**
 * An abstract component that can listen and register events
 */
public abstract class Component {

    private Map<Class, List<EventMethod>> registeredEvents;
    private ComponentRegistry registry;

    public Component() {
        registeredEvents = new HashMap<>();
    }

    public final <T extends Event> void registerEvent(Class<T> eventType, EventMethod<T> method) {
        registeredEvents.computeIfAbsent(eventType, e -> new ArrayList<>()).add(method);
    }

    public final List<EventMethod> getEvents(Event event) {
        return registeredEvents.getOrDefault(event.getClass(), Collections.emptyList());
    }

    /**
     * Send an event
     * @param event
     */
    protected final void send(Event event) {
        registry.sendEvent(event);
    }

    public final void initialize(ComponentRegistry registry) {
        this.registry = registry;
    }

    /**
     * Called when the component loads
     */
    public abstract void onLoad();

    /**
     * Called when the component unloads
     */
    public abstract void onUnload();
}
