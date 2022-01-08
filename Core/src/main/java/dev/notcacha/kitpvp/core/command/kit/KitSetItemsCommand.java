package dev.notcacha.kitpvp.core.command.kit;

import dev.notcacha.kitpvp.api.item.SerializableItem;
import dev.notcacha.kitpvp.api.kit.Kit;
import dev.notcacha.kitpvp.api.message.MessageHandler;
import dev.notcacha.kitpvp.api.repository.ModelRepository;
import dev.notcacha.kitpvp.core.util.PlayerInventoryUtil;
import me.fixeddev.commandflow.annotated.CommandClass;
import me.fixeddev.commandflow.annotated.annotation.Command;
import me.fixeddev.commandflow.annotated.annotation.OptArg;
import me.fixeddev.commandflow.bukkit.annotation.Sender;
import org.bukkit.entity.Player;

import javax.inject.Inject;
import java.util.Map;
import java.util.Optional;

@Command(names = "setitems", permission = "kitpvp.kit.edit.setitems")
public class KitSetItemsCommand implements CommandClass {

    @Inject private MessageHandler messageHandler;
    @Inject private ModelRepository<Kit> kitModelRepository;

    @Command(names = "")
    public boolean setItems(@Sender Player player, @OptArg String kitId) {
        if (kitId == null || kitId.trim().isEmpty()) {
            player.sendMessage(messageHandler.getMessage("kit.editor.set.items.usage"));
            return true;
        }

        kitModelRepository.findOne(kitId).callback(callback -> {
            Optional<Kit> callbackResponse = callback.getResponse();

            if (!callbackResponse.isPresent()) {
                player.sendMessage(messageHandler.getMessage("kit.editor.set.items.error").replace("%kit_id%", kitId));
                return;
            }

            Map<Integer, SerializableItem> map = PlayerInventoryUtil.getItems(player.getInventory());

            if (map.isEmpty()) {
                player.sendMessage(messageHandler.getMessage("kit.editor.set.items.empty-items"));

                return;
            }

            Kit kit = callbackResponse.get();

            kit.setInventoryContents(map);

            kitModelRepository.save(kit, true);

            player.sendMessage(messageHandler.getMessage("kit.editor.set.items.success").replace("%kit_id%", kitId));
        });
        return true;
    }
}
