package com.github.frcsty.districtenchants.commands;

import com.github.frcsty.districtenchants.DistrictEnchants;
import com.github.frcsty.districtenchants.commands.menu.EnchanterMenu;
import me.mattstudios.mf.annotations.Command;
import me.mattstudios.mf.annotations.Default;
import me.mattstudios.mf.annotations.Permission;
import me.mattstudios.mf.base.CommandBase;
import me.mattstudios.mfgui.gui.guis.Gui;
import org.bukkit.entity.Player;

@SuppressWarnings("unused")
@Command("enchanter")
public class EnchanterCommand extends CommandBase {

    private final DistrictEnchants plugin;

    public EnchanterCommand(final DistrictEnchants plugin) {
        this.plugin = plugin;
    }

    @Default
    @Permission("enchants.enchanter")
    public void enchanterCommand(final Player player) {
        final Gui menu = EnchanterMenu.getEnchanterMenu(plugin);

        if (menu == null) {
            return;
        }

        menu.open(player);
    }

}
