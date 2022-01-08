package dev.notcacha.kitpvp.core.gui.kit;

import dev.notcacha.kitpvp.api.kit.Kit;
import dev.notcacha.kitpvp.api.message.MessageHandler;
import me.patothebest.guiframework.gui.anvil.AnvilSlot;
import me.patothebest.guiframework.gui.inventory.GUIPage;
import me.patothebest.guiframework.gui.inventory.button.AnvilButton;
import me.patothebest.guiframework.gui.inventory.button.SimpleButton;
import me.patothebest.guiframework.itemstack.ItemStackBuilder;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;

public class KitEditorDescriptionGUI extends GUIPage {

    private static final String PATH = "kit.editor.items.description.sub-inv.%s.%s";

    private final MessageHandler messageHandler;
    private final Kit kit;

    public KitEditorDescriptionGUI(Plugin plugin, Player player, String rawName, MessageHandler messageHandler, Kit kit) {
        super(plugin, player, rawName, 9);
        this.messageHandler = messageHandler;
        this.kit = kit;

        build();
    }

    @Override
    public void buildPage() {
        addButton(
                buildAnvilButton(
                        "add_line",
                        buildItem(
                                "add_line"
                        )
                ),
                0
        );

        addButton(
                buildButton(
                        buildItem(
                                "clear"
                        )
                ),
                4
        );

        addButton(
                buildAnvilButton(
                        "quit_line",
                        buildItem(
                                "quit_line"
                        )
                ),
                8
        );
    }

    private AnvilButton buildAnvilButton(String type, ItemStackBuilder item) {
        return new AnvilButton(item).onConfirm(event -> {

            player.closeInventory();

            String output = event.getOutput();

            switch (type) {

                case "add_line": {

                    kit.getDescription().add(output);

                    player.sendMessage(
                            messageHandler.getMessage(
                                    String.format(
                                            PATH,
                                            type,
                                            "message"
                                    )
                            ).replace(
                                    "%kit_id%",
                                    kit.getId()
                            )
                    );
                    break;
                }

                case "quit_line": {

                    int line;

                    try {
                        line = Integer.parseInt(output);
                    } catch (NumberFormatException e) {
                        player.sendMessage(
                                messageHandler.getMessage(
                                        String.format(
                                                PATH,
                                                type,
                                                "invalid-number"
                                        )
                                ).replace(
                                        "%kit_id%",
                                        kit.getId()
                                )
                        );

                        return;
                    }

                    if (kit.getDescription().get(line) == null) {
                        //The line not exists in list. xd

                        player.sendMessage(
                                messageHandler.getMessage(
                                        String.format(
                                                PATH,
                                                type,
                                                "error"
                                        )
                                ).replace(
                                        "%kit_id%",
                                        kit.getId()
                                ).replace(
                                        "%line%",
                                        String.valueOf(line)
                                )
                        );

                        return;
                    }

                    kit.getDescription().remove(line);

                    player.sendMessage(
                            messageHandler.getMessage(
                                    String.format(
                                            PATH,
                                            type,
                                            "message"
                                    )
                            ).replace(
                                    "%kit_id%",
                                    kit.getId()
                            )
                    );
                    break;
                }
            }

        }).onCancel(() -> {}).useSlot(
                AnvilSlot.INPUT_LEFT,
                new ItemStackBuilder(Material.PAPER).name("")
        );
    }

    private SimpleButton buildButton(ItemStackBuilder item) {
        return new SimpleButton(item).onClick(() -> {

            kit.setDescription(new ArrayList<>());

            player.closeInventory();

            player.sendMessage(
                    messageHandler.getMessage(
                            String.format(
                                    PATH,
                                    "clear",
                                    "message"
                            )
                    ).replace(
                            "%kit_id%",
                            kit.getId()
                    )
            );
        });
    }

    private ItemStackBuilder buildItem(String type) {
        return new ItemStackBuilder(Material.PAPER)
                .name(messageHandler.getMessage(String.format(PATH, type, "name")))
                .lore(messageHandler.getMessages(String.format(PATH, type, "lore")));
    }
}
