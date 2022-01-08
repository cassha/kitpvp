package dev.notcacha.kitpvp.core.util;

import dev.notcacha.kitpvp.api.item.SerializableItem;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import java.util.HashMap;
import java.util.Map;

public class PlayerInventoryUtil {

    public static void clear(Player player) {
        player.getInventory().clear();

        player.getInventory().setHelmet(null);
        player.getInventory().setChestplate(null);
        player.getInventory().setLeggings(null);
        player.getInventory().setBoots(null);
    }

    public static Map<Integer, SerializableItem> getArmor(PlayerInventory inventory) {
        Map<Integer, SerializableItem> map = new HashMap<>();

        ItemStack helmet = inventory.getHelmet();
        if (helmet != null) {
            map.put(0, SerializableItem.fromItemStack(helmet));
        }

        ItemStack chestPlate = inventory.getChestplate();
        if (chestPlate != null) {
            map.put(1, SerializableItem.fromItemStack(chestPlate));
        }

        ItemStack leggings = inventory.getLeggings();
        if (leggings != null) {
            map.put(2, SerializableItem.fromItemStack(leggings));
        }

        ItemStack boots = inventory.getBoots();
        if (boots != null) {
            map.put(3, SerializableItem.fromItemStack(boots));
        }

        return map;
    }

    public static Map<Integer, SerializableItem> getItems(PlayerInventory inventory) {
        Map<Integer, SerializableItem> map = new HashMap<>();

        ItemStack[] itemStacks = inventory.getContents();

        for (int i = 0; i < itemStacks.length; i++) {
            if (itemStacks[i] != null && itemStacks[i].getType() != Material.AIR) {
                map.put(i, SerializableItem.fromItemStack(itemStacks[i]));
            }
        }

        return map;
    }

}
