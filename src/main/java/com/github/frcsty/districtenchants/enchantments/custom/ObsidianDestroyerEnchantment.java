package com.github.frcsty.districtenchants.enchantments.custom;

import com.github.frcsty.districtenchants.enchantments.CustomEnchantment;
import com.github.frcsty.districtenchants.enchantments.CustomEnchantmentTarget;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockDamageEvent;
import org.bukkit.inventory.ItemStack;

public class ObsidianDestroyerEnchantment extends CustomEnchantment implements Listener {

    private final int id;

    public ObsidianDestroyerEnchantment(final int id) {
        super(id);

        this.id = id;
    }

    @EventHandler
    public void onBlockDamage(BlockDamageEvent event) {
        final ItemStack item = event.getItemInHand();
        final Block block = event.getBlock();
        if (item == null) return;
        if (event.isCancelled()) return;

        if (item.containsEnchantment(this)) {
            if (block.getType() == Material.OBSIDIAN) {
                block.breakNaturally();
                item.setDurability((short) (item.getDurability() + 1));
            }
        }
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public String getName() {
        return "Obsidian Destroyer";
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
        return EnchantmentTarget.TOOL;
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
        return CustomEnchantmentTarget.PICKAXES;
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
