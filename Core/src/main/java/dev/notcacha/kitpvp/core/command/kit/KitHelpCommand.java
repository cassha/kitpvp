package dev.notcacha.kitpvp.core.command.kit;

import dev.notcacha.kitpvp.api.message.MessageHandler;
import me.fixeddev.commandflow.annotated.CommandClass;
import me.fixeddev.commandflow.annotated.annotation.Command;
import me.fixeddev.commandflow.bukkit.annotation.Sender;
import org.bukkit.entity.Player;

import javax.inject.Inject;

@Command(names = "help", permission = "kit.help")
public class KitHelpCommand implements CommandClass {

    @Inject
    private MessageHandler messageHandler;

    @Command(names = "")
     public boolean help(@Sender Player player) {

        messageHandler.getMessages("kit.usage").forEach(player::sendMessage);

        return true;
    }
}
