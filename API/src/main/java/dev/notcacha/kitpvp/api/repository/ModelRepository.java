package dev.notcacha.kitpvp.api.repository;

import dev.notcacha.kitpvp.api.async.AsyncResponse;
import dev.notcacha.kitpvp.api.model.Model;

import java.util.Optional;
import java.util.Set;

public interface ModelRepository<T extends Model> {

    /**
     * Find an object in the database repository with their id
     *
     * @param id The id of the object to find
     * @return An optional sync response of the object
     */

    Optional<T> findOneSync(String id);

    /**
     * Find an object in the database repository with their id
     *
     * @param id The id of the object to find
     * @return An optional async response of the object
     */

    AsyncResponse<T> findOne(String id);

    /**
     * Find all the objects in repository.
     *
     * @return All models in {@link Set<T>} wrapped.
     */

    AsyncResponse<Set<T>> findAll();

    /**
     * Save an object with the same type of the generic type and save in the database
     *
     * @param object The object to save
     * @param saveInCached determines in object as been saved in cached.
     * @return An async void response indicating that was saved
     */

    AsyncResponse<Void> save(T object, boolean saveInCached);

    /**
     * Delete an object of the database
     *
     * @param id The id of the object to delete
     * @return An async void response indicating that was deleted
     */

    AsyncResponse<Boolean> delete(String id);
}
