package dev.notcacha.kitpvp.core.command;

import javax.inject.Inject;

import dev.notcacha.kitpvp.api.kit.Kit;
import dev.notcacha.kitpvp.api.kit.applier.KitApplier;
import dev.notcacha.kitpvp.api.repository.ModelRepository;
import dev.notcacha.kitpvp.core.command.kit.*;
import dev.notcacha.kitpvp.core.gui.kit.KitGUI;
import me.fixeddev.commandflow.annotated.CommandClass;
import me.fixeddev.commandflow.annotated.annotation.Command;
import me.fixeddev.commandflow.annotated.annotation.SubCommandClasses;
import me.fixeddev.commandflow.bukkit.annotation.Sender;
import me.yushust.message.MessageHandler;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

@Command(names = "kit")
@SubCommandClasses({
        KitCreateCommand.class,
        KitDeleteCommand.class,
        KitEditCommand.class,
        KitHelpCommand.class,
        KitSetArmorCommand.class,
        KitSetItemsCommand.class
})
public class KitCommand implements CommandClass {

    @Inject private MessageHandler messageHandler;
    @Inject private ModelRepository<Kit> kitModelRepository;
    @Inject private KitApplier kitApplier;
    @Inject private Plugin plugin;

    @Command(names = "")
    public boolean main(@Sender Player player) {

        new KitGUI(plugin, player, messageHandler.get(player, "kit.gui-title"), kitModelRepository, kitApplier, messageHandler);

        return true;
    }
}
