package dev.notcacha.kitpvp.core.command;

import me.fixeddev.commandflow.annotated.CommandClass;
import me.fixeddev.commandflow.annotated.annotation.Command;
import me.fixeddev.commandflow.bukkit.annotation.Sender;
import me.yushust.message.MessageHandler;
import org.bukkit.entity.Player;

import javax.inject.Inject;

@Command(names = {"statistic", "stats"}, permissionMessage = "kitpvp.statistic")
public class StatisticCommand implements CommandClass {

    @Inject private MessageHandler messageHandler;

    @Command(names = "")
    public boolean statistic(@Sender Player player) {

        messageHandler.sendIn(player, "placeholder_api", "statistic");

        return true;
    }

}
