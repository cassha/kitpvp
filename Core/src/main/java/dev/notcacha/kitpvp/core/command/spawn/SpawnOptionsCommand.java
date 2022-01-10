package dev.notcacha.kitpvp.core.command.spawn;

import javax.inject.Inject;
import dev.notcacha.kitpvp.core.gui.SpawnOptionsGUI;
import dev.notcacha.kitpvp.core.spawn.SpawnManager;
import me.fixeddev.commandflow.annotated.CommandClass;
import me.fixeddev.commandflow.annotated.annotation.Command;
import me.fixeddev.commandflow.bukkit.annotation.Sender;
import me.yushust.message.MessageHandler;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

@Command(names = "options", permission = "kitpvp.spawn.options")
public class SpawnOptionsCommand implements CommandClass {

    @Inject private MessageHandler messageHandler;
    @Inject private SpawnManager spawnManager;
    @Inject private Plugin plugin;

    @Command(names = "")
    public boolean options(@Sender Player player) {

        new SpawnOptionsGUI(plugin, player, messageHandler.getMessage("spawn.options.title"), messageHandler, spawnManager);
        return true;
    }
}
