package dev.notcacha.kitpvp.core.listener.user;

import javax.inject.Inject;
import dev.notcacha.kitpvp.api.event.UserDeathEvent;
import dev.notcacha.kitpvp.api.event.UserJoinEvent;
import dev.notcacha.kitpvp.api.event.UserLeaveEvent;
import dev.notcacha.kitpvp.api.message.MessageHandler;
import dev.notcacha.kitpvp.core.spawn.SpawnManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;

public class UserListener implements Listener {

    @Inject private MessageHandler messageHandler;
    @Inject private Plugin plugin;
    @Inject private SpawnManager spawnManager;

    @EventHandler
    public void onUserJoin(UserJoinEvent event) {
        messageHandler.getMessages("join.player").forEach(message -> event.getPlayer().sendMessage(message));

        String onlineMessage = messageHandler.getMessage("join.online-players");

        if (onlineMessage.equals("null")) return;

        plugin.getServer().getOnlinePlayers().forEach(p -> p.sendMessage(
                onlineMessage.replace("%player_name%", event.getPlayer().getName())
        ));

        if (spawnManager.isDefaultTeleport()) {
            if (!spawnManager.isPresent()) return;

            event.getPlayer().teleport(spawnManager.getLocation());
        }
    }

    @EventHandler
    public void onUserLeave(UserLeaveEvent event) {
        String quitMessage = messageHandler.getMessage("quit");

        if (quitMessage.equals("null")) return;

        plugin.getServer().getOnlinePlayers().forEach(p -> p.sendMessage(
                quitMessage.replace("%player_name%", event.getPlayer().getName())
        ));
    }

    @EventHandler
    public void onUserDeath(UserDeathEvent event) {
        String deathMessage = messageHandler.getMessage("death-message");

        if (deathMessage.equals("null") || event.getKiller() == null) return;

        plugin.getServer().getOnlinePlayers().forEach(player -> {
            player.sendMessage(
                    deathMessage
                            .replace("%player_name%", event.getUser().getUsername())
                            .replace("%killer_name%", event.getKiller().getUsername())
            );
        });
    }
}
