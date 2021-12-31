package dev.notcacha.kitpvp.core.util;

import dev.notcacha.kitpvp.api.item.SerializableItem;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;

public class SerializableItemDeserializerUtil {

    public static Map<Integer, ItemStack> toItemStack(Map<Integer, SerializableItem> map) {
        Map<Integer, ItemStack> itemMap = new HashMap<>();

        map.forEach((integer, item) -> itemMap.put(integer, item.toItemStack()));

        return itemMap;
    }

    public static Map<Integer, SerializableItem> fromItemStack(Map<Integer, ItemStack> map) {
        Map<Integer, SerializableItem> itemMap = new HashMap<>();

        map.forEach((integer, item) -> itemMap.put(integer, SerializableItem.fromItemStack(item)));

        return itemMap;
    }

}
