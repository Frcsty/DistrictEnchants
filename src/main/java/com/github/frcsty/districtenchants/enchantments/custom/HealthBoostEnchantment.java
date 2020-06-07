package com.github.frcsty.districtenchants.enchantments.custom;

import com.github.frcsty.districtenchants.enchantments.CustomEnchantment;
import com.github.frcsty.districtenchants.enchantments.CustomEnchantmentTarget;
import com.github.frcsty.districtenchants.listener.events.armor.ArmorEquipEvent;
import com.github.frcsty.districtenchants.listener.events.armor.ArmorType;
import com.github.frcsty.districtenchants.util.Enchantments;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;

public class HealthBoostEnchantment extends CustomEnchantment implements Listener {

    private final int id;

    public HealthBoostEnchantment(final int id) {
        super(id);

        this.id = id;
    }

    @EventHandler(ignoreCancelled = true)
    public void onArmorEquip(final ArmorEquipEvent event) {
        if (event.getType() != ArmorType.CHESTPLATE) return;

        Enchantments.setHealth(Enchantments.getArmorCheck(
                event.getOldArmorPiece(),
                event.getNewArmorPiece(),
                this),
                event.getPlayer()
        );
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public String getName() {
        return "Health Boost";
    }

    @Override
    public int getMaxLevel() {
        return 2;
    }

    @Override
    public int getStartLevel() {
        return 1;
    }

    @Override
    public EnchantmentTarget getItemTarget() {
        return EnchantmentTarget.ARMOR_TORSO;
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
        return CustomEnchantmentTarget.ARMOR_TORSO;
    }

    @Override
    public int getTier() {
        return 3;
    }

    @Override
    public CustomEnchantment getCustomEnchantment() {
        return this;
    }
}
