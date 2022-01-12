package dev.notcacha.kitpvp.core.cache;

import dev.notcacha.kitpvp.api.cache.ObjectCache;
import dev.notcacha.kitpvp.api.model.Model;
import org.bukkit.plugin.Plugin;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.logging.Level;

public class MapObjectCache<T extends Model> implements ObjectCache<T> {

    private final Map<String, T> map = new HashMap<>();

    @Inject private Plugin plugin;

    @Override
    public Optional<T> findIfPresent(String objectId) {
        return Optional.ofNullable(map.get(objectId));
    }

    @Override
    public void addObject(T object) {
        if (ifPresent(object.getId())) {
            plugin.getLogger().log(Level.WARNING, "The model with the id " + object.getId() + " that is wanting to be added to the cache is already stored, otherwise update the cache");
            return;
        }

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
        if (!ifPresent(objectId)) {
            plugin.getLogger().log(Level.WARNING, "The model of id " + objectId + " not saved in cached.");
            return;
        }

        map.remove(objectId);
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
