package dev.notcacha.kitpvp.api.model.processor;

import dev.notcacha.kitpvp.api.async.AsyncResponse;
import dev.notcacha.kitpvp.api.model.Model;
import dev.notcacha.kitpvp.api.processor.Processor;

import java.util.Set;

public interface ModelDeleteProcessor<T extends Model> extends Processor {

    /**
     * Remove an object {@link T} from storage, using {@param object} to identify and delete it
     */

    boolean deleteSync(T object);

    /**
     * Remove multiple {@link T} objects from storage, using {@param object} to identify and delete it
     */

    void deleteSync(Set<T> objects);

    /**
     * Remove an object {@link T} asynchronously using reference {@param object} to get it and remove it
     *
     * @return {@link AsyncResponse} to handle the asynchronous response
     */

    AsyncResponse<Boolean> deleteAsync(T object);

    /**
     * Remove multiple {@link T} objects asynchronously using reference {@param object} to get and remove it
     *
     * @return {@link AsyncResponse} to handle the asynchronous response
     */

    AsyncResponse<Void> deleteAsync(Set<T> objects);

}
