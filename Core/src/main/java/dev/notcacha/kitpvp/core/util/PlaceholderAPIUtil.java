package dev.notcacha.kitpvp.core.util;

import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.List;

public class PlaceholderAPIUtil {

    public static String detectAndApply(Player player, String text) {
        if (Bukkit.getServer().getPluginManager().isPluginEnabled("PlaceholderAPI")) {
            return PlaceholderAPI.setPlaceholders(player, text);
        }

        return text;
    }

    public static List<String> detectAndApply(Player player, List<String> list) {
        list.replaceAll(string -> detectAndApply(player, string));

        return list;
    }
}
