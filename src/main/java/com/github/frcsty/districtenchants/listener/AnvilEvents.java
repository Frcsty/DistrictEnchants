package com.github.frcsty.districtenchants.listener;

import com.github.frcsty.districtenchants.listener.events.anvil.AnvilUseEvent;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;

import java.util.Map;

public class AnvilEvents implements Listener {

    @EventHandler
    public void onAnvilUse(final AnvilUseEvent event) {
        final ItemStack result = event.getResult();
        final ItemStack inputOne = event.getInputOne();
        if (!event.isValid()) return;

        final Map<Enchantment, Integer> enchantments = inputOne.getEnchantments();
        for (Enchantment enchantment : enchantments.keySet()) {
            result.addEnchantment(enchantment, enchantments.get(enchantment));
        }
    }
}
