package dev.notcacha.kitpvp.core.model;

import com.google.common.util.concurrent.ListeningExecutorService;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.ReplaceOptions;
import dev.notcacha.kitpvp.api.ModelBinderData;
import dev.notcacha.kitpvp.api.async.AsyncResponse;
import dev.notcacha.kitpvp.api.cache.ObjectCache;
import dev.notcacha.kitpvp.api.model.Model;
import dev.notcacha.kitpvp.api.model.processor.ModelSaveProcessor;
import dev.notcacha.kitpvp.api.mongo.MongoConnection;
import dev.notcacha.kitpvp.core.async.SimpleAsyncResponse;

import javax.inject.Inject;
import java.util.Set;

public class MongoModelSaveProcessor<T extends Model> implements ModelSaveProcessor<T> {

    @Inject private ListeningExecutorService executorService;
    @Inject private ObjectCache<T> objectCache;

    private final MongoCollection<T> mongoCollection;

    @Inject
    public MongoModelSaveProcessor(ModelBinderData<T> modelBinderData, MongoConnection mongoConnection) {
        this.mongoCollection = mongoConnection.getClient()
                .getDatabase("kitpvp")
                .getCollection(modelBinderData.getType().getRawType().getSimpleName().toLowerCase(), modelBinderData.getType().getRawType());
    }

    @Override
    public void saveSync(T model, boolean saveInCached) {
        mongoCollection.replaceOne(Filters.eq("_id", model.getId()), model, new ReplaceOptions().upsert(true));

        if (saveInCached) {
            objectCache.addObject(model);
        }
    }

    @Override
    public void saveSync(Set<T> models) {
        models.forEach(model -> saveSync(model, false));
    }

    @Override
    public AsyncResponse<Void> saveAsync(T model, boolean saveInCached) {
        return new SimpleAsyncResponse<>(executorService.submit(() -> {
            saveSync(model, saveInCached);

            return null;
        }));
    }

    @Override
    public AsyncResponse<Void> saveAsync(Set<T> models) {
        return new SimpleAsyncResponse<>(executorService.submit(() -> {
            saveSync(models);

            return null;
        }));
    }

    @Override
    public void saveAll() {
        saveSync(objectCache.getAllPresent());
    }
}
