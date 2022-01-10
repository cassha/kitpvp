package dev.notcacha.kitpvp.core.command.spawn;

import javax.inject.Inject;
import dev.notcacha.kitpvp.core.spawn.SpawnManager;
import me.fixeddev.commandflow.annotated.CommandClass;
import me.fixeddev.commandflow.annotated.annotation.Command;
import me.fixeddev.commandflow.bukkit.annotation.Sender;
import me.yushust.message.MessageHandler;
import org.bukkit.entity.Player;

@Command(names = "setspawn", permission = "kitpvp.spawn.set")
public class SetSpawnCommand implements CommandClass {

    @Inject private MessageHandler messageHandler;
    @Inject private SpawnManager spawnManager;

    @Command(names = "")
    public boolean mainCommand(@Sender Player player) {

        spawnManager.setLocation(player.getLocation());

        messageHandler.send(player, "spawn.success");

        return true;
    }
}
