package dev.notcacha.kitpvp.api.model.processor;

import dev.notcacha.kitpvp.api.async.AsyncResponse;
import dev.notcacha.kitpvp.api.model.Model;
import dev.notcacha.kitpvp.api.processor.Processor;

import java.util.Set;

public interface ModelDeleteProcessor<T extends Model> extends Processor {

    /**
     * Remove an object {@link T} from storage, using {@param id} to identify and delete it
     */

    boolean deleteSync(String id);

    /**
     * Remove multiple {@link T} objects from storage, using {@param ids} to identify and delete it
     */

    void deleteSync(Set<String> ids);

    /**
     * Remove an object {@link T} asynchronously using reference {@param id} to get it and remove it
     *
     * @return {@link AsyncResponse} to handle the asynchronous response
     */

    AsyncResponse<Boolean> deleteAsync(String id);

    /**
     * Remove multiple {@link T} objects asynchronously using reference {@param ids} to get and remove it
     *
     * @return {@link AsyncResponse} to handle the asynchronous response
     */

    AsyncResponse<Void> deleteAsync(Set<String> ids);

}
