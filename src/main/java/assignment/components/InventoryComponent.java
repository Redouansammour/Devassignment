package assignment.components;

import net.gameslabs.api.Component;
import net.gameslabs.api.EventMethod;
import net.gameslabs.api.Player;
import net.gameslabs.events.GetItemEvent;
import net.gameslabs.events.InventoryEvent;
import net.gameslabs.model.AssignmentFailed;
import net.gameslabs.model.Item;
import net.gameslabs.model.PlayerInventory;

import java.util.HashMap;
import java.util.Map;

public class InventoryComponent extends Component {

    // Create a map to store the inventories of players
    private Map<Player, PlayerInventory> inventories;

    // Constructor that initializes the map of inventories
    public InventoryComponent() {
        inventories = new HashMap<>();
    }

    // Register the event to listen for when a player tries to get an item
    @Override
    public void onLoad() {
        registerEvent(GetItemEvent.class, this::onGetItem);
    }

    // When a player tries to get an item, check their inventory and set the count of the item
    private void onGetItem(GetItemEvent event) {
        PlayerInventory inventory = getInventory(event.getPlayer());
        int count = inventory.getItemCount(event.getItem());
        event.setCount(count);
    }

    // Check if a player has a certain item in their inventory and return true or false
    public boolean hasItem(Player player, Item item, int quantity) {
        PlayerInventory inventory = getInventory(player);
        return inventory.getItemCount(item) >= quantity;
    }

    // Add an item to a player's inventory and send an event to update the UI
    public void addItem(Player player, Item item, int quantity) {
        PlayerInventory inventory = getInventory(player);
        inventory.addItem(item, quantity);
        send(new InventoryEvent(player, inventory));
    }

    // Get the inventory of a player from the map, or create a new inventory if the player does not have one
    private PlayerInventory getInventory(Player player) {
        return inventories.computeIfAbsent(player, p -> new PlayerInventory());
    }

    // Unload the component (not used in this case)
    @Override
    public void onUnload() {
        // Do nothing
    }
}
