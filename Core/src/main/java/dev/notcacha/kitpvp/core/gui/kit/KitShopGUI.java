package dev.notcacha.kitpvp.core.gui.kit;

import dev.notcacha.kitpvp.api.kit.Kit;
import dev.notcacha.kitpvp.api.repository.ModelRepository;
import dev.notcacha.kitpvp.api.util.Colorize;
import dev.notcacha.kitpvp.core.hook.VaultHook;
import dev.notcacha.kitpvp.core.translation.messenger.DefaultMessenger;
import me.patothebest.guiframework.gui.inventory.GUIPage;
import me.patothebest.guiframework.gui.inventory.button.SimpleButton;
import me.patothebest.guiframework.itemstack.ItemStackBuilder;
import me.yushust.message.MessageHandler;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.util.Optional;
import java.util.Set;

public class KitShopGUI extends GUIPage {

    private final MessageHandler messageHandler;
    private final ModelRepository<Kit> kitModelRepository;
    private final VaultHook vaultHook;

    public KitShopGUI(Plugin plugin, Player player, String rawName, MessageHandler messageHandler, ModelRepository<Kit> kitModelRepository, VaultHook vaultHook) {
        super(plugin, player, rawName, 45);
        this.messageHandler = messageHandler;
        this.kitModelRepository = kitModelRepository;
        this.vaultHook = vaultHook;

        build();
    }

    @Override
    public void buildPage() {
        if (!vaultHook.isEnabled()) {
            messageHandler.send(player, "kit.shop.already-all-kits");

            player.closeInventory();
            return;
        }

        kitModelRepository.findAll().callback(callback -> {

            Optional<Set<Kit>> callbackResponse = callback.getResponse();

            if (!callbackResponse.isPresent()) {
                return;
            }

            Set<Kit> set = callbackResponse.get();

            if (set.size() == 0) {
                messageHandler.send(player, "kit.shop.already-all-kits");

                player.closeInventory();
                return;
            }

            set.forEach(kit -> {
                if (!player.hasPermission("kitpvp.kit." + kit.getId())) {
                    ItemStackBuilder item = new ItemStackBuilder(kit.getIcon().toItemStack());

                    item.name(Colorize.colorize(kit.getDisplayName()));

                    item.lore(Colorize.colorize(kit.getDescription()));

                    addButton(buildButton(item, kit));
                }

            });

        });

    }

    private SimpleButton buildButton(ItemStackBuilder item, Kit kit) {
        return new SimpleButton(item).onClick(() -> {
            double playerBalance = vaultHook.getEconomy().getBalance(player);
            if (playerBalance < kit.getCost()) {
                messageHandler.sendReplacingIn(
                        player,
                        DefaultMessenger.PLACEHOLDER_MODE,
                        "kit.shop.insufficient-balance",
                        "%kit_id%", kit.getId(),
                        "%kit_cost%", kit.getCost()
                );

                player.closeInventory();
                return;
            }

            vaultHook.getEconomy().withdrawPlayer(player, kit.getCost());

            plugin.getServer().dispatchCommand(
                    plugin.getServer().getConsoleSender(),
                    plugin.getConfig().getString("kit-buy-command")
                            .replace("%player_name%", player.getName())
                            .replace("%permission%", "kitpvp.kit." + kit.getId())
            );

            messageHandler.sendReplacingIn(
                    player,
                    DefaultMessenger.PLACEHOLDER_MODE,
                    "kit.shop.message",
                    "%kit_id%", kit.getId()
            );

            player.closeInventory();
        });
    }
}
