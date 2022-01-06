package dev.notcacha.kitpvp.core.service;

import javax.inject.Inject;
import dev.notcacha.kitpvp.api.service.Service;
import dev.notcacha.kitpvp.core.command.ChatCommand;
import dev.notcacha.kitpvp.core.command.KitCommand;
import dev.notcacha.kitpvp.core.command.spawn.SetSpawnCommand;
import dev.notcacha.kitpvp.core.command.SpawnCommand;
import dev.notcacha.kitpvp.core.command.TagCommand;
import me.fixeddev.commandflow.CommandManager;
import me.fixeddev.commandflow.annotated.AnnotatedCommandTreeBuilder;
import me.fixeddev.commandflow.annotated.AnnotatedCommandTreeBuilderImpl;
import me.fixeddev.commandflow.annotated.CommandClass;
import me.fixeddev.commandflow.annotated.builder.AnnotatedCommandBuilderImpl;
import me.fixeddev.commandflow.annotated.part.PartInjector;
import me.fixeddev.commandflow.annotated.part.SimplePartInjector;
import me.fixeddev.commandflow.annotated.part.defaults.DefaultsModule;
import me.fixeddev.commandflow.bukkit.factory.BukkitModule;
import me.yushust.inject.Injector;
import org.bukkit.plugin.Plugin;

public class CommandService implements Service {

    @Inject private CommandManager commandManager;

    @Inject private Plugin plugin;
    @Inject private Injector injector;

    @Inject private SpawnCommand spawnCommand;
    @Inject private SetSpawnCommand setSpawnCommand;
    @Inject private KitCommand kitCommand;
    @Inject private TagCommand tagCommand;
    @Inject private ChatCommand chatCommand;

    @Override
    public void start() {
        plugin.getServer().getLogger().info("[KitPvP] The command loader service has been started.");

        register(
                setSpawnCommand,
                spawnCommand,
                kitCommand,
                tagCommand,
                chatCommand
        );
    }

    private void register(CommandClass... commandClasses) {
        PartInjector partInjector = new SimplePartInjector();
        partInjector.install(new BukkitModule());
        partInjector.install(new DefaultsModule());

        AnnotatedCommandTreeBuilder treeBuilder = new AnnotatedCommandTreeBuilderImpl(
                new AnnotatedCommandBuilderImpl(partInjector),
                (clazz, parent) -> injector.getInstance(clazz)
        );

        for (CommandClass commandClass : commandClasses) {
            commandManager.registerCommands(treeBuilder.fromClass(commandClass));
        }
    }

    @Override
    public void stop() {
        plugin.getServer().getLogger().info("[KitPvP] The command service has been stopped and un register all commands.");

        commandManager.unregisterAll();
    }
}
