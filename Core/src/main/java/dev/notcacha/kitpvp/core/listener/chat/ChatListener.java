package dev.notcacha.kitpvp.core.listener.chat;

import dev.notcacha.kitpvp.api.chat.ChatManager;
import dev.notcacha.kitpvp.api.chat.formatter.ChatFormatter;
import dev.notcacha.kitpvp.api.message.MessageHandler;
import dev.notcacha.kitpvp.core.util.PlaceholderAPIUtil;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import javax.inject.Inject;

public class ChatListener implements Listener {

    @Inject private MessageHandler messageHandler;
    @Inject private ChatFormatter formatter;
    @Inject private ChatManager chatManager;

    @EventHandler
    public void onAsyncPlayerChatEvent(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();

        if (chatManager.isMuted()) {
            if (player.hasPermission("kitpvp.chat.muted.bypass")) {
                return;
            }

            player.sendMessage(messageHandler.getMessage("chat.muted.error-talk"));
            return;
        }

        String message = event.getMessage();
        String format = formatter.format(player, PlaceholderAPIUtil.detectAndApply(player, messageHandler.getMessage("chat.format")),message);
        event.setFormat("%2$s");
        event.setMessage(format);
    }
}
