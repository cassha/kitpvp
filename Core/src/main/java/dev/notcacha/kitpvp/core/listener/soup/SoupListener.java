package dev.notcacha.kitpvp.core.listener.soup;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

public class SoupListener implements Listener {

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        if (event.getItem() == null || !(event.getItem().getType() == Material.MUSHROOM_SOUP)) return;

        Player player = event.getPlayer();

        if (player.getHealth() != 20) {
            player.setHealth(20.0D);
        }
    }
}
