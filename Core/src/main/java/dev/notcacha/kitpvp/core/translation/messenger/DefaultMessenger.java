package dev.notcacha.kitpvp.core.translation.messenger;

import dev.notcacha.kitpvp.core.util.PlaceholderAPIUtil;
import me.yushust.message.send.MessageSender;
import org.bukkit.entity.Player;

public class DefaultMessenger implements MessageSender<Player> {

    @Override
    public void send(Player player, String mode, String message) {
        if (mode.equals("placeholder_api")) {
            message = PlaceholderAPIUtil.detectAndApply(player, message);
        }

        player.sendMessage(message);
    }
}
