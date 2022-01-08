package dev.notcacha.kitpvp.core.model;

import com.google.common.util.concurrent.ListeningExecutorService;
import javax.inject.Inject;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import dev.notcacha.kitpvp.api.ModelBinderData;
import dev.notcacha.kitpvp.api.async.AsyncResponse;
import dev.notcacha.kitpvp.api.async.Response;
import dev.notcacha.kitpvp.api.cache.ObjectCache;
import dev.notcacha.kitpvp.api.model.Model;
import dev.notcacha.kitpvp.api.model.processor.ModelFindProcessor;
import dev.notcacha.kitpvp.api.mongo.MongoConnection;
import dev.notcacha.kitpvp.core.async.SimpleAsyncResponse;
import dev.notcacha.kitpvp.core.async.WrappedResponse;
import dev.notcacha.kitpvp.core.util.CountdownTimerUtil;
import org.bukkit.plugin.Plugin;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class MongoModelFindProcessor<T extends Model> implements ModelFindProcessor<T> {

    private final ListeningExecutorService executorService;
    private final ObjectCache<T> objectCache;

    private final MongoCollection<T> mongoCollection;
    private final Plugin plugin;

    private boolean loadOfMongo = false;

    @Inject
    public MongoModelFindProcessor(ListeningExecutorService executorService, ObjectCache<T> objectCache, ModelBinderData<T> modelBinderData, MongoConnection mongoConnection, Plugin plugin) {
        this.executorService = executorService;
        this.objectCache = objectCache;
        this.plugin = plugin;
        this.mongoCollection = mongoConnection.getClient()
                .getDatabase("kitpvp")
                .getCollection(modelBinderData.getType().getRawType().getSimpleName().toLowerCase(), modelBinderData.getType().getRawType());
    }

    @Override
    public Optional<T> findOneSync(String id) {
        T model = objectCache.findIfPresent(id).orElse(null);

        if (model == null) {
            model = mongoCollection.find(Filters.eq("_id", id)).first();

            if (model != null) {
                objectCache.update(model);
            }
        }

        return Optional.ofNullable(model);
    }

    @Override
    public AsyncResponse<T> findOneAsync(String id) {
        return new SimpleAsyncResponse<>(executorService.submit(() -> {
            Optional<T> response = findOneSync(id);

            return response.<Response<T>>map(t -> new WrappedResponse<>(Response.Status.SUCCESS, t)).orElseGet(() -> new WrappedResponse<>(Response.Status.ERROR, null));

        }));
    }

    @Override
    public AsyncResponse<Set<T>> findAllAsync() {
        if (!loadOfMongo) {
            mongoCollection.find().into(new HashSet<>()).forEach(model -> {
                if (!objectCache.ifPresent(model.getId())) {
                    objectCache.addObject(model);

                    new CountdownTimerUtil(
                            plugin,
                            120,
                            () -> {
                                loadOfMongo = true;
                            },
                            (ignored) -> {},
                            () -> {
                                loadOfMongo = false;
                            }
                    ).scheduleTimer();
                }
            });
        }

        return new SimpleAsyncResponse<>(executorService.submit(() -> new WrappedResponse<>(Response.Status.SUCCESS, objectCache.getAllPresent())));
    }
}
