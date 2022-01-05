package dev.notcacha.kitpvp.core.repository;

import dev.notcacha.kitpvp.api.async.AsyncResponse;
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

    @Override
    public Optional<T> findOneSync(String id) {
        return modelFindProcessor.findOneSync(id);
    }

    @Override
    public AsyncResponse<T> findOne(String id) {
        return modelFindProcessor.findOneAsync(id);
    }

    @Override
    public AsyncResponse<Set<T>> findAll() {
        return modelFindProcessor.findAllAsync();
    }

    @Override
    public AsyncResponse<Void> save(T object, boolean saveInCached) {
        return modelSaveProcessor.saveAsync(object, saveInCached);
    }

    @Override
    public AsyncResponse<Boolean> delete(String id) {
        return modelDeleteProcessor.deleteAsync(id);
    }
}
