package dev.notcacha.kitpvp.core.model.custom;

import com.google.common.util.concurrent.ListeningExecutorService;
import dev.notcacha.kitpvp.api.ModelBinderData;
import dev.notcacha.kitpvp.api.cache.ObjectCache;
import dev.notcacha.kitpvp.api.mongo.MongoConnection;
import dev.notcacha.kitpvp.api.user.User;
import dev.notcacha.kitpvp.core.model.MongoModelFindProcessor;
import dev.notcacha.kitpvp.api.user.KitUser;
import org.bukkit.Bukkit;

import javax.inject.Inject;
import java.util.Optional;
import java.util.UUID;

public class MongoModelUserFindProcessor extends MongoModelFindProcessor<User> {

    private final ObjectCache<User> userObjectCache;

    @Inject
    public MongoModelUserFindProcessor(ListeningExecutorService executorService, ObjectCache<User> objectCache, ModelBinderData<User> modelBinderData, MongoConnection mongoConnection) {
        super(executorService, objectCache, modelBinderData, mongoConnection);

        this.userObjectCache = objectCache;
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

            userObjectCache.addObject(user);
        }

        return Optional.of(user);
    }
}
