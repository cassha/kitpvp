package dev.notcacha.kitpvp.core.hook;

import dev.notcacha.kitpvp.api.cache.ObjectCache;
import dev.notcacha.kitpvp.api.user.User;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.entity.Player;

import java.util.Optional;

public class PlaceholderAPIHook extends PlaceholderExpansion {

    private final ObjectCache<User> userObjectCache;

    public PlaceholderAPIHook(ObjectCache<User> userObjectCache) {
        this.userObjectCache = userObjectCache;
    }

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
}
