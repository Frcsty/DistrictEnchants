package com.github.frcsty.districtenchants.enchantments;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.inventory.ItemStack;

public abstract class CustomEnchantment extends Enchantment {

    public CustomEnchantment(int id) {
        super(id);
    }

    @Override
    public int getId() {
        return super.getId();
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public int getMaxLevel() {
        return 0;
    }

    @Override
    public int getStartLevel() {
        return 0;
    }

    @Override
    public EnchantmentTarget getItemTarget() {
        return null;
    }

    @Override
    public boolean conflictsWith(Enchantment other) {
        return false;
    }

    @Override
    public boolean canEnchantItem(ItemStack item) {
        return true;
    }

    public abstract CustomEnchantmentTarget getCustomItemTarget();

    public abstract int getTier();

    public abstract CustomEnchantment getCustomEnchantment();
}
