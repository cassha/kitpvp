package dev.notcacha.kitpvp.core.listener;

import javax.inject.Inject;
import dev.notcacha.kitpvp.api.cache.ObjectCache;
import dev.notcacha.kitpvp.api.event.UserJoinEvent;
import dev.notcacha.kitpvp.api.event.UserLeaveEvent;
import dev.notcacha.kitpvp.api.model.processor.ModelFindProcessor;
import dev.notcacha.kitpvp.api.model.processor.ModelSaveProcessor;
import dev.notcacha.kitpvp.api.user.User;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.Plugin;

import java.util.Optional;

public class PlayerListener implements Listener {

    @Inject private Plugin plugin;
    @Inject private ModelFindProcessor<User> userModelFindProcessor;
    @Inject private ModelSaveProcessor<User> userModelSaveProcessor;
    @Inject private ObjectCache<User> userObjectCache;

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        String id = event.getPlayer().getUniqueId().toString();

        event.setJoinMessage(null);

        userModelFindProcessor.findOneAsync(id).callback(callback -> {
            Optional<User> response = callback.getResponse();

            if (!response.isPresent()) {
                throw new IllegalArgumentException("The user of id " + id + " not found.");
            }

            plugin.getServer().getPluginManager().callEvent(
                    new UserJoinEvent(response.get(), event.getPlayer())
            );
        });
    }

    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent event) {
        event.setQuitMessage(null);

        User user = userObjectCache.findIfPresent(event.getPlayer().getUniqueId().toString()).orElse(null);

        System.out.println(user);

        plugin.getServer().getPluginManager().callEvent(new UserLeaveEvent(user, event.getPlayer()));

        userModelSaveProcessor.saveAsync(user, false);
    }

}
