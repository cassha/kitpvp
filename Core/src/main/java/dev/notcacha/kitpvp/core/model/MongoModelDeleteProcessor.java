package dev.notcacha.kitpvp.core.model;

import com.google.common.util.concurrent.ListeningExecutorService;
import com.mongodb.client.MongoClient;
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
    public boolean deleteSync(T object) {
        mongoCollection.deleteOne(Filters.eq("_id", object.getId()));
        objectCache.removeObject(object.getId());

        return true;
    }

    @Override
    public void deleteSync(Set<T> objects) {
        objects.forEach(this::deleteSync);
    }

    @Override
    public AsyncResponse<Boolean> deleteAsync(T object) {
        return new SimpleAsyncResponse<>(executorService.submit(() -> new WrappedResponse<>(Response.Status.SUCCESS, deleteSync(object))));
    }

    @Override
    public AsyncResponse<Void> deleteAsync(Set<T> objects) {
        return new SimpleAsyncResponse<>(executorService.submit(() -> {
            deleteSync(objects);

            return null;
        }));
    }
}
