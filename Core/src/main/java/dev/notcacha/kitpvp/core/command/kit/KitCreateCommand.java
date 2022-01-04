package dev.notcacha.kitpvp.core.command.kit;

import javax.inject.Inject;
import dev.notcacha.kitpvp.api.cache.ObjectCache;
import dev.notcacha.kitpvp.api.kit.Kit;
import dev.notcacha.kitpvp.api.message.MessageHandler;
import dev.notcacha.kitpvp.api.repository.ModelRepository;
import dev.notcacha.kitpvp.core.item.SerializableItemBuilder;
import dev.notcacha.kitpvp.api.kit.DefaultKit;
import me.fixeddev.commandflow.annotated.CommandClass;
import me.fixeddev.commandflow.annotated.annotation.Command;
import me.fixeddev.commandflow.annotated.annotation.OptArg;
import me.fixeddev.commandflow.bukkit.annotation.Sender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;

@Command(names = {"create", "c", "add"}, permission = "kitpvp.kit.create")
public class KitCreateCommand implements CommandClass {

    @Inject private MessageHandler messageHandler;
    @Inject private ObjectCache<Kit> kitObjectCache;
    @Inject private ModelRepository<Kit> modelRepository;

    @Command(names = "")
    public boolean create(@Sender Player player, @OptArg String kitId) {
        if (kitId.trim().isEmpty()) {
            player.sendMessage(
                    messageHandler.getMessage(
                            "kit.create.usage"
                    )
            );

            return false;
        }

        modelRepository.findOne(kitId).callback(callback -> {

            if (callback.getResponse().isPresent()) {
                player.sendMessage(messageHandler.getMessage("kit.exists").replace("%kit_name%", kitId));
                return;
            }

            Kit kit = new DefaultKit(kitId, kitId, new ArrayList<>(), SerializableItemBuilder.newBuilder()
                    .setMaterial("STONE")
                    .setDisplayName(
                            "&9Example stone."
                    )
                    .build()
                    , new HashMap<>(), new HashMap<>());

            kitObjectCache.addObject(kit);

            player.sendMessage(messageHandler.getMessage("kit.create.success").replace("%kit_name%", kitId));
        });


        return true;
    }
}
