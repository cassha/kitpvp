package dev.notcacha.kitpvp.core.command.kit;

import javax.inject.Inject;
import dev.notcacha.kitpvp.api.kit.Kit;
import dev.notcacha.kitpvp.api.repository.ModelRepository;
import me.fixeddev.commandflow.annotated.CommandClass;
import me.fixeddev.commandflow.annotated.annotation.Command;
import me.fixeddev.commandflow.annotated.annotation.OptArg;
import me.fixeddev.commandflow.bukkit.annotation.Sender;
import me.yushust.message.MessageHandler;
import org.bukkit.entity.Player;

@Command(names = "delete", permission = "kitpvp.kit.delete")
public class KitDeleteCommand implements CommandClass {

    @Inject private MessageHandler messageHandler;
    @Inject private ModelRepository<Kit> modelRepository;

    @Command(names = "")
    public boolean delete(@Sender Player player, @OptArg String kitId) {
        if (kitId == null || kitId.trim().isEmpty()) {
            messageHandler.send(player, "kit.delete.usage");

            return true;
        }

        modelRepository.delete(kitId).callback(callback -> {
            if (!callback.getResponse().get()) {
                messageHandler.sendReplacing(player, "kit.not-exists", "%kit_id%", kitId);

                return;
            }

            messageHandler.sendReplacing(player, "kit.delete.success", "%kit_id%", kitId);
        });

        return true;
    }
}
