package com.github.frcsty.districtenchants;

import com.github.frcsty.districtenchants.commands.EnchanterCommand;
import com.github.frcsty.districtenchants.commands.EnchantmentCommand;
import com.github.frcsty.districtenchants.enchantments.EnchantmentWrapper;
import com.github.frcsty.districtenchants.listener.AnvilEvents;
import com.github.frcsty.districtenchants.listener.EnchantmentApplyListener;
import com.github.frcsty.districtenchants.listener.events.CustomEventRegistration;
import me.mattstudios.mf.base.CommandManager;
import me.realized.tokenmanager.api.TokenManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;
import java.util.logging.Level;

public final class DistrictEnchants extends JavaPlugin {

    private final EnchantmentWrapper enchantmentWrapper = new EnchantmentWrapper(this);
    private TokenManager tokenManager;

    @Override
    public void onEnable() {
        saveDefaultConfig();

        final Plugin tokenManagerPlugin = Bukkit.getServer().getPluginManager().getPlugin("TokenManager");
        if (tokenManagerPlugin != null && tokenManagerPlugin.isEnabled()) {
            tokenManager = (TokenManager) tokenManagerPlugin;
        } else {
            getLogger().log(Level.WARNING, "Failed to initialize TokenManager API! Disabling plugin.");
            getServer().getPluginManager().disablePlugin(this);
            return;
        }

        final CommandManager commandManager = new CommandManager(this);
        commandManager.getCompletionHandler().register("#enchantments"
                , (input) -> Arrays.asList("agility", "bounce", "coins", "defiance", "dive", "experience"
                        , "feast", "haste", "health", "magma", "moxie", "obsidian", "reforged", "resurgence"
                        , "vision", "vitality"))
        ;
        commandManager.register(new EnchanterCommand(this), new EnchantmentCommand(this));


        enchantmentWrapper.loadEnchantments();
        getServer().getPluginManager().registerEvents(new EnchantmentApplyListener(this), this);
        getServer().getPluginManager().registerEvents(new AnvilEvents(), this);

        final CustomEventRegistration customEventRegistration = new CustomEventRegistration(this);

        customEventRegistration.register();
    }

    @Override
    public void onDisable() {
        reloadConfig();
    }

    public TokenManager getTokenManager() { return tokenManager; }

}
