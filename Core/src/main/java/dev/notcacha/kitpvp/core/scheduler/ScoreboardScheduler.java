package dev.notcacha.kitpvp.core.scheduler;

import dev.notcacha.kitpvp.api.scoreboard.ScoreboardSetup;

import org.bukkit.plugin.Plugin;

public class ScoreboardScheduler implements Runnable {

    private final Plugin plugin;
    private final ScoreboardSetup scoreboardSetup;

    public ScoreboardScheduler(Plugin plugin, ScoreboardSetup scoreboardSetup) {
        this.plugin = plugin;
        this.scoreboardSetup = scoreboardSetup;
    }

    @Override
    public void run() {
        plugin.getServer().getOnlinePlayers().forEach(onlinePlayer -> {
            scoreboardSetup.setup(onlinePlayer);
        });
    }

}
