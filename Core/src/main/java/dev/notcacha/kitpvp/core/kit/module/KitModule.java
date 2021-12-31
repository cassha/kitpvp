package dev.notcacha.kitpvp.core.kit.module;

import me.yushust.inject.AbstractModule;
import dev.notcacha.kitpvp.api.ModelBinderData;
import dev.notcacha.kitpvp.api.binder.ModelBinder;
import dev.notcacha.kitpvp.api.kit.Kit;
import dev.notcacha.kitpvp.api.model.processor.ModelFindProcessor;
import dev.notcacha.kitpvp.core.binder.CoreModelBinder;
import dev.notcacha.kitpvp.core.util.TypeReferenceUtil;

public class KitModule extends AbstractModule {

    @Override
    protected void configure() {
        ModelBinder<Kit> kitModelBinder = new CoreModelBinder<>(
                binder(),
                Kit.class,
                ModelBinderData.forStorage(
                        TypeReferenceUtil.getTypeOf(Kit.class),
                        "/kits/"
                )
        );

        kitModelBinder.bindCache().bindDefault();
        kitModelBinder.bindRepository();
        kitModelBinder.bindProcessors()
                .bindFind()
                .bindDelete()
                .bindSave();
    }
}
