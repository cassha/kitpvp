package dev.notcacha.kitpvp.core.command.kit;

import javax.inject.Inject;
import dev.notcacha.kitpvp.api.kit.Kit;
import dev.notcacha.kitpvp.api.repository.ModelRepository;
import dev.notcacha.kitpvp.core.item.SerializableItemBuilder;
import dev.notcacha.kitpvp.api.kit.DefaultKit;
import me.fixeddev.commandflow.annotated.CommandClass;
import me.fixeddev.commandflow.annotated.annotation.Command;
import me.fixeddev.commandflow.annotated.annotation.OptArg;
import me.fixeddev.commandflow.bukkit.annotation.Sender;
import me.yushust.message.MessageHandler;
import org.bukkit.entity.Player;

import java.util.*;

@Command(names = {"create", "c", "add"}, permission = "kitpvp.kit.create")
public class KitCreateCommand implements CommandClass {

    @Inject private MessageHandler messageHandler;
    @Inject private ModelRepository<Kit> modelRepository;

    @Command(names = "")
    public boolean create(@Sender Player player, @OptArg String kitId) {
        if (kitId == null || kitId.trim().isEmpty()) {
            messageHandler.send(player, "kit.create.usage");
            return false;
        }

        modelRepository.findOne(kitId).callback(callback -> {

            if (callback.getResponse().isPresent()) {
                messageHandler.sendReplacing(player, "kit.exists", "%kit_id%", kitId);
                return;
            }

            Kit kit = new DefaultKit(kitId, kitId, new ArrayList<>(), SerializableItemBuilder.newBuilder()
                    .setMaterial("STONE")
                    .setDisplayName(
                            "&9Example stone."
                    ).setDescription(
                            Collections.singletonList("&7Example list of kit &f: &9" + kitId)
                    )
                    .build()
                    , new HashMap<>(), new HashMap<>());

            modelRepository.save(kit, true);

            messageHandler.sendReplacing(player, "kit.create.success", "%kit_id%", kitId);
        });


        return true;
    }
}
