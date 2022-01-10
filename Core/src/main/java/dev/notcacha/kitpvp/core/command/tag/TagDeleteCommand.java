package dev.notcacha.kitpvp.core.command.tag;

import dev.notcacha.kitpvp.api.repository.ModelRepository;
import dev.notcacha.kitpvp.api.tag.Tag;
import me.fixeddev.commandflow.annotated.CommandClass;
import me.fixeddev.commandflow.annotated.annotation.Command;
import me.fixeddev.commandflow.annotated.annotation.OptArg;
import me.fixeddev.commandflow.bukkit.annotation.Sender;
import me.yushust.message.MessageHandler;
import org.bukkit.entity.Player;

import javax.inject.Inject;

@Command(names = "delete", permission = "kitpvp.tag.delete")
public class TagDeleteCommand implements CommandClass {

    @Inject private ModelRepository<Tag> tagModelRepository;
    @Inject private MessageHandler messageHandler;

    @Command(names = "")
    public boolean main(@Sender Player player, @OptArg String tagId) {
        if (tagId == null || tagId.trim().isEmpty()) {
            messageHandler.send(player, "tag.delete.usage");
            return true;
        }

        tagModelRepository.delete(tagId).callback(callback -> {
            if (callback.getResponse().get()) {
                messageHandler.sendReplacing(player, "tag.delete.success", "%tag_id%", tagId);

                return;
            }

            messageHandler.sendReplacing(player, "tag.delete.error", "%tag_id%", tagId);
        });

        return true;
    }
}
