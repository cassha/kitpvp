package dev.notcacha.kitpvp.api.binder;

import dev.notcacha.kitpvp.api.cache.ObjectCache;
import dev.notcacha.kitpvp.api.model.Model;
import dev.notcacha.kitpvp.api.processor.Processor;
import dev.notcacha.kitpvp.api.repository.ModelRepository;

public interface ModelBinder<T extends Model> {

    /**
     * Bind a {@link ModelRepository} for model {@link T}
     *
     * @return This class instance from bind.
     */

    ModelBinder<T> bindRepository();

    /**
     * Bind the {@link ObjectCache} from {@link T} model.
     *
     * @return New {@link ModelCacheBinder} from bind cache's.
     */

    ModelCacheBinder<T> bindCache();

    /**
     * Bind the {@link Processor} from {@link T} model.
     *
     * @return New {@link ModelProcessorBinder} from bind processor's.
     */

    ModelProcessorBinder<T> bindProcessors();

    <M extends Model> ModelBinder<M> newBinder(Class<M> modelClass);

}
