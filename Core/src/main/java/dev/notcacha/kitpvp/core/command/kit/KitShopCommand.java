package dev.notcacha.kitpvp.core.command.kit;

import dev.notcacha.kitpvp.api.kit.Kit;
import dev.notcacha.kitpvp.api.repository.ModelRepository;
import dev.notcacha.kitpvp.core.gui.kit.KitShopGUI;
import dev.notcacha.kitpvp.core.hook.VaultHook;
import me.fixeddev.commandflow.annotated.CommandClass;
import me.fixeddev.commandflow.annotated.annotation.Command;
import me.fixeddev.commandflow.bukkit.annotation.Sender;
import me.yushust.message.MessageHandler;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import javax.inject.Inject;

@Command(names = {"shop", "buy"}, permission = "kitpvp.kit.shop")
public class KitShopCommand implements CommandClass {

    @Inject private MessageHandler messageHandler;
    @Inject private ModelRepository<Kit> kitModelRepository;
    @Inject private Plugin plugin;
    @Inject private VaultHook vaultHook;


    @Command(names = "")
    public boolean shop(@Sender Player player) {

        new KitShopGUI(plugin, player, messageHandler.get(player, "kit.shop.title"), messageHandler, kitModelRepository, vaultHook);

        return true;
    }
}
