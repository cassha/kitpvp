package dev.notcacha.kitpvp.core.service;

import javax.inject.Inject;
import dev.notcacha.kitpvp.api.service.Service;
import dev.notcacha.kitpvp.core.listener.PlayerDeathListener;
import dev.notcacha.kitpvp.core.listener.PlayerListener;
import dev.notcacha.kitpvp.core.listener.chat.ChatListener;
import dev.notcacha.kitpvp.core.listener.scoreboard.ScoreboardListener;
import dev.notcacha.kitpvp.core.listener.soup.SoupListener;
import dev.notcacha.kitpvp.core.listener.spawn.SpawnTeleportListener;
import dev.notcacha.kitpvp.core.listener.user.UserListener;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;

public class EventService implements Service {

    @Inject private Plugin plugin;

    @Inject private PlayerListener playerListener;
    @Inject private PlayerDeathListener deathListener;
    @Inject private UserListener userListener;
    @Inject private ChatListener chatListener;
    @Inject private SpawnTeleportListener spawnTeleportListener;
    @Inject private ScoreboardListener scoreboardListener;
    @Inject private SoupListener soupListener;

    @Override
    public void start() {
        plugin.getServer().getLogger().info("[KitPvP] The event service has been started, the events has been load.");

        register(
                playerListener,
                deathListener,
                userListener,
                chatListener,
                spawnTeleportListener,
                scoreboardListener,
                soupListener
        );
    }

    private void register(Listener... listeners) {
        for (Listener listener : listeners) {
            plugin.getServer().getPluginManager().registerEvents(listener, plugin);
        }
    }

    @Override
    public void stop() {
        plugin.getServer().getLogger().info("[KitPvP] The event service has been stopped, the events has been un-load.");
    }
}
