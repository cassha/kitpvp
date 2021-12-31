package dev.notcacha.kitpvp.core.binder;

import dev.notcacha.kitpvp.api.binder.ModelBinder;
import dev.notcacha.kitpvp.api.binder.ModelProcessorBinder;
import dev.notcacha.kitpvp.api.model.Model;
import dev.notcacha.kitpvp.api.model.processor.ModelDeleteProcessor;
import dev.notcacha.kitpvp.api.model.processor.ModelFindProcessor;
import dev.notcacha.kitpvp.api.model.processor.ModelSaveProcessor;
import dev.notcacha.kitpvp.api.processor.Processor;
import dev.notcacha.kitpvp.api.util.Validate;
import dev.notcacha.kitpvp.core.model.MongoModelDeleteProcessor;
import dev.notcacha.kitpvp.core.model.MongoModelFindProcessor;
import dev.notcacha.kitpvp.core.model.MongoModelSaveProcessor;
import dev.notcacha.kitpvp.core.util.TypeReferenceUtil;
import me.yushust.inject.Binder;

public class CoreModelProcessorBinder<T extends Model> implements ModelProcessorBinder<T> {

    private final Binder binder;
    private final Class<T> modelClass;
    private final ModelBinder<T> modelBinderBack;

    public CoreModelProcessorBinder(Binder binder, Class<T> modelClass, ModelBinder<T> modelBinderBack) {
        this.binder = binder;
        this.modelClass = modelClass;
        this.modelBinderBack = modelBinderBack;
    }

    @Override
    public ModelProcessorBinder<T> bindDelete() {

        binder.bind(
                TypeReferenceUtil.getParameterized(ModelDeleteProcessor.class, modelClass)
        ).to(
                TypeReferenceUtil.getParameterized(MongoModelDeleteProcessor.class, modelClass)
        ).singleton();

        return this;
    }

    @Override
    public ModelProcessorBinder<T> bindFind() {

        binder.bind(
                TypeReferenceUtil.getParameterized(ModelFindProcessor.class, modelClass)
        ).to(
                TypeReferenceUtil.getParameterized(MongoModelFindProcessor.class, modelClass)
        ).singleton();

        return this;
    }

    @Override
    public ModelProcessorBinder<T> bindSave() {

        binder.bind(
                TypeReferenceUtil.getParameterized(ModelSaveProcessor.class, modelClass)
        ).to(
                TypeReferenceUtil.getParameterized(MongoModelSaveProcessor.class, modelClass)
        ).singleton();

        return this;
    }

    @Override
    public <P extends Processor, I extends P> ModelProcessorBinder<T> bindCustom(Class<P> processorClass, Class<I> implementProcessorClass) {
        Validate.nonNull(processorClass, "The custom processor is null.");
        Validate.nonNull(implementProcessorClass, "The custom implement processor is null.");

        binder.bind(
                TypeReferenceUtil.getParameterized(processorClass, modelClass)
        ).to(
                TypeReferenceUtil.getParameterized(implementProcessorClass, modelClass)
        ).singleton();

        return this;
    }

    @Override
    public ModelProcessorBinder<T> bindAll() {
        bindDelete();
        bindFind();
        bindSave();

        return this;
    }

    @Override
    public ModelBinder<T> back() {
        return modelBinderBack;
    }

}
