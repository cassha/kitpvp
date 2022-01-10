package dev.notcacha.kitpvp.core.gui.kit;

import dev.notcacha.kitpvp.api.item.SerializableItem;
import dev.notcacha.kitpvp.api.kit.Kit;
import me.patothebest.guiframework.gui.inventory.GUIPage;
import me.patothebest.guiframework.gui.inventory.button.SimpleButton;
import me.patothebest.guiframework.itemstack.ItemStackBuilder;
import me.yushust.message.MessageHandler;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

public class KitEditorIconGUI extends GUIPage {

    private final MessageHandler messageHandler;
    private final Kit kit;

    public KitEditorIconGUI(Plugin plugin, Player player, String rawName, MessageHandler messageHandler, Kit kit) {
        super(plugin, player, rawName, 9);
        this.messageHandler = messageHandler;
        this.kit = kit;

        this.blockInventoryMovement = false;

        build();
    }

    @Override
    public void buildPage() {

        for (int i = 0; i < 9 ; i++) {
            if (!(i == 4)) {
                addButton(
                        new SimpleButton(
                                new ItemStackBuilder(new ItemStack(Material.STAINED_GLASS, 1, (short) 15)).name("&1")
                        ),
                        i
                );
            }
        }


    }

    @EventHandler
    public void onPlayerCloseInventory(InventoryCloseEvent event) {
        if (overrideClose) {
            onInventoryCloseOverride();
            return;
        }

        Player player = (Player) event.getPlayer();

        if (!this.player.getOpenInventory().getTitle().equalsIgnoreCase(name)) {
            return;
        }

        if (this.player.getName().equalsIgnoreCase(player.getName())) {
            destroy();
            destroyInternal();

            ItemStack item = event.getInventory().getItem(4);

            if (item != null) {
                kit.setIcon(SerializableItem.fromItemStack(item));

                messageHandler.sendReplacing(player, String.format(KitEditorGUI.PATH, "icon", "change"), "%kit_id%", kit.getId());
            }
        }
    }
}
