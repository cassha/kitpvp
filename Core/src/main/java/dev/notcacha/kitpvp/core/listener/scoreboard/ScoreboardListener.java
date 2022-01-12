package dev.notcacha.kitpvp.core.listener.scoreboard;

import dev.notcacha.kitpvp.api.event.UserJoinEvent;
import dev.notcacha.kitpvp.api.event.UserLeaveEvent;
import dev.notcacha.kitpvp.api.scoreboard.ScoreboardSetup;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;

import javax.inject.Inject;

public class ScoreboardListener implements Listener {

    @Inject private ScoreboardSetup scoreboardSetup;
    @Inject private Plugin plugin;

    @EventHandler
    public void onUserLeave(UserLeaveEvent event) {
        if (plugin.getConfig().getBoolean("scoreboard")) {
            Bukkit.getScheduler().callSyncMethod(plugin, () -> {

                scoreboardSetup.remove(event.getPlayer());

                return null;
            });
        }
    }
}
