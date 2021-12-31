package dev.notcacha.kitpvp.core.repository;

import com.google.common.util.concurrent.ListeningExecutorService;
import dev.notcacha.kitpvp.api.async.AsyncResponse;
import dev.notcacha.kitpvp.api.async.Response;
import dev.notcacha.kitpvp.core.async.SimpleAsyncResponse;
import dev.notcacha.kitpvp.core.async.WrappedResponse;
import dev.notcacha.kitpvp.api.model.Model;
import dev.notcacha.kitpvp.api.model.processor.ModelDeleteProcessor;
import dev.notcacha.kitpvp.api.model.processor.ModelFindProcessor;
import dev.notcacha.kitpvp.api.model.processor.ModelSaveProcessor;
import dev.notcacha.kitpvp.api.repository.ModelRepository;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.Optional;
import java.util.Set;

@Singleton
public class CoreModelRepository<T extends Model> implements ModelRepository<T> {

    @Inject private ModelFindProcessor<T> modelFindProcessor;
    @Inject private ModelSaveProcessor<T> modelSaveProcessor;
    @Inject private ModelDeleteProcessor<T> modelDeleteProcessor;
    @Inject private ListeningExecutorService executorService;

    @Override
    public Optional<T> findOneSync(String id) {
        return modelFindProcessor.findOneSync(id);
    }

    @Override
    public AsyncResponse<T> findOne(String id) {
        return new SimpleAsyncResponse<>(executorService.submit(() -> new WrappedResponse<>(Response.Status.SUCCESS, findOneSync(id).orElse(null))));
    }

    @Override
    public AsyncResponse<Set<T>> findAll() {
        return modelFindProcessor.findAllAsync();
    }

    @Override
    public AsyncResponse<Void> save(T object) {
        return new SimpleAsyncResponse<>(executorService.submit(() -> {
            modelSaveProcessor.saveAsync(object);
            return null;
        }));
    }

    @Override
    public AsyncResponse<Void> delete(String id) {
        return new SimpleAsyncResponse<>(executorService.submit(() -> {
            findOneSync(id).ifPresent((model) -> modelDeleteProcessor.deleteAsync(model));

            return null;
        }));
    }
}
