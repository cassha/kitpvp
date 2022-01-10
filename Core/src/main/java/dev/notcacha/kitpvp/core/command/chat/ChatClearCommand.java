package dev.notcacha.kitpvp.core.command.chat;

import me.fixeddev.commandflow.annotated.CommandClass;
import me.fixeddev.commandflow.annotated.annotation.Command;
import me.fixeddev.commandflow.bukkit.annotation.Sender;
import me.yushust.message.MessageHandler;
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

            for (int i = 0; i < 120; i ++) {
                onlinePlayer.sendMessage("");
            }

            messageHandler.sendReplacingIn(
                    player,
                    "placeholder_api",
                    "chat.clear",
                    "%player_name%",
                    player.getName()
            );

        });
        return true;
    }

}
