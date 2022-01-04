package dev.notcacha.kitpvp.core.command.tag;

import dev.notcacha.kitpvp.api.message.MessageHandler;
import dev.notcacha.kitpvp.api.tag.delete.TagDelete;
import me.fixeddev.commandflow.annotated.CommandClass;
import me.fixeddev.commandflow.annotated.annotation.Command;
import me.fixeddev.commandflow.annotated.annotation.OptArg;
import me.fixeddev.commandflow.bukkit.annotation.Sender;
import org.bukkit.entity.Player;

import javax.inject.Inject;

@Command(names = "delete", permission = "kitpvp.tag.delete")
public class TagDeleteCommand implements CommandClass {

    @Inject private TagDelete tagDelete;
    @Inject private MessageHandler messageHandler;

    @Command(names = "")
    public boolean main(@Sender Player player, @OptArg String tagId) {
        if (tagId == null || tagId.trim().isEmpty()) {
            player.sendMessage(messageHandler.getMessage("tag.delete.usage"));
            return true;
        }

        if (tagDelete.delete(tagId)) {
            player.sendMessage(messageHandler.getMessage("tag.delete.success").replace("%tag_id%", tagId));
            return true;
        }

        player.sendMessage(messageHandler.getMessage("tag.delete.error").replace("%tag_id%", tagId));
        return true;
    }
}
