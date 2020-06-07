package com.github.frcsty.districtenchants.enchantments.custom;

import com.github.frcsty.districtenchants.enchantments.CustomEnchantment;
import com.github.frcsty.districtenchants.enchantments.CustomEnchantmentTarget;
import com.github.frcsty.districtenchants.util.Enchantments;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.inventory.ItemStack;

public class ResurgenceEnchantment extends CustomEnchantment implements Listener {

    private final int id;

    public ResurgenceEnchantment(final int id) {
        super(id);

        this.id = id;
    }

    @EventHandler(ignoreCancelled = true)
    public void onPlayerDamage(EntityDamageEvent event) {
        if (!(event.getEntity() instanceof Player)) return;
        final Player player = (Player) event.getEntity();

        final int level = Enchantments.getArmorCheckLevel(player, this);
        if (level == 0) return;

        final double health = player.getHealth();
        if (health > 0 && health < 5) {
            final int chance = Enchantments.getRandom().nextInt(1000);
            if (level == 1) {
                if (chance < 100) player.setHealth(20);
            } else if (level == 2) {
                if (chance < 250) player.setHealth(20);
            } else {
                if (chance < 350) player.setHealth(20);
            }
        }
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public String getName() {
        return "Resurgence";
    }

    @Override
    public int getMaxLevel() {
        return 3;
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
        return CustomEnchantmentTarget.ARMOR;
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
