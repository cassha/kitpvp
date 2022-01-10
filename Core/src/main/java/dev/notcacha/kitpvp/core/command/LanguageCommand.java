package dev.notcacha.kitpvp.core.command;

import dev.notcacha.kitpvp.api.repository.ModelRepository;
import dev.notcacha.kitpvp.api.user.User;
import dev.notcacha.kitpvp.core.gui.LanguageOptionsGUI;
import me.fixeddev.commandflow.annotated.CommandClass;
import me.fixeddev.commandflow.annotated.annotation.Command;
import me.fixeddev.commandflow.bukkit.annotation.Sender;
import me.yushust.message.MessageHandler;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import javax.inject.Inject;
import java.util.Optional;
import java.util.logging.Level;

@Command(names = "language", permission = "kitpvp.language")
public class LanguageCommand implements CommandClass {

    @Inject private MessageHandler messageHandler;
    @Inject private Plugin plugin;
    @Inject private ModelRepository<User> userModelRepository;

    @Command(names = "")
    public boolean language(@Sender Player player) {

        userModelRepository.findOne(player.getUniqueId().toString()).callback(callback -> {
            Optional<User> callbackResponse = callback.getResponse();

            if (!callbackResponse.isPresent()) {
                plugin.getLogger().log(Level.SEVERE, "The data of user " + player.getName() + " not found.");

                return;
            }

            User user = callbackResponse.get();

            new LanguageOptionsGUI(
                    plugin,
                    player,
                    messageHandler.get(
                            player,
                            "language.gui.title"
                    ),
                    messageHandler,
                    user
            );
        });

        return true;
    }
}
