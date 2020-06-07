package com.github.frcsty.districtenchants.enchantments.custom;

import com.github.frcsty.districtenchants.enchantments.CustomEnchantment;
import com.github.frcsty.districtenchants.enchantments.CustomEnchantmentTarget;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;

public class ExperienceEnchantment extends CustomEnchantment implements Listener {

    private final int id;

    public ExperienceEnchantment(final int id) {
        super(id);

        this.id = id;
    }

    @EventHandler(ignoreCancelled = true)
    public void onEntityKill(EntityDeathEvent event) {
        if (event.getEntity().getKiller() == null) return;
        final Player player = event.getEntity().getKiller();
        final ItemStack item = player.getItemInHand();

        if (event.getEntity() instanceof Player) return;
        if (item == null) return;

        if (item.containsEnchantment(this)) {
            final int level = item.getEnchantmentLevel(this);
            double multi = 1;
            switch (level) {
                case 1:
                    multi = 1.25;
                    break;
                case 2:
                    multi = 1.5;
                    break;
                case 3:
                    multi = 1.75;
                    break;
            }
            event.setDroppedExp((int) (event.getDroppedExp() * multi));
        }
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public String getName() {
        return "Experience";
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
        return EnchantmentTarget.WEAPON;
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
        return CustomEnchantmentTarget.WEAPON;
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
