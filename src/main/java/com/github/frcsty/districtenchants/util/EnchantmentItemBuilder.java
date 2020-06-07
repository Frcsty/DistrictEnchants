package com.github.frcsty.districtenchants.util;

import me.mattstudios.mfgui.gui.components.ItemNBT;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;
import java.util.Map;

public class EnchantmentItemBuilder {

    private final int material;
    private final short data;
    private final int amount;
    private String display;
    private List<String> lore;
    private Enchantment enchantment;
    private int level;

    private Map<String, String> nbt;

    public EnchantmentItemBuilder(final int material, final short data, final int amount) {
        this.material = material;
        this.data = data;
        this.amount = amount;
    }

    public final void setDisplay(final String value) {
        this.display = value;
    }

    public final void setLore(final List<String> values) {
        this.lore = values;
    }

    public final void setNbt(final String... values) {
        nbt = Replace.getReplacements(values);
    }

    public final void setEnchantment(final Enchantment enchantment, final int level) {
        this.enchantment = enchantment;
        this.level = level;
    }

    public ItemStack getItem() {
        ItemStack item = new ItemStack(material, amount, data);
        final ItemMeta meta = item.getItemMeta();

        if (display != null) {
            meta.setDisplayName(Color.colorize(display));
        }
        if (!lore.isEmpty()) {
            meta.setLore(Color.colorize(lore));
        }

        item.setItemMeta(meta);

        item.addEnchantment(enchantment, level);
        for (String nbtString : nbt.keySet()) {
            item = ItemNBT.setNBTTag(item, nbtString, nbt.get(nbtString));
        }

        return item;
    }
}
