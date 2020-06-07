package com.github.frcsty.districtenchants.enchantments.custom;

import com.github.frcsty.districtenchants.enchantments.CustomEnchantment;
import com.github.frcsty.districtenchants.enchantments.CustomEnchantmentTarget;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.inventory.ItemStack;

public class FeastEnchantment extends CustomEnchantment implements Listener {

    private final int id;

    public FeastEnchantment(final int id) {
        super(id);

        this.id = id;
    }

    @EventHandler(ignoreCancelled = true)
    public void onHunger(FoodLevelChangeEvent event) {
        final Player player = (Player) event.getEntity();
        final ItemStack item = player.getInventory().getHelmet();
        if (item == null) return;

        if (item.containsEnchantment(this)) {
            event.setFoodLevel(20);
        }
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public String getName() {
        return "Feast";
    }

    @Override
    public int getMaxLevel() {
        return 1;
    }

    @Override
    public int getStartLevel() {
        return 1;
    }

    @Override
    public EnchantmentTarget getItemTarget() {
        return EnchantmentTarget.ARMOR_HEAD;
    }

    @Override
    public boolean conflictsWith(final Enchantment other) {
        return false;
    }

    @Override
    public boolean canEnchantItem(final ItemStack item) {
        return true;
    }

    @Override
    public CustomEnchantmentTarget getCustomItemTarget() {
        return CustomEnchantmentTarget.ARMOR_HEAD;
    }

    @Override
    public int getTier() {
        return 2;
    }

    @Override
    public CustomEnchantment getCustomEnchantment() {
        return this;
    }
}
