package dev.notcacha.kitpvp.core.binder;

import dev.notcacha.kitpvp.api.binder.ModelBinder;
import dev.notcacha.kitpvp.api.binder.ModelCacheBinder;
import dev.notcacha.kitpvp.api.cache.ObjectCache;
import dev.notcacha.kitpvp.api.model.Model;
import dev.notcacha.kitpvp.core.cache.MapObjectCache;
import dev.notcacha.kitpvp.core.util.TypeReferenceUtil;
import me.yushust.inject.Binder;

public class CoreModelCacheBinder<T extends Model> implements ModelCacheBinder<T> {

    private final Binder binder;
    private final Class<T> type;

    private final ModelBinder<T> modelBinderBack;

    public CoreModelCacheBinder(Binder binder, Class<T> type, ModelBinder<T> modelBinderBack) {
        this.binder = binder;
        this.type = type;
        this.modelBinderBack = modelBinderBack;
    }

    @Override
    public ModelCacheBinder<T> bindDefault() {

        binder.bind(TypeReferenceUtil.getParameterized(ObjectCache.class, type))
                .to(
                        TypeReferenceUtil.getParameterized(MapObjectCache.class, type)
                ).singleton();

        return this;
    }

    @Override
    public ModelBinder<T> back() {
        return modelBinderBack;
    }
}
