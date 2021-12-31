package dev.notcacha.kitpvp.core.model.custom;

import dev.notcacha.kitpvp.api.ModelBinderData;
import dev.notcacha.kitpvp.api.mongo.MongoConnection;
import dev.notcacha.kitpvp.api.user.User;
import dev.notcacha.kitpvp.core.model.MongoModelFindProcessor;
import dev.notcacha.kitpvp.core.user.KitUser;
import org.bukkit.Bukkit;

import java.util.Optional;
import java.util.UUID;

public class MongoModelUserFindProcessor extends MongoModelFindProcessor<User> {

    public MongoModelUserFindProcessor(ModelBinderData<User> modelBinderData, MongoConnection mongoConnection) {
        super(modelBinderData, mongoConnection);
    }

    @Override
    public Optional<User> findOneSync(String id) {
        User user = super.findOneSync(id).orElse(null);

        if (user == null) {
            user = new KitUser(id, Bukkit.getOfflinePlayer(
                    UUID.fromString(
                            id
                    )
            ).getName());
        }

        return Optional.of(user);
    }
}
