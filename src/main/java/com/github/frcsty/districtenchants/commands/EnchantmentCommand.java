package com.github.frcsty.districtenchants.commands;

import com.github.frcsty.districtenchants.DistrictEnchants;
import com.github.frcsty.districtenchants.enchantments.CustomEnchantment;
import com.github.frcsty.districtenchants.util.*;
import me.mattstudios.mf.annotations.Command;
import me.mattstudios.mf.annotations.Completion;
import me.mattstudios.mf.annotations.Permission;
import me.mattstudios.mf.annotations.SubCommand;
import me.mattstudios.mf.base.CommandBase;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.enchantments.Enchantment;

@SuppressWarnings("unused")
@Command("enchants")
public class EnchantmentCommand extends CommandBase {

    private final FileConfiguration config;

    public EnchantmentCommand(final DistrictEnchants plugin) {
        this.config = plugin.getConfig();
    }

    @SubCommand("give")
    @Permission("enchants.give")
    public void giveEnchantmentCommand(final CommandSender sender, final String target, @Completion("#enchantments") final String enchantName, final Integer level, final Integer amount) {
        final CustomEnchantment enchantment = (CustomEnchantment) Enchantment.getById(Enchantments.getEnchantmentID(enchantName));

        if (enchantment == null) {
            sender.sendMessage(Color.colorize(config.getString("messages.invalid-enchantment")));
            return;
        }

        if (level > enchantment.getMaxLevel()) {
            sender.sendMessage(Color.colorize(config.getString("messages.invalid-enchantment-level")));
            return;
        }

        if (!PlayerCheck.isPlayerValid(target, sender, config)) {
            return;
        }

        Bukkit.getPlayerExact(target).getInventory().addItem(Enchantments.getCustomEnchantmentItem(config, enchantment, level, amount));
    }
}
