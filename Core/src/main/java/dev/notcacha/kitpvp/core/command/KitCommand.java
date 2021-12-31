package dev.notcacha.kitpvp.core.command;

import javax.inject.Inject;
import dev.notcacha.kitpvp.api.message.MessageHandler;
import dev.notcacha.kitpvp.core.command.kit.KitCreateCommand;
import dev.notcacha.kitpvp.core.command.kit.KitDeleteCommand;
import dev.notcacha.kitpvp.core.command.kit.KitEditCommand;
import me.fixeddev.commandflow.annotated.CommandClass;
import me.fixeddev.commandflow.annotated.annotation.Command;
import me.fixeddev.commandflow.annotated.annotation.SubCommandClasses;
import me.fixeddev.commandflow.bukkit.annotation.Sender;
import org.bukkit.entity.Player;

@Command(names = "kit")
@SubCommandClasses({
        KitCreateCommand.class,
        KitDeleteCommand.class,
        KitEditCommand.class
})
public class KitCommand implements CommandClass {

    @Inject private MessageHandler messageHandler;

    @Command(names = "")
    public boolean main(@Sender Player player) {

        messageHandler.getMessages("kit.usage").forEach(player::sendMessage);

        return true;
    }
}
