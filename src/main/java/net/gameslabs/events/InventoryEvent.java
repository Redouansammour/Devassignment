package net.gameslabs.events;

import net.gameslabs.api.Event;
import net.gameslabs.api.Player;
import net.gameslabs.model.PlayerInventory;

public class InventoryEvent extends Event {
    private final Player player;
    private final PlayerInventory inventory;

    public InventoryEvent(Player player, PlayerInventory inventory) {
        this.player = player;
        this.inventory = inventory;
    }

    public Player getPlayer() {
        return player;
    }

    public PlayerInventory getInventory() {
        return inventory;
    }
}
