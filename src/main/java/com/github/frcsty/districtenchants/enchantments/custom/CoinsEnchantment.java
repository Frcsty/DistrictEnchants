package com.github.frcsty.districtenchants.enchantments.custom;

import com.github.frcsty.districtenchants.DistrictEnchants;
import com.github.frcsty.districtenchants.enchantments.CustomEnchantment;
import com.github.frcsty.districtenchants.enchantments.CustomEnchantmentTarget;
import com.github.frcsty.districtenchants.util.Enchantments;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;

public class CoinsEnchantment extends CustomEnchantment implements Listener {

    private final DistrictEnchants plugin;
    private final int id;

    public CoinsEnchantment(final DistrictEnchants plugin, final int id) {
        super(id);

        this.plugin = plugin;
        this.id = id;
    }

    @EventHandler(ignoreCancelled = true)
    public void onEntityKill(EntityDeathEvent event) {
        final Player player = event.getEntity().getKiller();
        if (player == null) return;
        final ItemStack item = player.getItemInHand();
        if (event.getEntity() instanceof Player) return;
        if (item == null) return;

        plugin.getTokenManager().addTokens(player, Enchantments.getRandomTokenAmount(item.getEnchantmentLevel(this)));
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public String getName() {
        return "Coins";
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
