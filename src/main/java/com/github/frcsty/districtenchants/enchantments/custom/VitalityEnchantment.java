package com.github.frcsty.districtenchants.enchantments.custom;

import com.github.frcsty.districtenchants.enchantments.CustomEnchantment;
import com.github.frcsty.districtenchants.enchantments.CustomEnchantmentTarget;
import com.github.frcsty.districtenchants.listener.events.armor.ArmorEquipEvent;
import com.github.frcsty.districtenchants.util.Enchantments;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffectType;

public class VitalityEnchantment extends CustomEnchantment implements Listener {

    private final int id;

    public VitalityEnchantment(final int id) {
        super(id);

        this.id = id;
    }

    @EventHandler(ignoreCancelled = true)
    public void onArmorEquip(final ArmorEquipEvent event) {
        final Player player = event.getPlayer();
        if (Enchantments.getArmorCheck(player.getInventory(), this) < 3) {
            return;
        }

        Enchantments.setEnchantEffects(Enchantments.getArmorCheck(
                event.getOldArmorPiece(),
                event.getNewArmorPiece(),
                this),
                player,
                PotionEffectType.INCREASE_DAMAGE
        );
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public String getName() {
        return "Vitality";
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
