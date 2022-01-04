package dev.notcacha.kitpvp.core.cache;

import dev.notcacha.kitpvp.api.cache.ObjectCache;
import dev.notcacha.kitpvp.api.model.Model;

import javax.inject.Singleton;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

@Singleton
public class MapObjectCache<T extends Model> implements ObjectCache<T> {

    private final Map<String, T> map = new HashMap<>();

    @Override
    public Optional<T> findIfPresent(String objectId) {
        return Optional.ofNullable(map.get(objectId));
    }

    @Override
    public void addObject(T object) {
        map.put(object.getId(), object);
    }

    @Override
    public void update(T object) {
        if (ifPresent(object.getId())) {
            removeObject(object.getId());
        }

        addObject(object);
    }

    @Override
    public void removeObject(String objectId) {
        if (ifPresent(objectId)) removeObject(objectId);
    }

    @Override
    public boolean ifPresent(String objectId) {
        return map.containsKey(objectId);
    }

    @Override
    public Set<T> getAllPresent() {
        return new HashSet<>(map.values());
    }

    @Override
    public void clear() {
        map.clear();
    }
}
