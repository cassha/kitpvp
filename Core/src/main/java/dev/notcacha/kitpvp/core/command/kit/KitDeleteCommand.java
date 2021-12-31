package dev.notcacha.kitpvp.core.command.kit;

import javax.inject.Inject;
import dev.notcacha.kitpvp.api.cache.ObjectCache;
import dev.notcacha.kitpvp.api.kit.Kit;
import dev.notcacha.kitpvp.api.message.MessageHandler;
import dev.notcacha.kitpvp.api.model.processor.ModelDeleteProcessor;
import dev.notcacha.kitpvp.core.kit.DefaultKit;
import me.fixeddev.commandflow.annotated.CommandClass;
import me.fixeddev.commandflow.annotated.annotation.Command;
import me.fixeddev.commandflow.annotated.annotation.OptArg;
import me.fixeddev.commandflow.bukkit.annotation.Sender;
import org.bukkit.entity.Player;

@Command(names = "delete", permission = "kitpvp.kit.delete")
public class KitDeleteCommand implements CommandClass {

    @Inject private MessageHandler messageHandler;
    @Inject private ObjectCache<Kit> kitObjectCache;
    @Inject private ModelDeleteProcessor<Kit> modelDeleteProcessor;

    @Command(names = "")
    public boolean delete(@Sender Player player, @OptArg String kitId) {

        if (kitId.trim().isEmpty()) {
            player.sendMessage(messageHandler.getMessage("kit.delete.usage"));

            return true;
        }

        modelDeleteProcessor.deleteAsync(new DefaultKit(kitId, null, null, null, null,null)).callback(callback -> {

            if (!callback.getResponse().get() || !kitObjectCache.ifPresent(kitId)) {
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
