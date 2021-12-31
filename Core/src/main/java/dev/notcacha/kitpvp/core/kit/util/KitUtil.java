package dev.notcacha.kitpvp.core.kit.util;

import dev.notcacha.kitpvp.api.item.SerializableItem;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import java.util.HashMap;
import java.util.Map;

public class KitUtil {

    public static Map<Integer, SerializableItem> editArmor(Player player) {
        Map<Integer, SerializableItem> map = new HashMap<>();

        PlayerInventory inventory = player.getInventory();

        map.put(0, SerializableItem.fromItemStack(inventory.getHelmet()));
        map.put(1, SerializableItem.fromItemStack(inventory.getChestplate()));
        map.put(2, SerializableItem.fromItemStack(inventory.getLeggings()));
        map.put(3, SerializableItem.fromItemStack(inventory.getBoots()));

        return  map;
    }

    public static Map<Integer, SerializableItem> editContents(Player player) {
        Map<Integer, SerializableItem> map = new HashMap<>();

        ItemStack[] itemStacks = player.getInventory().getContents();

        for (int i = 0; i < itemStacks.length; i++) {
            ItemStack item = itemStacks[i];

            map.put(i,
                    SerializableItem.fromItemStack(item)
                    );
        }

        return  map;
    }
}
