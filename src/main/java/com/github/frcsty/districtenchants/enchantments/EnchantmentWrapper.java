package com.github.frcsty.districtenchants.enchantments;

import com.github.frcsty.districtenchants.DistrictEnchants;
import com.github.frcsty.districtenchants.enchantments.custom.*;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.Listener;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;

public class EnchantmentWrapper {

    private final DistrictEnchants plugin;

    private final DiveEnchantment dive;
    private final VisionEnchantment vision;
    private final MagmaEnchantment magma;
    private final HasteEnchantment haste;
    private final ObsidianDestroyerEnchantment obsidian;
    private final FeastEnchantment feast;
    private final BounceEnchantment bounce;
    private final AgilityEnchantment agility;
    private final VitalityEnchantment vitality;
    private final DefianceEnchantment defiance;
    private final HealthBoostEnchantment health;
    private final ExperienceEnchantment experience;
    private final MoxieEnchantment moxie;
    private final CoinsEnchantment coins;
    private final ReforgedEnchantment reforged;
    private final ResurgenceEnchantment resurgence;

    public EnchantmentWrapper(final DistrictEnchants plugin) {
        this.plugin = plugin;

        this.dive = new DiveEnchantment(101); // Static ID of 101 - Dive Enchantment
        this.vision = new VisionEnchantment(102); // Static ID of 102 - Vision Enchantment
        this.magma = new MagmaEnchantment(103); // Static ID of 103 - Magma Enchantment
        this.haste = new HasteEnchantment(104); // Static ID of 104 - Haste Enchantment
        this.obsidian = new ObsidianDestroyerEnchantment(105); // Static ID of 105 - Obsidian Destroyer Enchantment
        this.feast = new FeastEnchantment(106); // Static ID of 106 - Feast Enchantment
        this.bounce = new BounceEnchantment(107); // Static ID of 107 - Bounce Enchantment
        this.agility = new AgilityEnchantment(108); // Static ID of 108 - Agility Enchantment
        this.vitality = new VitalityEnchantment(109); // Static ID of 109 - Vitality Enchantment
        this.defiance = new DefianceEnchantment(110); // Static ID of 110 - Defiance Enchantment
        this.health = new HealthBoostEnchantment(111); // Static ID of 111 - Health Boost Enchantment
        this.experience = new ExperienceEnchantment(112); // Static ID of 112 - Experience Enchantment
        this.moxie = new MoxieEnchantment(113); // Static ID of 113 - Moxie Enchantment
        this.coins = new CoinsEnchantment(plugin, 114); // Static ID of 114 - Coins Enchantment
        this.reforged = new ReforgedEnchantment(plugin, 115); // Static ID of 115 - Reforged Enchantment
        this.resurgence = new ResurgenceEnchantment(116); // Static ID of 116 - Resurgence Enchantment
    }

    public void loadEnchantments() {
        try {
            final Field field = Enchantment.class.getDeclaredField("acceptingNew");
            field.setAccessible(true);
            field.set(null, true);
            field.setAccessible(false);

            final List<Enchantment> enchantments = Arrays.asList(dive, vision, magma, haste
                    , obsidian, feast, bounce, agility, vitality, defiance, health, experience
                    , moxie, coins, reforged, resurgence);

            final List<Listener> listeners = Arrays.asList(dive, vision, magma, haste
                    , obsidian, feast, bounce, agility, vitality, defiance, health, experience
                    , moxie, coins, reforged, resurgence);

            for (Enchantment enchantment : enchantments) {
                Enchantment.registerEnchantment(enchantment);
            }
            for (Listener listener : listeners) {
                plugin.getServer().getPluginManager().registerEvents(listener, plugin);
            }
        } catch (IllegalAccessException | NoSuchFieldException ex) {
            plugin.getLogger().log(Level.WARNING, "Internal error occurred while enabling enchantments", ex);
        }
    }
}
