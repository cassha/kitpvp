package dev.notcacha.kitpvp.api.cache;

import java.util.Map;
import java.util.Optional;
import java.util.Set;

public interface ObjectCache<O> {

    /**
     * @return An object {@link O} encapsulated in an {@link Optional} to prevent possible NPEs
     */

    Optional<O> findIfPresent(String objectId);

    /**
     * Add an object to cache.
     *
     * @param object has been added to cache.
     */

    void addObject(O object);

    /**
     * Update the object in cache.
     * @param object has been updated in cache.
     */

    void update(O object);

    /**
     * Remove an object to cache.
     *
     * @param objectId from object has been removed from cache.
     */

    void removeObject(String objectId);

    /**
     * @return If the object is cached.
     */

    boolean ifPresent(String objectId);

    /**
     * Remove all object in cached.
     */

    void clear();

    /**
     * @return All objects that are present in the cache.
     */

    Set<O> getAllPresent();


}
