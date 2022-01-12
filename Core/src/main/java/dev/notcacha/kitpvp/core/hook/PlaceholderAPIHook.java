package dev.notcacha.kitpvp.core.hook;

import dev.notcacha.kitpvp.api.cache.ObjectCache;
import dev.notcacha.kitpvp.api.user.User;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import javax.inject.Inject;
import java.util.Optional;

public class PlaceholderAPIHook extends PlaceholderExpansion {

    @Inject private ObjectCache<User> userObjectCache;
    @Inject private Plugin plugin;

    @Override
    public String getIdentifier() {
        return "kitpvp";
    }

    @Override
    public String getAuthor() {
        return "cassha";
    }

    @Override
    public String getVersion() {
        return "0.0.1";
    }

    @Override
    public String onPlaceholderRequest(Player player, String identifier) {
        Optional<User> optionalUser = userObjectCache.findIfPresent(player.getUniqueId().toString());

        if (!optionalUser.isPresent()) {
            return "";
        }

        User user = optionalUser.get();

        switch (identifier) {

            case "kills": {
                return String.valueOf(user.getStatistics().getKillsStatistic().getAmount());
            }

            case "deaths": {
                return String.valueOf(user.getStatistics().getDeathsStatistic().getAmount());
            }
        }

        return null;
    }

    public boolean register() {
        if (!plugin.getServer().getPluginManager().getPlugin("PlaceholderAPI").isEnabled()) {

            plugin.getServer().getLogger().warning("[KitPvP] The PlaceholderAPI is not found in server from create hook.");

            return false;
        }

        plugin.getServer().getLogger().info("[KitPvP] The plugin hook with PlaceholderAPI has been created correctly.");

        return super.register();
    }
}
