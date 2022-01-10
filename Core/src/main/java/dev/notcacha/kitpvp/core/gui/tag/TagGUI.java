package dev.notcacha.kitpvp.core.gui.tag;

import dev.notcacha.kitpvp.api.repository.ModelRepository;
import dev.notcacha.kitpvp.api.tag.Tag;
import dev.notcacha.kitpvp.api.tag.applier.TagApplier;
import me.patothebest.guiframework.gui.inventory.GUIPage;
import me.patothebest.guiframework.gui.inventory.button.SimpleButton;
import me.patothebest.guiframework.itemstack.ItemStackBuilder;
import me.yushust.message.MessageHandler;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public class TagGUI extends GUIPage {

    private final TagApplier tagApplier;
    private final MessageHandler messageHandler;
    private final ModelRepository<Tag> modelRepository;

    public TagGUI(Plugin plugin, Player player, String title, TagApplier tagApplier, MessageHandler messageHandler, ModelRepository<Tag> modelRepository) {
        super(plugin, player, title, 45);
        this.tagApplier = tagApplier;
        this.messageHandler = messageHandler;
        this.modelRepository = modelRepository;

        build();
    }

    @Override
    public void buildPage() {

        ItemStackBuilder item = new ItemStackBuilder(Material.NAME_TAG);

        modelRepository.findAll().callback(callback -> {
            Optional<Set<Tag>> callbackResponse = callback.getResponse();

            if (callbackResponse.isPresent()) {
                Set<Tag> tagSet = callbackResponse.get();

                if (tagSet.size() > 0) {

                    tagSet.forEach(tag -> {
                        item.name(tag.getListName());

                        List<String> lore = messageHandler.getMany(player, "tag.gui.description");
                        lore.replaceAll(text -> text.replace("%prefix%", tag.getPrefix()).replace("%suffix%", tag.getSuffix()));

                        item.lore(lore);

                        addButton(new SimpleButton(item).onClick(() -> {
                            if (player.hasPermission("kitpvp.tag." + tag.getId())) {

                                tagApplier.apply(player, tag);
                                messageHandler.sendReplacing(player, "tag.apply.success","%tag_id%", tag.getId());
                            } else {

                                messageHandler.sendReplacing(player, "tag.apply.error","%tag_id%", tag.getId());
                            }

                            player.closeInventory();
                        }));
                    });
                }
            }

        });

    }
}
