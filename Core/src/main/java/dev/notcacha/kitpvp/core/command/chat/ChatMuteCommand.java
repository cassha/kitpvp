package dev.notcacha.kitpvp.core.command.chat;

import dev.notcacha.kitpvp.api.chat.ChatManager;
import dev.notcacha.kitpvp.api.message.MessageHandler;
import dev.notcacha.kitpvp.core.util.PlaceholderAPIUtil;
import me.fixeddev.commandflow.annotated.CommandClass;
import me.fixeddev.commandflow.annotated.annotation.Command;
import me.fixeddev.commandflow.bukkit.annotation.Sender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import javax.inject.Inject;

@Command(names = "mute", permission = "kitpvp.chat.mute")
public class ChatMuteCommand implements CommandClass {

    @Inject private MessageHandler messageHandler;
    @Inject private ChatManager chatManager;
    @Inject private Plugin plugin;

    @Command(names = "")
    public boolean mute(@Sender Player player) {
        if (chatManager.isMuted()) {
            chatManager.setMuted(false);

            plugin.getServer().getOnlinePlayers().forEach(p -> p.sendMessage(PlaceholderAPIUtil.detectAndApply(player, messageHandler.getMessage("chat.muted.unmute").replace("%player_name%", player.getName()))));
            return true;
        }

        chatManager.setMuted(true);

        plugin.getServer().getOnlinePlayers().forEach(p -> p.sendMessage(PlaceholderAPIUtil.detectAndApply(player, messageHandler.getMessage("chat.muted.mute").replace("%player_name%", player.getName()))));
        return true;
    }
}
