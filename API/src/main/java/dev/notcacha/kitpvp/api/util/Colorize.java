package dev.notcacha.kitpvp.api.util;

import org.bukkit.ChatColor;

import java.util.List;

public interface Colorize {

    static String colorize(String string) {
        return ChatColor.translateAlternateColorCodes('&', string);
    }

    static List<String> colorize(List<String> list) {
        list.replaceAll(Colorize::colorize);

        return list;
    }
}
