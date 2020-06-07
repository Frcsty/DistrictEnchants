package com.github.frcsty.districtenchants.commands.menu;

import com.github.frcsty.districtenchants.DistrictEnchants;
import com.github.frcsty.districtenchants.enchantments.CustomEnchantment;
import com.github.frcsty.districtenchants.util.Color;
import com.github.frcsty.districtenchants.util.Enchantments;
import com.github.frcsty.districtenchants.util.Replace;
import me.mattstudios.mfgui.gui.components.ItemBuilder;
import me.mattstudios.mfgui.gui.guis.Gui;
import me.mattstudios.mfgui.gui.guis.GuiItem;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class EnchanterMenu {

    public static Gui getEnchanterMenu(final DistrictEnchants plugin) {
        final FileConfiguration config = plugin.getConfig();
        final Gui gui = new Gui(plugin, config.getInt("enchanter.rows"), Color.colorize(config.getString("enchanter.title")));
        final ConfigurationSection items = config.getConfigurationSection("enchanter.items");
        final ConfigurationSection background = config.getConfigurationSection("enchanter.background");

        gui.setDefaultClickAction(event -> event.setCancelled(true));

        for (String item : items.getKeys(false)) {
            final ConfigurationSection itemSection = items.getConfigurationSection(item);
            final int levelRequirement = itemSection.getInt("level");
            final String level = "level-" + levelRequirement;

            final GuiItem guiItem = new GuiItem(new ItemBuilder(new ItemStack(itemSection.getInt("material")
                    , 1, (short) itemSection.getInt("data")))
                    .setName(Color.colorize(itemSection.getString("display")))
                    .setLore(Color.colorize(itemSection.getStringList("lore")))
                    .build(), event -> {
                final Player player = (Player) event.getWhoClicked();

                if (player.getLevel() > levelRequirement) {
                    final CustomEnchantment randomEnchantment = Enchantments.getRandomEnchantment(Enchantments.getCustomEnchantments(config.getStringList("enchantments." + level)));
                    player.getInventory().addItem(Enchantments.getCustomEnchantmentItem(config, randomEnchantment, Enchantments.getRandomEnchantmentLevel(randomEnchantment), 1));
                    player.setLevel(player.getLevel() - levelRequirement);
                } else {
                    player.sendMessage(Color.colorize(Replace.replaceString(config.getString("messages.cant-afford-enchantment"), "{level}", String.valueOf((levelRequirement - player.getLevel())))));
                }
            });

            gui.setItem(itemSection.getInt("slot"), guiItem);
        }

        for (String item : background.getKeys(false)) {
            final ConfigurationSection itemSection = config.getConfigurationSection("enchanter.background." + item);

            final GuiItem guiItem = new GuiItem(new ItemBuilder(new ItemStack(itemSection.getInt("material")
                    , 1, (short) itemSection.getInt("data")))
                    .setName(Color.colorize(itemSection.getString("display")))
                    .setLore(Color.colorize(itemSection.getStringList("lore")))
                    .build());

            final List<Integer> slots = itemSection.getIntegerList("slots");
            for (Integer slot : slots) {
                gui.setItem(slot, guiItem);
            }
        }

        return gui;
    }
}
