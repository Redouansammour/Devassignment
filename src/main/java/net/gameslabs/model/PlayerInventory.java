package net.gameslabs.model;

import java.util.EnumMap;
import java.util.Map;

public class PlayerInventory {
    private Map<Item, Integer> items;

    public PlayerInventory() {
        items = new EnumMap(Item.class);
    }

    public void addItem(Item item, int quantity) {
        items.put(item, getItemCount(item) + quantity);
    }

    public boolean removeItem(Item item, int quantity) {
        if (!items.containsKey(item)) {
            return false;
        }
        int currentQuantity = getItemCount(item);
        if (currentQuantity < quantity) {
            return false;
        }
        items.put(item, currentQuantity - quantity);
        return true;
    }

    public int getItemCount(Item item) {
        return items.getOrDefault(item, 0);
    }
}
