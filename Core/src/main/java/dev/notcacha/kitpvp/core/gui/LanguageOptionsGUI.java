package dev.notcacha.kitpvp.core.gui;

import dev.notcacha.kitpvp.api.user.User;
import me.patothebest.guiframework.gui.inventory.GUIPage;
import me.patothebest.guiframework.gui.inventory.button.SimpleButton;
import me.patothebest.guiframework.itemstack.ItemStackBuilder;
import me.yushust.message.MessageHandler;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.Nullable;

public class LanguageOptionsGUI extends GUIPage {

    private static final String PATH = "language.gui.items.%s.%s";

    private final MessageHandler messageHandler;
    private final User user;

    public LanguageOptionsGUI(Plugin plugin, Player player, String rawName, MessageHandler messageHandler, User user) {
        super(plugin, player, rawName, 9);

        this.messageHandler = messageHandler;
        this.user = user;

        build();
    }

    @Override
    public void buildPage() {

        addButton(
                buildButton(
                        buildItem(
                                "language-en"
                        ),
                        "en"
                ),
                0
        );

        addButton(
                buildButton(
                        buildItem(
                                "current-language"
                        ),
                        null
                ),
                4
        );

        addButton(
                buildButton(
                        buildItem(
                                "language-es"
                        ),
                        "es"
                ),
                8
        );

    }

    private ItemStackBuilder buildItem(String type) {
        return new ItemStackBuilder(Material.PAPER)
                .name(messageHandler.get(player, String.format(PATH, type, "name")))
                .lore(messageHandler.replacingMany(player, String.format(PATH, type, "lore"), "%player_language%", user.getLanguage()));
    }

    private SimpleButton buildButton(ItemStackBuilder item, @Nullable String languageIdentifier) {
        return new SimpleButton(item).onClick(() -> {

            if (languageIdentifier == null) {
                return;
            }

            user.setLanguage(languageIdentifier);

            messageHandler.sendReplacing(player, "language.gui.message", "%language%", languageIdentifier);

            player.closeInventory();
        });
    }
}
