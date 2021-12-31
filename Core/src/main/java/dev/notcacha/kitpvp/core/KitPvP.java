package dev.notcacha.kitpvp.core;

import javax.inject.Inject;
import dev.notcacha.kitpvp.api.service.Service;
import me.yushust.inject.Injector;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public class KitPvP extends JavaPlugin {

    @Inject private Service service;

    @Override
    public void onEnable() {
        copyDefaults();

        Injector injector = Injector.create(new KitPvPModule(this));
        injector.injectMembers(this);

        service.start();
    }

    @Override
    public void onDisable() {
        service.stop();
    }

    public void copyDefaults() {
        File configFile = new File(this.getDataFolder(), "config.yml");
        if (!configFile.exists()) {
            getConfig().options().copyDefaults(true);
            saveDefaultConfig();
        }
    }

}
