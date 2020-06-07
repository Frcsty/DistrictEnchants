package com.github.frcsty.districtenchants.listener.events;

import com.github.frcsty.districtenchants.DistrictEnchants;
import com.github.frcsty.districtenchants.listener.events.anvil.AnvilUseListener;
import com.github.frcsty.districtenchants.listener.events.armor.ArmorListener;

public class CustomEventRegistration {

    private final DistrictEnchants plugin;

    public CustomEventRegistration(final DistrictEnchants plugin) {
        this.plugin = plugin;
    }

    public final void register() {
        plugin.getServer().getPluginManager().registerEvents(new ArmorListener(), plugin);
        plugin.getServer().getPluginManager().registerEvents(new AnvilUseListener(), plugin);
    }
}
