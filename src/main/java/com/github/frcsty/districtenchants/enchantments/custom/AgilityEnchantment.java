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
import org.bukkit.potion.PotionEffectType;

public class AgilityEnchantment extends CustomEnchantment implements Listener {

    private final int id;

    public AgilityEnchantment(final int id) {
        super(id);

        this.id = id;
    }

    @EventHandler(ignoreCancelled = true)
    public final void onArmorEquip(final ArmorEquipEvent event) {
        if (event.getType() != ArmorType.BOOTS) return;

        Enchantments.setEnchantEffects(Enchantments.getArmorCheck(
                event.getOldArmorPiece(),
                event.getNewArmorPiece(),
                this
                ),
                event.getPlayer(),
                PotionEffectType.SPEED);
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public String getName() {
        return "Agility";
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
        return EnchantmentTarget.ARMOR_FEET;
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
        return CustomEnchantmentTarget.ARMOR_FEET;
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
