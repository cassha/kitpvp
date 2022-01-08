package dev.notcacha.kitpvp.core.command.kit;

import javax.inject.Inject;
import dev.notcacha.kitpvp.api.kit.Kit;
import dev.notcacha.kitpvp.api.message.MessageHandler;
import dev.notcacha.kitpvp.api.repository.ModelRepository;
import me.fixeddev.commandflow.annotated.CommandClass;
import me.fixeddev.commandflow.annotated.annotation.Command;
import me.fixeddev.commandflow.annotated.annotation.OptArg;
import me.fixeddev.commandflow.bukkit.annotation.Sender;
import org.bukkit.entity.Player;

@Command(names = "delete", permission = "kitpvp.kit.delete")
public class KitDeleteCommand implements CommandClass {

    @Inject private MessageHandler messageHandler;
    @Inject private ModelRepository<Kit> modelRepository;

    @Command(names = "")
    public boolean delete(@Sender Player player, @OptArg String kitId) {
        if (kitId == null || kitId.trim().isEmpty()) {
            player.sendMessage(messageHandler.getMessage("kit.delete.usage"));

            return true;
        }

        modelRepository.delete(kitId).callback(callback -> {
            if (!callback.getResponse().get()) {
                player.sendMessage(
                        messageHandler.getMessage("kit.not-exists").replace("%kit_name%", kitId)
                );

                return;
            }

            player.sendMessage(
                    messageHandler.getMessage("kit.delete.success").replace("%kit_name%", kitId)
            );
        });

        return true;
    }
}
