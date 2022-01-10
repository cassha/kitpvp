package dev.notcacha.kitpvp.core.gui;

import dev.notcacha.kitpvp.core.spawn.SpawnManager;
import me.patothebest.guiframework.gui.inventory.GUIPage;
import me.patothebest.guiframework.gui.inventory.button.SimpleButton;
import me.patothebest.guiframework.itemstack.ItemStackBuilder;
import me.yushust.message.MessageHandler;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class SpawnOptionsGUI extends GUIPage {

    private final MessageHandler messageHandler;
    private final SpawnManager spawnManager;

    public SpawnOptionsGUI(Plugin plugin, Player player, String rawName, MessageHandler messageHandler, SpawnManager spawnManager) {
        super(plugin, player, rawName, 9);
        this.messageHandler = messageHandler;
        this.spawnManager = spawnManager;

        build();
    }

    @Override
    public void buildPage() {

       addButton(buildButton("enabled"), 0);
       addButton(buildButton("disabled"), 8);

    }

    private SimpleButton buildButton(String type) {
        return new SimpleButton(buildItem(type)).onClick(() -> {

            switch (type) {

                case "enabled": {
                    if (spawnManager.isDefaultTeleport()) {
                        send(player, "enabled", "is-enabled");
                    } else {
                        spawnManager.setDefaultTeleport(true);
                        send(player, "enabled", "enabled");
                    }

                    player.closeInventory();
                    return;
                }

                case "disabled": {
                    if (!spawnManager.isDefaultTeleport()) {
                        send(player, "disabled", "is-disabled");
                    } else {
                        spawnManager.setDefaultTeleport(false);

                        send(player, "disabled", "disabled");
                    }

                    player.closeInventory();
                }

            }

        });
    }

    private ItemStackBuilder buildItem(String type) {
        return new ItemStackBuilder(type.equals("enabled") ? Material.EMERALD_BLOCK : Material.REDSTONE)
                .name(messageHandler.getMessage("spawn.options.items." + type + ".name"))
                .lore(messageHandler.getMessages("spawn.options.items." + type + ".lore"));
    }

    private void send(Player player, String path, String otherPath) {
        messageHandler.send(player, "spawn.options.items" + path + "." + otherPath);
    }

}
