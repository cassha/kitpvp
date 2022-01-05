package dev.notcacha.kitpvp.core.tag.module;

import me.yushust.inject.AbstractModule;
import dev.notcacha.kitpvp.api.ModelBinderData;
import dev.notcacha.kitpvp.api.binder.ModelBinder;
import dev.notcacha.kitpvp.api.tag.Tag;
import dev.notcacha.kitpvp.api.tag.applier.TagApplier;
import dev.notcacha.kitpvp.core.binder.CoreModelBinder;
import dev.notcacha.kitpvp.core.tag.CoreTagApplier;
import dev.notcacha.kitpvp.core.util.TypeReferenceUtil;

public class TagModule extends AbstractModule {

    @Override
    protected void configure() {
        ModelBinder<Tag> tagModelBinder = new CoreModelBinder<>(
                binder(),
                Tag.class,
                ModelBinderData.forStorage(
                        TypeReferenceUtil.getTypeOf(Tag.class)
                )
        );

        tagModelBinder.bindCache().bindDefault();
        tagModelBinder.bindProcessors()
                .bindFind()
                .bindDelete()
                .bindSave();

        tagModelBinder.bindRepository();

        bind(TagApplier.class).to(CoreTagApplier.class).singleton();
    }
}
