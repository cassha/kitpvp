package dev.notcacha.kitpvp.core.listener;

import dev.notcacha.kitpvp.api.event.UserDeathEvent;
import dev.notcacha.kitpvp.api.model.processor.ModelFindProcessor;
import dev.notcacha.kitpvp.api.user.User;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.plugin.Plugin;

import javax.inject.Inject;

public class PlayerDeathListener implements Listener {

    @Inject private ModelFindProcessor<User> userModelFindProcessor;
    @Inject private Plugin plugin;

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        Player player = event.getEntity();

        event.setDeathMessage(null);

        userModelFindProcessor.findOneAsync(player.getUniqueId().toString()).callback(callback -> {
            callback.ifSuccessful(user -> {
                user.getStatistics().getDeathsStatistic().increase(1);

                plugin.getServer().getPluginManager().callEvent(
                        new UserDeathEvent(
                                user,
                                player,
                                null,
                                null
                        )
                );

                if (event.getEntity().getKiller() != null) {
                    Player killer = event.getEntity().getKiller();
                    userModelFindProcessor.findOneAsync(killer.getUniqueId().toString()).callback(killerCallback -> {
                        killerCallback.ifSuccessful(userKiller -> {
                            userKiller.getStatistics().getKillsStatistic().increase(1);

                            plugin.getServer().getPluginManager().callEvent(
                                    new UserDeathEvent(
                                            user,
                                            player,
                                            userKiller,
                                            killer
                                    )
                            );
                        });

                    });
                }

            });
        });

    }
}
