package dev.notcacha.kitpvp.core.service;

import dev.notcacha.kitpvp.api.scoreboard.ScoreboardSetup;
import dev.notcacha.kitpvp.api.service.Service;
import dev.notcacha.kitpvp.core.scheduler.ScoreboardScheduler;
import org.bukkit.plugin.Plugin;

import javax.inject.Inject;

public class ScoreboardService implements Service {

    @Inject private Plugin plugin;
    @Inject private ScoreboardSetup scoreboardSetup;

    @Override
    public void start() {
        plugin.getServer().getScheduler().runTaskTimer(plugin, new ScoreboardScheduler(plugin, scoreboardSetup), 40, 20);
    }
}
