package dev.notcacha.kitpvp.core.file.module;

import me.yushust.inject.AbstractModule;
import dev.notcacha.kitpvp.core.file.YamlFile;
import org.bukkit.plugin.Plugin;

public class FileModule extends AbstractModule {

    private final Plugin plugin;

    public FileModule(Plugin plugin) {
        this.plugin = plugin;
    }

    @Override
    protected void configure() {

        bind(YamlFile.class).named("message").toProvider(() -> new YamlFile(plugin, "messages")
        ).singleton();

        bind(YamlFile.class).named("spawn").toProvider(() -> new YamlFile(plugin, "spawn")
        ).singleton();
    }
}
