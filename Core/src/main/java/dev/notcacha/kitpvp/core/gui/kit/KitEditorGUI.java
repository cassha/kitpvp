package dev.notcacha.kitpvp.core.gui.kit;

import dev.notcacha.kitpvp.api.kit.Kit;
import dev.notcacha.kitpvp.api.message.MessageHandler;
import dev.notcacha.kitpvp.core.kit.util.KitUtil;
import me.patothebest.guiframework.gui.inventory.GUIPage;
import me.patothebest.guiframework.gui.inventory.button.SimpleButton;
import me.patothebest.guiframework.itemstack.ItemStackBuilder;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class KitEditorGUI extends GUIPage {

    private final MessageHandler messageHandler;
    private final Kit kit;

    public KitEditorGUI(Plugin plugin, Player player, String rawName, MessageHandler messageHandler, Kit kit) {
        super(plugin, player, rawName, 9);
        this.messageHandler = messageHandler;
        this.kit = kit;

        build();
    }

    @Override
    public void buildPage() {

        addButton(buildButton("contents"));
        addButton(buildButton("decoration"));

    }

    private SimpleButton buildButton(String type) {
        return new SimpleButton(buildItem(type)).onClick(() -> {

            switch (type) {

                case "contents": {

                    kit.setInventoryContents(
                            KitUtil.editContents(player)
                    );

                    kit.setArmorContents(
                            KitUtil.editArmor(player)
                    );

                    player.sendMessage(messageHandler.getMessage("kit.editor.items.contents.messages.success").replace("%kit_id%", kit.getId()));
                    player.closeInventory();
                    return;
                }

                case "decoration": {

                    return;
                }

            }

            player.closeInventory();

        });
    }

    private ItemStackBuilder buildItem(String type) {
        return new ItemStackBuilder(type.equals("contents") ? Material.IRON_HELMET : Material.PAPER)
                .name(messageHandler.getMessage("kit.editor.items." + type + ".name"))
                .lore(messageHandler.getMessages("kit.editor.items." + type + ".lore"));
    }
}
