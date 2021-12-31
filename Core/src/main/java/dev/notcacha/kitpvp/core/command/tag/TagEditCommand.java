package dev.notcacha.kitpvp.core.command.tag;

import dev.notcacha.kitpvp.api.message.MessageHandler;
import dev.notcacha.kitpvp.api.model.processor.ModelFindProcessor;
import dev.notcacha.kitpvp.api.tag.Tag;
import dev.notcacha.kitpvp.core.gui.tag.TagEditorGUI;
import me.fixeddev.commandflow.annotated.CommandClass;
import me.fixeddev.commandflow.annotated.annotation.Command;
import me.fixeddev.commandflow.annotated.annotation.OptArg;
import me.fixeddev.commandflow.bukkit.annotation.Sender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import javax.inject.Inject;
import java.util.Optional;

@Command(names = "edit", permission = "kitpvp.tag.edit")
public class TagEditCommand implements CommandClass {

    @Inject private MessageHandler messageHandler;
    @Inject private ModelFindProcessor<Tag> tagModelFindProcessor;
    @Inject private Plugin plugin;

    @Command(names = "")
    public boolean main(@Sender Player player, @OptArg String tagId) {
        if (tagId == null || tagId.trim().isEmpty()) {
            player.sendMessage(messageHandler.getMessage("tag.edit.usage"));
            return true;
        }

        tagModelFindProcessor.findOneAsync(tagId).callback(callback -> {
            Optional<Tag> callbackResponse = callback.getResponse();

            if (!callbackResponse.isPresent()) {

                player.sendMessage(messageHandler.getMessage("tag.edit.error").replace("%tag_id%", tagId));

                return;
            }

            new TagEditorGUI(plugin, player, messageHandler.getMessage("tag.edit.title").replace("%tag_id%", tagId), messageHandler, callbackResponse.get());
        });
        return true;
    }
}
