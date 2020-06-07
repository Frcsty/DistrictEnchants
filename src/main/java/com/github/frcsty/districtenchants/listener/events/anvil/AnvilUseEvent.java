package com.github.frcsty.districtenchants.listener.events.anvil;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.inventory.ItemStack;

@SuppressWarnings("unused")
public class AnvilUseEvent extends Event implements Cancellable {

    private static final HandlerList handlers = new HandlerList();
    private final Player player;
    private final ItemStack currentItem;
    private final ItemStack result;
    private final ItemStack inputOne;
    private final ItemStack inputTwo;
    private final boolean valid;

    private boolean cancel = false;

    AnvilUseEvent(final Player player, final ItemStack currentItem, final ItemStack result, final ItemStack inputOne, final ItemStack inputTwo, final boolean valid) {
        this.player = player;
        this.currentItem = currentItem;
        this.result = result;
        this.inputOne = inputOne;
        this.inputTwo = inputTwo;
        this.valid = valid;
    }

    private static HandlerList getHandlerList() {
        return handlers;
    }

    @Override
    public final HandlerList getHandlers() {
        return handlers;
    }

    public final boolean isCancelled() {
        return cancel;
    }

    public final void setCancelled(final boolean cancel) {
        this.cancel = cancel;
    }

    public final Player getPlayer() {
        return player;
    }

    public final ItemStack getCurrentItem() {
        return currentItem;
    }

    public final ItemStack getResult() {
        return result;
    }

    public final ItemStack getInputOne() {
        return inputOne;
    }

    public final ItemStack getInputTwo() {
        return inputTwo;
    }

    public final boolean isValid() {
        return valid;
    }
}
