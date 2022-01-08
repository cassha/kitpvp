package dev.notcacha.kitpvp.core.listener.spawn;

import dev.notcacha.kitpvp.api.event.UserJoinEvent;
import dev.notcacha.kitpvp.core.spawn.SpawnManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

import javax.inject.Inject;

public class SpawnTeleportListener implements Listener {

    @Inject private SpawnManager spawnManager;

    @EventHandler(ignoreCancelled = true, priority = EventPriority.HIGH)
    public void onUserJoin(UserJoinEvent event) {
        if (!spawnManager.isDefaultTeleport() || !spawnManager.isPresent()) {
            System.out.println("pepe");
            return;
        }

        event.getPlayer().teleport(spawnManager.getLocation());
    }
}
