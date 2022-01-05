package dev.notcacha.kitpvp.core.model;

import com.google.common.util.concurrent.ListeningExecutorService;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import dev.notcacha.kitpvp.api.ModelBinderData;
import dev.notcacha.kitpvp.api.async.AsyncResponse;
import dev.notcacha.kitpvp.api.async.Response;
import dev.notcacha.kitpvp.api.cache.ObjectCache;
import dev.notcacha.kitpvp.api.model.Model;
import dev.notcacha.kitpvp.api.model.processor.ModelDeleteProcessor;
import dev.notcacha.kitpvp.api.mongo.MongoConnection;
import dev.notcacha.kitpvp.core.async.SimpleAsyncResponse;
import dev.notcacha.kitpvp.core.async.WrappedResponse;

import javax.inject.Inject;
import java.util.Set;

public class MongoModelDeleteProcessor<T extends Model>  implements ModelDeleteProcessor<T> {

    @Inject private ListeningExecutorService executorService;
    @Inject private ObjectCache<T> objectCache;

    private final MongoCollection<T> mongoCollection;

    @Inject
    public MongoModelDeleteProcessor(ModelBinderData<T> modelBinderData, MongoConnection mongoConnection) {
        this.mongoCollection = mongoConnection.getClient()
                .getDatabase("kitpvp")
                .getCollection(modelBinderData.getType().getRawType().getSimpleName().toLowerCase(), modelBinderData.getType().getRawType());
    }

    @Override
    public boolean deleteSync(String id) {
        mongoCollection.deleteOne(Filters.eq("_id", id));

        objectCache.removeObject(id);

        return true;
    }

    @Override
    public void deleteSync(Set<String> ids) {
        ids.forEach(this::deleteSync);
    }

    @Override
    public AsyncResponse<Boolean> deleteAsync(String id) {
        return new SimpleAsyncResponse<>(executorService.submit(() -> new WrappedResponse<>(Response.Status.SUCCESS, deleteSync(id))));
    }

    @Override
    public AsyncResponse<Void> deleteAsync(Set<String> ids) {
        return new SimpleAsyncResponse<>(executorService.submit(() -> {
            deleteSync(ids);

            return null;
        }));
    }
}
