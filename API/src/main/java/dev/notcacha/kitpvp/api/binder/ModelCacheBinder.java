package dev.notcacha.kitpvp.api.binder;

import dev.notcacha.kitpvp.api.cache.ObjectCache;
import dev.notcacha.kitpvp.api.model.Model;

public interface ModelCacheBinder<T extends Model> extends ModelBinderBackLayout<T> {

    /**
     * Bind a {@link ObjectCache} which will be default cache.
     */

    ModelCacheBinder<T> bindDefault();

}
