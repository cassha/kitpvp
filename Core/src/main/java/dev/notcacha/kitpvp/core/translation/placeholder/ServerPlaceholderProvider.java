package dev.notcacha.kitpvp.core.translation.placeholder;

import me.yushust.message.format.PlaceholderProvider;
import me.yushust.message.track.ContextRepository;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.Nullable;

public class ServerPlaceholderProvider implements PlaceholderProvider<Player> {

    private final Plugin plugin;

    public ServerPlaceholderProvider(Plugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public @Nullable Object replace(ContextRepository contextRepository, Player player, String text) {
        if (text.equals("online_players")) {
            return String.valueOf(plugin.getServer().getOnlinePlayers().size());
        }


        return null;
    }
}
