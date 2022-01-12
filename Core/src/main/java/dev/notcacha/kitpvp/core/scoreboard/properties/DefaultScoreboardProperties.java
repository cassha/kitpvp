package dev.notcacha.kitpvp.core.scoreboard.properties;

import dev.notcacha.kitpvp.api.scoreboard.properties.ScoreboardProperties;
import dev.notcacha.kitpvp.api.util.Colorize;
import dev.notcacha.kitpvp.core.util.PlaceholderAPIUtil;
import me.yushust.message.MessageHandler;
import org.bukkit.entity.Player;

import javax.inject.Inject;
import java.util.List;

public class DefaultScoreboardProperties implements ScoreboardProperties {

    @Inject private MessageHandler messageHandler;

    @Override
    public String getTitle(Player player) {
        return Colorize.colorize(messageHandler.get(player, "scoreboard.title"));
    }

    @Override
    public List<String> getLines(Player player) {
        return Colorize.colorize(
                PlaceholderAPIUtil.detectAndApply(player, messageHandler.getMany(player, "scoreboard.lines"))
        );
    }
}
