package com.github.frcsty.districtenchants.listener;

import com.github.frcsty.districtenchants.DistrictEnchants;
import com.github.frcsty.districtenchants.enchantments.CustomEnchantment;
import com.github.frcsty.districtenchants.util.Color;
import com.github.frcsty.districtenchants.util.Enchantments;
import com.github.frcsty.districtenchants.util.Replace;
import me.mattstudios.mfgui.gui.components.ItemNBT;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class EnchantmentApplyListener implements Listener {

    private final DistrictEnchants plugin;
    private List<Integer> customEnchantmentsIDs = Arrays.asList(101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116);

    public EnchantmentApplyListener(final DistrictEnchants plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onEnchantmentApply(InventoryClickEvent event) {
        final Player player = (Player) event.getWhoClicked();
        final ItemStack startingItem = event.getCursor();
        final ItemStack finishingItem = event.getCurrentItem();

        if (startingItem == null || startingItem.getType() == Material.AIR) {
            return;
        }
        if (finishingItem == null || finishingItem.getType() == Material.AIR) {
            return;
        }

        final String enchantmentIdString = ItemNBT.getNBTTag(startingItem, "enchantment");
        final String finishingEnchantmentIdString = ItemNBT.getNBTTag(finishingItem, "enchantment");
        if (enchantmentIdString == null || enchantmentIdString.length() == 0) {
            return;
        }
        if (finishingEnchantmentIdString.length() != 0) {
            return;
        }

        final int enchantmentId = Integer.valueOf(enchantmentIdString);
        if (enchantmentId == 0) {
            return;
        }

        Enchantment temp = CustomEnchantment.getById(enchantmentId);

        if (temp == null) {
            return;
        }

        if (!customEnchantmentsIDs.contains(temp.getId())) {
            return;
        }

        final CustomEnchantment enchantment = (CustomEnchantment) temp;
        final int level = Integer.valueOf(ItemNBT.getNBTTag(startingItem, "level"));

        if (!enchantment.getCustomItemTarget().includes(finishingItem)) {
            player.sendMessage(Color.colorize(plugin.getConfig().getString("messages.incompatible-enchantment")));
            return;
        }

        final Map<Enchantment, Integer> targetEnchantmentMap = finishingItem.getEnchantments();
        if (targetEnchantmentMap.isEmpty()) {
            finishingItem.addEnchantment(enchantment, level);
        } else {
            boolean hasEnchantment = false;
            boolean upgrade = false;
            for (Enchantment enchant : targetEnchantmentMap.keySet()) {
                if (enchant.equals(enchantment)) {
                    if (targetEnchantmentMap.get(enchant) > level || level == enchantment.getMaxLevel()) {
                        return;
                    }
                    if (targetEnchantmentMap.get(enchant) == level && enchantment.getMaxLevel() > level) {
                        upgrade = true;
                    }
                    hasEnchantment = true;
                }
            }

            if (hasEnchantment) {
                finishingItem.removeEnchantment(enchantment);
                if (upgrade) {
                    final int cost = Enchantments.getTierLevelCost(plugin.getConfig(), enchantment.getTier());
                    if (player.getLevel() < cost) {
                        return;
                    } else {
                        finishingItem.addEnchantment(enchantment, level + 1);
                        player.setLevel(player.getLevel() - cost);
                    }
                } else {
                    finishingItem.addEnchantment(enchantment, level);
                }
            } else {
                finishingItem.addEnchantment(enchantment, level);
            }
        }

        final Map<Enchantment, Integer> newTargetEnchantmentMap = finishingItem.getEnchantments();
        final List<CustomEnchantment> enchantments = new ArrayList<>();
        for (Enchantment enchant : newTargetEnchantmentMap.keySet()) {
            if (customEnchantmentsIDs.contains(enchant.getId())) {
                enchantments.add((CustomEnchantment) enchant);
            }
        }

        setItemMeta(finishingItem, enchantments);
        if (startingItem.getAmount() > 1) {
            startingItem.setAmount(startingItem.getAmount() - 1);
        } else {
            event.setCursor(null);
        }
    }

    private void setItemMeta(final ItemStack item, final List<CustomEnchantment> customEnchants) {
        final ItemMeta meta = item.getItemMeta();

        final List<String> lore = new ArrayList<>();
        final List<String> customEnchantStrings = new ArrayList<>();
        final String format = plugin.getConfig().getString("enchantment-format");

        for (CustomEnchantment enchantment : Enchantments.getSortedEnchantment(customEnchants)) {
            final String enchantmentLine = Color.colorize(Replace.replaceString(format
                    , "{enchantment}", enchantment.getName()
                    , "{level}", Enchantments.getRomanNumeral(item.getEnchantmentLevel(enchantment))
                    , "{tier-color}", Enchantments.getTierColor(enchantment.getTier())
                    , "{tier-name}", Enchantments.getFormattedTierName(enchantment.getTier())
                    , "{tier}", Enchantments.getRomanNumeral(enchantment.getTier())));
            lore.add(enchantmentLine);
            customEnchantStrings.add(enchantmentLine);
        }

        if (meta.getLore() != null && !meta.getLore().isEmpty()) {
            final List<String> oldLore = meta.getLore();
            for (String enchantString : customEnchantStrings) {
                oldLore.removeIf(line -> line.contains(enchantString.split(" ")[0]));
            }
            lore.addAll(oldLore);
        }

        meta.setLore(lore);
        item.setItemMeta(meta);
    }
}

