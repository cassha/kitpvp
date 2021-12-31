package dev.notcacha.kitpvp.core.command;

import javax.inject.Inject;
import dev.notcacha.kitpvp.api.message.MessageHandler;
import dev.notcacha.kitpvp.core.command.spawn.SpawnOptionsCommand;
import dev.notcacha.kitpvp.core.spawn.SpawnManager;
import me.fixeddev.commandflow.annotated.CommandClass;
import me.fixeddev.commandflow.annotated.annotation.Command;
import me.fixeddev.commandflow.annotated.annotation.SubCommandClasses;
import me.fixeddev.commandflow.bukkit.annotation.Sender;
import org.bukkit.entity.Player;

@Command(names = "spawn", permission = "kitpvp.spawn")
@SubCommandClasses({SpawnOptionsCommand.class})
public class SpawnCommand implements CommandClass {

    @Inject private MessageHandler messageHandler;
    @Inject private SpawnManager spawnManager;

    @Command(names = "")
    public boolean mainCommand(@Sender Player player) {

        if (!spawnManager.isPresent()) {
            player.sendMessage(
                    messageHandler.getMessage(
                            "spawn.not-exists"
                    )
            );

            return true;
        }

        player.teleport(spawnManager.getLocation());

        player.sendMessage(
                messageHandler.getMessage(
                        "spawn.teleport"
                )
        );
        return true;
    }
}
