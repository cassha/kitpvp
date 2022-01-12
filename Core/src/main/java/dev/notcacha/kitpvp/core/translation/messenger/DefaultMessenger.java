package dev.notcacha.kitpvp.core.translation.messenger;

import dev.notcacha.kitpvp.core.util.PlaceholderAPIUtil;
import me.yushust.message.send.MessageSender;
import org.bukkit.entity.Player;

public class DefaultMessenger implements MessageSender<Player> {

    public static final String PLACEHOLDER_MODE = "placeholder_api";

    @Override
    public void send(Player player, String mode, String message) {
        if (mode.equals(PLACEHOLDER_MODE)) {
            message = PlaceholderAPIUtil.detectAndApply(player, message);
        }

        player.sendMessage(message);
    }
}
