package dev.notcacha.kitpvp.core.tag.util;

import dev.notcacha.kitpvp.api.tag.Tag;
import dev.notcacha.kitpvp.api.tag.applier.TagApplier;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

public class CoreTagApplier implements TagApplier {

    @Override
    public void apply(Player player, Tag tag) {
        Scoreboard scoreboard = player.getScoreboard() != null ? player.getScoreboard() : Bukkit.getScoreboardManager().getMainScoreboard();

        Team team = scoreboard.getTeam(player.getName());

        if (team == null) {
            team = scoreboard.registerNewTeam(player.getName());
        }

        team.setPrefix(ChatColor.translateAlternateColorCodes('&', tag.getPrefix() + " "));
        team.setSuffix(ChatColor.translateAlternateColorCodes('&', " " + tag.getSuffix()));

        team.addEntry(player.getName());
    }

}
