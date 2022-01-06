package dev.notcacha.kitpvp.core.command.chat;

import dev.notcacha.kitpvp.api.message.MessageHandler;
import dev.notcacha.kitpvp.core.util.PlaceholderAPIUtil;
import me.fixeddev.commandflow.annotated.CommandClass;
import me.fixeddev.commandflow.annotated.annotation.Command;
import me.fixeddev.commandflow.bukkit.annotation.Sender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import javax.inject.Inject;

@Command(names = "clear", permission = "kit.chat.clear")
public class ChatClearCommand implements CommandClass {

    @Inject private MessageHandler messageHandler;
    @Inject private Plugin plugin;

    @Command(names = "")
    public boolean clear(@Sender Player player) {

        plugin.getServer().getOnlinePlayers().forEach(onlinePlayer -> {

            for (int i = 0; i < 90; i ++) {
                onlinePlayer.sendMessage("");
            }

            onlinePlayer.sendMessage(PlaceholderAPIUtil.detectAndApply(player, messageHandler.getMessage("chat.clear").replace("%player_name%", player.getName())));
        });
        return true;
    }

}
