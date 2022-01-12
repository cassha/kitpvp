package dev.notcacha.kitpvp.core.scoreboard;

import dev.notcacha.kitpvp.api.scoreboard.ScoreboardSetup;
import dev.notcacha.kitpvp.api.scoreboard.properties.ScoreboardProperties;
import dev.notcacha.scoreboard.api.ScoreboardManager;
import dev.notcacha.scoreboard.api.board.PlayerBoard;
import org.bukkit.entity.Player;

import javax.inject.Inject;
import java.util.List;

public class DefaultScoreboardSetup implements ScoreboardSetup {

    @Inject private ScoreboardProperties scoreboardProperties;
    @Inject private ScoreboardManager scoreboardManager;

    @Override
    public void setup(Player player) {
        PlayerBoard board = scoreboardManager.getBoard(player);

        if (board == null) {
            board = scoreboardManager.createBoard(player, scoreboardProperties.getTitle(player));
        }

        List<String> lines = scoreboardProperties.getLines(player);

        for (int i = 0; i < lines.size(); i++) {
            board.set(
                    lines.get(i), lines.size() - (i)
            );
        }
    }

    @Override
    public void remove(Player player) {
        scoreboardManager.deleteBoard(player);
        scoreboardManager.removeBoard(player);
    }
}
