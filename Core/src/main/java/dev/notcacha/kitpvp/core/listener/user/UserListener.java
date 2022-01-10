package dev.notcacha.kitpvp.core.listener.user;

import javax.inject.Inject;
import dev.notcacha.kitpvp.api.event.UserDeathEvent;
import dev.notcacha.kitpvp.api.event.UserJoinEvent;
import dev.notcacha.kitpvp.api.event.UserLeaveEvent;
import me.yushust.message.MessageHandler;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;

public class UserListener implements Listener {

    @Inject private MessageHandler messageHandler;
    @Inject private Plugin plugin;

    @EventHandler
    public void onUserJoin(UserJoinEvent event) {
        Player player = event.getPlayer();

        messageHandler.sendReplacingIn(
                player,
                "placeholder_api",
                "join.player"
        );

        if (messageHandler.getMessage("join.online-players").equals("null")) return;

        plugin.getServer().getOnlinePlayers().forEach(onlinePlayer -> messageHandler.sendReplacingIn(onlinePlayer, "placeholder_api", "join.online-players", "%player_name%", event.getPlayer().getName()));

    }

    @EventHandler
    public void onUserLeave(UserLeaveEvent event) {
        if (messageHandler.getMessage("quit").equals("null")) return;

        plugin.getServer().getOnlinePlayers().forEach(onlinePlayer -> messageHandler.sendReplacingIn(onlinePlayer, "placeholder_api", "quit", "%player_name%", event.getPlayer().getName()));
    }

    @EventHandler
    public void onUserDeath(UserDeathEvent event) {

        if (event.getKiller() == null) return;

        plugin.getServer().getOnlinePlayers().forEach(player -> messageHandler.sendReplacingIn(
                player,
                "placeholder_api",
                "death-message",
                "%player_name%",
                event.getUser().getUsername(),
                "%killer_name%",
                event.getKiller().getUsername()
        ));
    }
}
