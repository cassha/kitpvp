package dev.notcacha.kitpvp.core.command;

import dev.notcacha.kitpvp.core.command.chat.ChatClearCommand;
import dev.notcacha.kitpvp.core.command.chat.ChatMuteCommand;
import me.fixeddev.commandflow.annotated.CommandClass;
import me.fixeddev.commandflow.annotated.annotation.Command;
import me.fixeddev.commandflow.annotated.annotation.SubCommandClasses;
import me.fixeddev.commandflow.bukkit.annotation.Sender;
import me.yushust.message.MessageHandler;
import org.bukkit.entity.Player;

import javax.inject.Inject;

@Command(names = "chat", permission = "kitpvp.chat")
@SubCommandClasses({
        ChatClearCommand.class,
        ChatMuteCommand.class
})
public class ChatCommand implements CommandClass {

    @Inject private MessageHandler messageHandler;

    @Command(names = "")
    public boolean main(@Sender Player player) {

        messageHandler.send(player, "chat.usage");

        return true;
    }

}
