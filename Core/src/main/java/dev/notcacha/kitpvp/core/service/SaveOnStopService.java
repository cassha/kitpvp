package dev.notcacha.kitpvp.core.service;

import javax.inject.Inject;
import dev.notcacha.kitpvp.api.kit.Kit;
import dev.notcacha.kitpvp.api.model.processor.ModelSaveProcessor;
import dev.notcacha.kitpvp.api.mongo.MongoConnection;
import dev.notcacha.kitpvp.api.service.Service;
import dev.notcacha.kitpvp.api.tag.Tag;
import dev.notcacha.kitpvp.api.user.User;
import org.bukkit.plugin.Plugin;

import java.io.IOException;

public class SaveOnStopService implements Service {

    @Inject private Plugin plugin;

    @Inject private ModelSaveProcessor<User> userModelSaveProcessor;
    @Inject private ModelSaveProcessor<Kit> kitModelSaveProcessor;
    @Inject private ModelSaveProcessor<Tag> tagModelSaveProcessor;

    @Inject private MongoConnection mongoConnection;

    @Override
    public void start() {}

    @Override
    public void stop() {
        userModelSaveProcessor.saveAll();
        kitModelSaveProcessor.saveAll();
        tagModelSaveProcessor.saveAll();

        plugin.getLogger().info("[KitPvP] All models has been saved in storage.");

        try {
            mongoConnection.close();
        } catch (IOException ignored) {}
    }
}
