package dev.notcacha.kitpvp.core.binder;

import dev.notcacha.kitpvp.api.ModelBinderData;
import dev.notcacha.kitpvp.api.binder.ModelBinder;
import dev.notcacha.kitpvp.api.binder.ModelCacheBinder;
import dev.notcacha.kitpvp.api.binder.ModelProcessorBinder;
import dev.notcacha.kitpvp.api.model.Model;
import dev.notcacha.kitpvp.api.repository.ModelRepository;
import dev.notcacha.kitpvp.api.util.Validate;
import dev.notcacha.kitpvp.core.repository.CoreModelRepository;
import dev.notcacha.kitpvp.core.util.TypeReferenceUtil;
import me.yushust.inject.Binder;

public class CoreModelBinder<T extends Model> implements ModelBinder<T> {

    private final Binder binder;
    private final Class<T> modelClass;

    public CoreModelBinder(Binder binder, Class<T> modelClass) {
        this(binder, modelClass, ModelBinderData.create(TypeReferenceUtil.getTypeOf(modelClass)));
    }

    public CoreModelBinder(Binder binder, Class<T> modelClass, ModelBinderData<T> modelData) {
        this.binder = Validate.nonNull(binder, "The binder of ModelDataBinder is null.");
        this.modelClass = Validate.nonNull(modelClass, "The model class from ModelDataBinder is null.");

        Validate.nonNull(modelData, "The model data of ModelDataBinder is null.");

        binder.bind(
                TypeReferenceUtil.getParameterized(ModelBinderData.class, modelClass)
        ).toInstance(
                modelData
        );
    }

    @Override
    public ModelBinder<T> bindRepository() {
        binder.bind(
                TypeReferenceUtil.getParameterized(ModelRepository.class, modelClass)
        ).to(
                TypeReferenceUtil.getParameterized(CoreModelRepository.class, modelClass)
        ).singleton();

        return this;
    }

    @Override
    public ModelCacheBinder<T> bindCache() {
        return new CoreModelCacheBinder<>(binder, modelClass, this);
    }

    @Override
    public ModelProcessorBinder<T> bindProcessors() {
        return new CoreModelProcessorBinder<>(binder, modelClass, this);
    }

    @Override
    public <M extends Model> ModelBinder<M> newBinder(Class<M> modelClass) {
        Validate.nonNull(modelClass, "The new model class from new binder is null.");

        return new CoreModelBinder<>(binder, modelClass);
    }

}
