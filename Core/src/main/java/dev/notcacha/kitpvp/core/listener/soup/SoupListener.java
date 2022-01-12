package dev.notcacha.kitpvp.core.listener.soup;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;

public class SoupListener implements Listener {

    @EventHandler
    public void onPlayerInteract(PlayerItemConsumeEvent event) {

        ItemStack item = event.getItem();

        if (item != null && item.getType() == Material.MUSHROOM_STEW) {
            Player player = event.getPlayer();

            if (player.getHealth() < 20) {
                player.setHealth(20.0D);
            }
        }
    }
}
