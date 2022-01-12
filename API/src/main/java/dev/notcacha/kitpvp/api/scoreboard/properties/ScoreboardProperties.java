package dev.notcacha.kitpvp.api.scoreboard.properties;

import org.bukkit.entity.Player;

import java.util.List;

public interface ScoreboardProperties {

    /**
     * @param player has been get language.
     * @return Return the title of scoreboard.
     */

    String getTitle(Player player);

    /**
     * @param player has been get language.
     * @return Return the lines of scoreboard.
     */

    List<String> getLines(Player player);
}
