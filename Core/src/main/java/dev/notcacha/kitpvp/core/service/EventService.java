package dev.notcacha.kitpvp.core.service;

import javax.inject.Inject;
import dev.notcacha.kitpvp.api.service.Service;
import dev.notcacha.kitpvp.core.listener.PlayerDeathListener;
import dev.notcacha.kitpvp.core.listener.PlayerListener;
import dev.notcacha.kitpvp.core.listener.user.UserListener;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;

public class EventService implements Service {

    @Inject private Plugin plugin;

    @Inject private PlayerListener playerListener;
    @Inject private PlayerDeathListener deathListener;
    @Inject private UserListener userListener;

    @Override
    public void start() {
        plugin.getServer().getLogger().info("[KitPvP] The event service has been started, the events has been load.");

        register(
                playerListener,
                deathListener,
                userListener
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
