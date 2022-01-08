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

@Command(names = "setarmor", permission = "kitpvp.kit.edit.setarmor")
public class KitSetArmorCommand implements CommandClass {

    @Inject private MessageHandler messageHandler;
    @Inject private ModelRepository<Kit> kitModelRepository;

    @Command(names = "")
    public boolean setArmor(@Sender Player player, @OptArg String kitId) {
        if (kitId == null || kitId.trim().isEmpty()) {
            player.sendMessage(messageHandler.getMessage("kit.editor.set.armor.usage"));
            return true;
        }

        kitModelRepository.findOne(kitId).callback(callback -> {
            Optional<Kit> callbackResponse = callback.getResponse();

            if (!callbackResponse.isPresent()) {
                player.sendMessage(messageHandler.getMessage("kit.editor.set.armor.error").replace("%kit_id%", kitId));
                return;
            }

            Map<Integer, SerializableItem> map = PlayerInventoryUtil.getArmor(player.getInventory());

            if (map.isEmpty()) {
                player.sendMessage(messageHandler.getMessage("kit.editor.set.armor.empty-armor"));

                return;
            }

            Kit kit = callbackResponse.get();

            kit.setArmorContents(map);

            kitModelRepository.save(kit, true);

            player.sendMessage(messageHandler.getMessage("kit.editor.set.armor.success").replace("%kit_id%", kitId));
        });

        return true;
    }
}
