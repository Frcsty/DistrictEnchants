package com.github.frcsty.districtenchants.enchantments.custom;

import com.github.frcsty.districtenchants.enchantments.CustomEnchantment;
import com.github.frcsty.districtenchants.enchantments.CustomEnchantmentTarget;
import com.github.frcsty.districtenchants.util.Enchantments;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffectType;

public class HasteEnchantment extends CustomEnchantment implements Listener {

    private final int id;

    public HasteEnchantment(final int id) {
        super(id);

        this.id = id;
    }

    @EventHandler(ignoreCancelled = true)
    public final void onPickaxeEquip(final PlayerItemHeldEvent event) {
        final Player player = event.getPlayer();
        final ItemStack item = player.getInventory().getItem(event.getNewSlot());

        Enchantments.setEnchantEffects(Enchantments.getItemLevelCheck(
                item,
                this
                ),
                player,
                PotionEffectType.FAST_DIGGING);
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public String getName() {
        return "Haste";
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
        return CustomEnchantmentTarget.HOLDABLE;
    }

    @Override
    public int getTier() {
        return 1;
    }

    @Override
    public CustomEnchantment getCustomEnchantment() {
        return this;
    }
}
