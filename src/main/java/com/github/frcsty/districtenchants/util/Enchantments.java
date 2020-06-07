package com.github.frcsty.districtenchants.util;

import com.github.frcsty.districtenchants.enchantments.CustomEnchantment;
import com.github.frcsty.districtenchants.enchantments.CustomEnchantmentTarget;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.*;
import java.util.stream.Collectors;

public class Enchantments {

    private static final SplittableRandom random = new SplittableRandom();

    public static SplittableRandom getRandom() {
        return random;
    }

    public static String getArmorCheck(final ItemStack oldArmor, final ItemStack newArmor, final CustomEnchantment enchantment) {
        String armor = "";
        int level = 1;

        if (oldArmor == null && newArmor != null && newArmor.containsEnchantment(enchantment)) {
            level = newArmor.getEnchantmentLevel(enchantment);
            armor = "new";
        } else if (newArmor == null && oldArmor != null && oldArmor.containsEnchantment(enchantment)) {
            level = oldArmor.getEnchantmentLevel(enchantment);
            armor = "old";
        } else if (newArmor != null && newArmor.containsEnchantment(enchantment)) {
            armor = "new";
        } else if (oldArmor != null && oldArmor.containsEnchantment(enchantment)) {
            armor = "old";
        }

        return armor + "-" + level;
    }

    public static int getArmorCheck(final PlayerInventory inventory, final CustomEnchantment enchantment) {
        boolean helmet = inventory.getHelmet() != null && inventory.getHelmet().containsEnchantment(enchantment);
        boolean chestplate = inventory.getChestplate() != null && inventory.getChestplate().containsEnchantment(enchantment);
        boolean leggings = inventory.getLeggings() != null && inventory.getLeggings().containsEnchantment(enchantment);
        boolean boots = inventory.getBoots() != null && inventory.getBoots().containsEnchantment(enchantment);

        int amount = 0;
        if (helmet) amount += 1;
        if (chestplate) amount += 1;
        if (leggings) amount += 1;
        if (boots) amount += 1;
        return amount;
    }

    public static int getArmorCheckLevel(final Player player, final CustomEnchantment enchantment) {
        final PlayerInventory inventory = player.getInventory();
        final ItemStack helmet = inventory.getHelmet();
        final ItemStack chestplate = inventory.getChestplate();
        final ItemStack leggings = inventory.getLeggings();
        final ItemStack boots = inventory.getBoots();

        final List<ItemStack> items = Arrays.asList(helmet, chestplate, leggings, boots);
        final Map<ItemStack, Integer> check = new HashMap<>();
        for (ItemStack item : items) {
            if (item != null && item.containsEnchantment(enchantment)) {
                check.put(item, item.getEnchantmentLevel(enchantment));
            }
        }

        int level = 0;
        for (ItemStack i : check.keySet()) {
            if (check.get(i) > level) {
                level = check.get(i);
            }
        }

        return level;
    }

    public static String getItemLevelCheck(final ItemStack item, final CustomEnchantment enchantment) {
        String check = "old";
        int level = 1;

        if (item != null && item.containsEnchantment(enchantment)) {
            check = "new";
            level = item.getEnchantmentLevel(enchantment);
        }

        return check + "-" + level;
    }

    public static void setEnchantEffects(final String value, final Player player, final PotionEffectType type) {
        final String[] input = value.split("-");
        PotionEffect potionEffect = new PotionEffect(type, Integer.MAX_VALUE, Integer.valueOf(input[1]) - 1, true, true);

        if (input[0].equalsIgnoreCase("new")) {
            player.addPotionEffect(potionEffect, true);
        } else {
            player.removePotionEffect(type);
        }
    }

    public static void setHealth(final String value, final Player player) {
        final String[] input = value.split("-");

        final double health = player.getMaxHealth();
        final int level = Integer.valueOf(input[1]);
        if (input[0].equalsIgnoreCase("new")) {
            player.setMaxHealth((health + level + level));
        } else {
            player.setMaxHealth(20);
        }
    }

    public static int getEnchantmentID(final String enchantment) {
        switch (enchantment) {
            case "dive":
                return 101;
            case "vision":
                return 102;
            case "magma":
                return 103;
            case "haste":
                return 104;
            case "obsidian":
                return 105;
            case "feast":
                return 106;
            case "bounce":
                return 107;
            case "agility":
                return 108;
            case "vitality":
                return 109;
            case "defiance":
                return 110;
            case "health":
                return 111;
            case "experience":
                return 112;
            case "moxie":
                return 113;
            case "coins":
                return 114;
            case "reforged":
                return 115;
            case "resurgence":
                return 116;
            default:
                return 999;
        }
    }

    public static int getRandomTokenAmount(final int level) {
        int amount = random.nextInt(1, 4);
        if (level == 1) amount = random.nextInt(1, 5);
        if (level == 2) amount = random.nextInt(1, 6);
        if (level == 3) amount = random.nextInt(1, 8);

        return amount;
    }

    @SuppressWarnings("deprecation")
    public static List<CustomEnchantment> getCustomEnchantments(final List<String> ids) {
        final List<CustomEnchantment> enchantments = new ArrayList<>();

        for (String name : ids) {
            enchantments.add((CustomEnchantment) Enchantment.getById(getEnchantmentID(name)));
        }

        return enchantments;
    }

    public static CustomEnchantment getRandomEnchantment(final List<CustomEnchantment> customEnchantments) {
        return customEnchantments.get(random.nextInt(customEnchantments.size()));
    }

    public static int getTierLevelCost(final FileConfiguration config, final int tier) {
        return config.getInt("combine-costs.tier-" + tier);
    }

    public static int getRandomEnchantmentLevel(final CustomEnchantment enchantment) {
        final int chance = random.nextInt(1000);

        if (chance < 100) {
            return enchantment.getMaxLevel();
        } else {
            final int level = random.nextInt(enchantment.getMaxLevel());
            return (level > enchantment.getStartLevel() ? (level == enchantment.getMaxLevel() ? level - 1 : level) : level) == 0 ? level + 1 : level;
        }
    }

    public static ItemStack getCustomEnchantmentItem(final FileConfiguration config, final CustomEnchantment enchantment, final int level, final int amount) {
        final EnchantmentItemBuilder item = new EnchantmentItemBuilder(config.getInt("enchantment-item.material"), (short) config.getInt("enchantment-item.data"), amount);

        item.setDisplay(Replace.replaceString(Color.colorize(config.getString("enchantment-item.display"))
                , "{enchantment}", enchantment.getName()
                , "{level}", Enchantments.getRomanNumeral(level)
                , "{applicable}", Enchantments.getFormattedTarget(enchantment.getCustomItemTarget())));
        item.setLore(Replace.replaceList(Color.colorize(config.getStringList("enchantment-item.lore"))
                , "{enchantment}", enchantment.getName()
                , "{level}", Enchantments.getRomanNumeral(level)
                , "{applicable}", Enchantments.getFormattedTarget(enchantment.getCustomItemTarget())));
        item.setNbt("enchantment", String.valueOf(enchantment.getId()), "level", String.valueOf(level));
        item.setEnchantment(enchantment, level);

        return item.getItem();
    }

    public static String getRomanNumeral(final int number) {
        switch (number) {
            case 1:
                return "I";
            case 2:
                return "II";
            case 3:
                return "III";
            case 4:
                return "IV";
            case 5:
                return "V";
            default:
                return "Invalid Number";
        }
    }

    private static String getFormattedTarget(final CustomEnchantmentTarget target) {
        switch (target) {
            case ALL:
                return "All Items";
            case BOW:
                return "Bows";
            case TOOL:
                return "Tools";
            case ARMOR:
                return "Armor";
            case WEAPON:
                return "Weapons";
            case ARMOR_FEET:
                return "Boots";
            case ARMOR_HEAD:
                return "Helmets";
            case ARMOR_LEGS:
                return "Leggings";
            case ARMOR_TORSO:
                return "Chestplates";
            case FISHING_ROD:
                return "Fishing Rod";
            case AXES:
                return "Axes";
            case WEAPONS:
                return "Swords & Axes";
            case HOLDABLE:
                return "Tools";
            case PICKAXES:
                return "Pickaxes";
            default:
                return "Invalid Target";
        }
    }

    public static String getFormattedTierName(final int id) {
        switch (id) {
            case 1:
                return "Basic";
            case 2:
                return "Advanced";
            case 3:
                return "Divine";
            default:
                return "Invalid Tier";
        }
    }

    public static String getTierColor(final int id) {
        switch (id) {
            case 1:
                return "&b";
            case 2:
                return "&a";
            case 3:
                return "&c";
            default:
                return "";
        }
    }

    public static List<CustomEnchantment> getSortedEnchantment(final List<CustomEnchantment> enchantments) {
        Map<CustomEnchantment, Integer> map = new HashMap<>();

        for (CustomEnchantment enchantment : enchantments) {
            map.put(enchantment, enchantment.getTier());
        }

        map = map.entrySet().stream()
                .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e2, LinkedHashMap::new));

        final List<CustomEnchantment> result = new ArrayList<>();
        map.forEach((key, value) -> result.add(key));

        return result;
    }

}
