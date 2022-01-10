package dev.notcacha.kitpvp.core.command;

import dev.notcacha.kitpvp.api.repository.ModelRepository;
import dev.notcacha.kitpvp.api.tag.Tag;
import dev.notcacha.kitpvp.api.tag.applier.TagApplier;
import dev.notcacha.kitpvp.core.command.tag.TagCreateCommand;
import dev.notcacha.kitpvp.core.command.tag.TagDeleteCommand;
import dev.notcacha.kitpvp.core.command.tag.TagEditCommand;
import dev.notcacha.kitpvp.core.gui.tag.TagGUI;
import me.fixeddev.commandflow.annotated.CommandClass;
import me.fixeddev.commandflow.annotated.annotation.Command;
import me.fixeddev.commandflow.annotated.annotation.SubCommandClasses;
import me.fixeddev.commandflow.bukkit.annotation.Sender;
import me.yushust.message.MessageHandler;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import javax.inject.Inject;

@Command(names = "tag", permission = "kitpvp.tag")
@SubCommandClasses({
        TagCreateCommand.class,
        TagDeleteCommand.class,
        TagEditCommand.class,
})
public class TagCommand implements CommandClass {

    @Inject private ModelRepository<Tag> modelRepository;
    @Inject private MessageHandler messageHandler;
    @Inject private TagApplier tagApplier;
    @Inject private Plugin plugin;

    @Command(names = "")
    public boolean main(@Sender Player player) {

        new TagGUI(plugin, player,messageHandler.get(player, "tag.gui.title"), tagApplier, messageHandler, modelRepository);

        return true;
    }
}
