package dev.notcacha.kitpvp.api.model.processor;

import dev.notcacha.kitpvp.api.async.AsyncResponse;
import dev.notcacha.kitpvp.api.model.Model;
import dev.notcacha.kitpvp.api.processor.Processor;

import java.util.Optional;
import java.util.Set;

public interface ModelFindProcessor<T extends Model> extends Processor {

    /**
     * @return {@link T} object from storage, using {@param id} as identifier to get the object
     */

    Optional<T> findOneSync(String id);

    /**
     * @param id as identifier to get the object
     * @return a {@link AsyncResponse} with the purpose of obtaining an object {@link T} asynchronously
     */

    AsyncResponse<T> findOneAsync(String id);

    /**
     * Find all Models in storage and cache.
     * @return All models {@link T} wrapped in {@link Set}
     */

    AsyncResponse<Set<T>> findAllAsync();

}
