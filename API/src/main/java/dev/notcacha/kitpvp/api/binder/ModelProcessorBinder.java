package dev.notcacha.kitpvp.api.binder;

import dev.notcacha.kitpvp.api.model.Model;
import dev.notcacha.kitpvp.api.model.processor.ModelDeleteProcessor;
import dev.notcacha.kitpvp.api.model.processor.ModelFindProcessor;
import dev.notcacha.kitpvp.api.model.processor.ModelSaveProcessor;
import dev.notcacha.kitpvp.api.processor.Processor;

public interface ModelProcessorBinder<T extends Model> extends ModelBinderBackLayout<T> {

    /**
     * Bind the {@link ModelDeleteProcessor} from {@link T} model.
     *
     * @return This class instance.
     */

    ModelProcessorBinder<T> bindDelete();

    /**
     * Bind the {@link ModelFindProcessor} from {@link T} model.
     *
     * @return This class instance.
     */

    ModelProcessorBinder<T> bindFind();

    /**
     * Bind the {@link ModelSaveProcessor} from {@link T} model.
     *
     * @return This class instance.
     */

    ModelProcessorBinder<T> bindSave();

    /**
     * Bind all processes that are available for the model {@link T}
     *
     * @return This class instance.
     */

    ModelProcessorBinder<T> bindAll();

    /**
     * Bind an {@link Processor} external to the program.
     *
     * @param processorClass That will be blessed
     * @param <P>            Refers to the {@link Processor}
     * @return This class instance.
     */

    <P extends Processor, I extends P> ModelProcessorBinder<T> bindCustom(Class<P> processorClass, Class<I> implementProcessorClass);
}
