package com.github.frcsty.districtenchants.enchantments;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public enum CustomEnchantmentTarget {

    ALL {
        @Override
        public boolean includes(Material item) {
            return true;
        }
    },

    ARMOR {
        @Override
        public boolean includes(Material item) {
            return ARMOR_FEET.includes(item)
                    || ARMOR_LEGS.includes(item)
                    || ARMOR_HEAD.includes(item)
                    || ARMOR_TORSO.includes(item);
        }
    },

    ARMOR_FEET {
        @Override
        public boolean includes(Material item) {
            return item.equals(Material.LEATHER_BOOTS)
                    || item.equals(Material.CHAINMAIL_BOOTS)
                    || item.equals(Material.IRON_BOOTS)
                    || item.equals(Material.DIAMOND_BOOTS)
                    || item.equals(Material.GOLD_BOOTS);
        }
    },

    ARMOR_LEGS {
        @Override
        public boolean includes(Material item) {
            return item.equals(Material.LEATHER_LEGGINGS)
                    || item.equals(Material.CHAINMAIL_LEGGINGS)
                    || item.equals(Material.IRON_LEGGINGS)
                    || item.equals(Material.DIAMOND_LEGGINGS)
                    || item.equals(Material.GOLD_LEGGINGS);
        }
    },

    ARMOR_TORSO {
        @Override
        public boolean includes(Material item) {
            return item.equals(Material.LEATHER_CHESTPLATE)
                    || item.equals(Material.CHAINMAIL_CHESTPLATE)
                    || item.equals(Material.IRON_CHESTPLATE)
                    || item.equals(Material.DIAMOND_CHESTPLATE)
                    || item.equals(Material.GOLD_CHESTPLATE);
        }
    },

    HOLDABLE {
        @Override
        public boolean includes(Material item) {
            return TOOL.includes(item) || WEAPON.includes(item);
        }
    },

    ARMOR_HEAD {
        @Override
        public boolean includes(Material item) {
            return item.equals(Material.LEATHER_HELMET)
                    || item.equals(Material.CHAINMAIL_HELMET)
                    || item.equals(Material.DIAMOND_HELMET)
                    || item.equals(Material.IRON_HELMET)
                    || item.equals(Material.GOLD_HELMET);
        }
    },

    WEAPON {
        @Override
        public boolean includes(Material item) {
            return item.equals(Material.WOOD_SWORD)
                    || item.equals(Material.STONE_SWORD)
                    || item.equals(Material.IRON_SWORD)
                    || item.equals(Material.DIAMOND_SWORD)
                    || item.equals(Material.GOLD_SWORD);
        }
    },

    WEAPONS {
        @Override
        public boolean includes(Material item) {
            return WEAPON.includes(item) || AXES.includes(item);
        }
    },

    AXES {
        @Override
        public boolean includes(Material item) {
            return item.equals(Material.WOOD_AXE)
                    || item.equals(Material.STONE_AXE)
                    || item.equals(Material.IRON_AXE)
                    || item.equals(Material.DIAMOND_AXE)
                    || item.equals(Material.GOLD_AXE);
        }
    },

    PICKAXES {
        @Override
        public boolean includes(Material item) {
            return item.equals(Material.WOOD_PICKAXE)
                    || item.equals(Material.STONE_PICKAXE)
                    || item.equals(Material.IRON_PICKAXE)
                    || item.equals(Material.DIAMOND_PICKAXE)
                    || item.equals(Material.GOLD_PICKAXE);
        }
    },

    TOOL {
        @Override
        public boolean includes(Material item) {
            return item.equals(Material.WOOD_SPADE)
                    || item.equals(Material.STONE_SPADE)
                    || item.equals(Material.IRON_SPADE)
                    || item.equals(Material.DIAMOND_SPADE)
                    || item.equals(Material.GOLD_SPADE)
                    || item.equals(Material.WOOD_PICKAXE)
                    || item.equals(Material.STONE_PICKAXE)
                    || item.equals(Material.IRON_PICKAXE)
                    || item.equals(Material.DIAMOND_PICKAXE)
                    || item.equals(Material.GOLD_PICKAXE)
                    || item.equals(Material.WOOD_HOE)         // NOTE: No vanilla enchantments for this
                    || item.equals(Material.STONE_HOE)        // NOTE: No vanilla enchantments for this
                    || item.equals(Material.IRON_HOE)         // NOTE: No vanilla enchantments for this
                    || item.equals(Material.DIAMOND_HOE)      // NOTE: No vanilla enchantments for this
                    || item.equals(Material.GOLD_HOE)         // NOTE: No vanilla enchantments for this
                    || item.equals(Material.WOOD_AXE)
                    || item.equals(Material.STONE_AXE)
                    || item.equals(Material.IRON_AXE)
                    || item.equals(Material.DIAMOND_AXE)
                    || item.equals(Material.GOLD_AXE)
                    || item.equals(Material.SHEARS)           // NOTE: No vanilla enchantments for this
                    || item.equals(Material.FLINT_AND_STEEL); // NOTE: No vanilla enchantments for this
        }
    },

    BOW {
        @Override
        public boolean includes(Material item) {
            return item.equals(Material.BOW);
        }
    },

    FISHING_ROD {
        @Override
        public boolean includes(Material item) {
            return item.equals(Material.FISHING_ROD);
        }
    };

    public abstract boolean includes(Material item);

    public boolean includes(ItemStack item) {
        return includes(item.getType());
    }
}
