package dev.notcacha.kitpvp.core.service;

import javax.inject.Inject;
import javax.inject.Named;

import dev.notcacha.kitpvp.api.service.Service;
import org.bukkit.plugin.Plugin;

public class KitPvPService implements Service {

    @Inject private Plugin plugin;
    @Inject @Named("command") private Service commandService;
    @Inject @Named("event") private Service eventService;
    @Inject @Named("spawn") private Service spawnsService;
    @Inject @Named("save") private  Service saveService;
    @Inject @Named("hook-register") private Service hookRegisterService;
    @Inject @Named("scoreboard") private Service scoreboardService;

    @Override
    public void start() {
        plugin.getServer().getLogger().info("[KitPvP] The main service has been started.");

        spawnsService.start();
        commandService.start();
        eventService.start();
        scoreboardService.start();
        hookRegisterService.start();
    }

    @Override
    public void stop() {
        commandService.stop();
        eventService.stop();
        spawnsService.stop();

        saveService.stop();

        plugin.getServer().getLogger().info("[KitPvP] The main service has been stopped.");
    }
}
