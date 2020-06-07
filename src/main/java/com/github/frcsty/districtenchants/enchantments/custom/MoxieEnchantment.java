package com.github.frcsty.districtenchants.enchantments.custom;

import com.github.frcsty.districtenchants.enchantments.CustomEnchantment;
import com.github.frcsty.districtenchants.enchantments.CustomEnchantmentTarget;
import com.github.frcsty.districtenchants.util.Enchantments;
import com.gmail.nossr50.events.experience.McMMOPlayerXpGainEvent;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;

public class MoxieEnchantment extends CustomEnchantment implements Listener {

    private final int id;

    public MoxieEnchantment(final int id) {
        super(id);

        this.id = id;
    }

    @EventHandler(ignoreCancelled = true)
    public void onEntityKill(McMMOPlayerXpGainEvent event) {
        final Player player = event.getPlayer();
        if (player.getItemInHand() == null) return;

        if (player.getItemInHand().containsEnchantment(this)) {
            final int chance = Enchantments.getRandom().nextInt(1000);
            if (chance < 450) {
                final int level = player.getItemInHand().getEnchantmentLevel(this);
                event.setRawXpGained(event.getRawXpGained() * percentage(level));
            }
        }
    }

    private float percentage(final int level) {
        switch (level) {
            case 1:
                return 1.15f;
            case 2:
                return 1.25f;
            case 3:
                return 1.5f;
            default:
                return 1f;
        }
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public String getName() {
        return "Moxie";
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
        return CustomEnchantmentTarget.WEAPONS;
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
