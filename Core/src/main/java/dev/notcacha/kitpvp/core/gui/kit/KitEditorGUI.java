package dev.notcacha.kitpvp.core.gui.kit;

import dev.notcacha.kitpvp.api.kit.Kit;
import dev.notcacha.kitpvp.api.message.MessageHandler;
import me.patothebest.guiframework.gui.anvil.AnvilSlot;
import me.patothebest.guiframework.gui.inventory.GUIPage;
import me.patothebest.guiframework.gui.inventory.button.AnvilButton;
import me.patothebest.guiframework.gui.inventory.button.SimpleButton;
import me.patothebest.guiframework.itemstack.ItemStackBuilder;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class KitEditorGUI extends GUIPage {

    protected static final String PATH = "kit.editor.items.%s.%s";

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

        addButton(buildAnvilButton(buildItem(Material.PAPER, "display_name")), 0);
        addButton(buildButton("icon", buildItem(Material.BEACON, "icon")), 4);
        addButton(buildButton("description", buildItem(Material.PAPER, "description")), 8);
    }

    private ItemStackBuilder buildItem(Material material, String type) {
        return new ItemStackBuilder(material)
                .name(messageHandler.getMessage(String.format(PATH, type, "name")))
                .lore(messageHandler.getMessages(String.format(PATH, type, "lore")));
    }

    private SimpleButton buildButton(String type, ItemStackBuilder item) {
        SimpleButton button = new SimpleButton(item);

        button.onClick(() -> {
            switch (type) {

                case "icon": {
                    player.closeInventory();

                    new KitEditorIconGUI(
                            getPlugin(),
                            getPlayer(),
                            messageHandler.getMessage(String.format(PATH, "icon", "name")).replace("%kit_id%", kit.getId()),
                            messageHandler,
                            kit
                    );
                    break;
                }

                case "description": {
                    player.closeInventory();

                    new KitEditorDescriptionGUI(
                            getPlugin(),
                            getPlayer(),
                            messageHandler.getMessage(
                                    String.format(PATH, "description", "name")
                            ).replace("%kit_id%", kit.getId()),
                            messageHandler,
                            kit
                    );
                    break;
                }
            }
        });

        return button;
    }

    private AnvilButton buildAnvilButton(ItemStackBuilder item) {
        AnvilButton anvilButton = new AnvilButton(item);

        anvilButton.onConfirm((event) -> {

            String output = event.getOutput();

            kit.setDisplayName(output);

            player.sendMessage(
                    messageHandler.getMessage(
                            String.format(PATH, "display_name", "message")
                    ).replace(
                            "%kit_id%",
                            kit.getId()
                    ).replace(
                            "%display_name%",
                            output
                    )
            );

            event.setWillClose(true);

        }).onCancel(() -> {})
                .useSlot(AnvilSlot.INPUT_LEFT, new ItemStackBuilder(item).name(ChatColor.stripColor(item.getItemMeta().getDisplayName())));

        return anvilButton;
    }
}
