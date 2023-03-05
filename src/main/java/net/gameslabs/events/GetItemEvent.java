package net.gameslabs.events;

import net.gameslabs.api.Player;
import net.gameslabs.api.PlayerEvent;
import net.gameslabs.model.Item;

public class GetItemEvent extends PlayerEvent {
    private final Item item;
    private int count;

    public GetItemEvent(Player player, Item item) {
        super(player);
        this.item = item;
    }

    public Item getItem() {
        return item;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
