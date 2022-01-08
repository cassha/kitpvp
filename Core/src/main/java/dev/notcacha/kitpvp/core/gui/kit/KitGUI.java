package dev.notcacha.kitpvp.core.gui.kit;

import dev.notcacha.kitpvp.api.kit.Kit;
import dev.notcacha.kitpvp.api.kit.applier.KitApplier;
import dev.notcacha.kitpvp.api.message.MessageHandler;
import dev.notcacha.kitpvp.api.repository.ModelRepository;
import dev.notcacha.kitpvp.api.util.Colorize;
import me.patothebest.guiframework.gui.inventory.GUIPage;
import me.patothebest.guiframework.gui.inventory.button.SimpleButton;
import me.patothebest.guiframework.itemstack.ItemStackBuilder;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.util.Optional;
import java.util.Set;

public class KitGUI extends GUIPage {

    private final ModelRepository<Kit> kitModelRepository;
    private final KitApplier kitApplier;
    private final MessageHandler messageHandler;

    public KitGUI(Plugin plugin, Player player, String rawName, ModelRepository<Kit> kitModelRepository, KitApplier kitApplier, MessageHandler messageHandler) {
        super(plugin, player, rawName, 45);
        this.kitModelRepository = kitModelRepository;
        this.kitApplier = kitApplier;
        this.messageHandler = messageHandler;

        build();
    }

    @Override
    public void buildPage() {
        kitModelRepository.findAll().callback(callback -> {
            Optional<Set<Kit>> callbackResponse = callback.getResponse();

            if (callbackResponse.isPresent()) {
                Set<Kit> kitSet = callbackResponse.get();

                if (kitSet.size() > 0) {

                    kitSet.forEach(kit -> {
                        ItemStackBuilder item = new ItemStackBuilder(kit.getIcon().toItemStack());

                        item.name(Colorize.colorize(kit.getDisplayName()));

                        item.lore(Colorize.colorize(kit.getDescription()));

                        addButton(new SimpleButton(item).onClick(() -> {
                            if (player.hasPermission("kitpvp.kit." + kit.getId())) {

                                kitApplier.apply(player, kit);
                                player.sendMessage(messageHandler.getMessage("kit.apply.success").replace("%kit_id%", kit.getId()));
                            } else {

                                player.sendMessage(messageHandler.getMessage("kit.apply.error").replace("%kit_id%", kit.getId()));
                            }

                            player.closeInventory();
                        }));
                    });
                }
            }

        });
    }

}
