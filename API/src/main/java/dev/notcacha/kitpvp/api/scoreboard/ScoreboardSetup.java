package dev.notcacha.kitpvp.api.scoreboard;

import org.bukkit.entity.Player;

public interface ScoreboardSetup {

    /**
     * Setup the scoreboard from player.
     *
     * @param player has been set scoreboard.
     */

    void setup(Player player);

    /**
     * Remove the scoreboard.
     * @param player has been removed scoreboard.
     */

    void remove(Player player);
}
