package com.github.frcsty.districtenchants.enchantments.custom;

import com.github.frcsty.districtenchants.DistrictEnchants;
import com.github.frcsty.districtenchants.enchantments.CustomEnchantment;
import com.github.frcsty.districtenchants.enchantments.CustomEnchantmentTarget;
import com.github.frcsty.districtenchants.util.Enchantments;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemDamageEvent;
import org.bukkit.inventory.ItemStack;

public class ReforgedEnchantment extends CustomEnchantment implements Listener {

    private final FileConfiguration config;
    private final int id;

    public ReforgedEnchantment(final DistrictEnchants plugin, final int id) {
        super(id);

        this.config = plugin.getConfig();
        this.id = id;
    }

    @EventHandler(ignoreCancelled = true)
    public void onDurabilityLoss(PlayerItemDamageEvent event) {
        final ItemStack item = event.getItem();
        if (item == null) return;

        if (item.containsEnchantment(this)) {
            final int chance = Enchantments.getRandom().nextInt(config.getInt("reforged-chances.bound"));
            final int level = item.getEnchantmentLevel(this);

            if (level == 1) {
                if (chance < config.getInt("reforged-chances.level-1")) event.setCancelled(true);
            } else {
                if (chance < config.getInt("reforged-chances.level-2")) event.setCancelled(true);
            }
        }
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public String getName() {
        return "Reforged";
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
        return EnchantmentTarget.ALL;
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
        return CustomEnchantmentTarget.ALL;
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
