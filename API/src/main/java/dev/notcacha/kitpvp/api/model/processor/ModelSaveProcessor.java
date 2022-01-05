package dev.notcacha.kitpvp.api.model.processor;

import dev.notcacha.kitpvp.api.async.AsyncResponse;
import dev.notcacha.kitpvp.api.model.Model;
import dev.notcacha.kitpvp.api.processor.Processor;

import java.util.Set;

public interface ModelSaveProcessor<T extends Model> extends Processor {

    /**
     * Save an object in the storage, {@link T} will be the saved object,
     * {@link T#getId()} this will be the identifier of this object that will be used when it is obtained
     */

    void saveSync(T model, boolean saveInCached);

    /**
     * Save multiple objects in the storage, {@link T} will be the saved objects,
     * {@link T#getId()} this will be the identifier of this object that will be used when it is obtained
     */

    void saveSync(Set<T> models);

    /**
     * Save an object {@link T} asynchronously
     *
     * @return {@link AsyncResponse} to handle the asynchronous response
     */

    AsyncResponse<Void> saveAsync(T model, boolean saveInCached);

    /**
     * Save multiple {@link T} objects asynchronously
     *
     * @return {@link AsyncResponse} to handle the asynchronous response
     */

    AsyncResponse<Void> saveAsync(Set<T> models);

    /**
     * Save all the models in storage.
     */

    void saveAll();
}
