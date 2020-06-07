package com.github.frcsty.districtenchants.listener.events.anvil;

import org.bukkit.Bukkit;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.AnvilInventory;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;

public class AnvilUseListener implements Listener {

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onInventoryClick(InventoryClickEvent event) {
        final HumanEntity entity = event.getWhoClicked();
        if (!(entity instanceof Player)) return;

        final Player player = (Player) entity;
        final Inventory inventory = event.getInventory();

        if (!(inventory instanceof AnvilInventory)) return;
        final InventoryView view = event.getView();
        final int rawSlot = event.getRawSlot();

        if (rawSlot != view.convertSlot(rawSlot)) return;

        final ItemStack leftItem = view.getItem(0);
        final ItemStack rightItem = view.getItem(1);
        final ItemStack resultItem = view.getItem(2);
        final ItemStack currentItem = event.getCurrentItem();
        boolean validResult = resultItem != null;

        Bukkit.getServer().getPluginManager().callEvent(new AnvilUseEvent(player, currentItem, resultItem, leftItem, rightItem, validResult));
    }
}
