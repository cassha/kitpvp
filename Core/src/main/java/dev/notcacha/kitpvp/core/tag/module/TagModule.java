package dev.notcacha.kitpvp.core.tag.module;

import me.yushust.inject.AbstractModule;
import dev.notcacha.kitpvp.api.ModelBinderData;
import dev.notcacha.kitpvp.api.binder.ModelBinder;
import dev.notcacha.kitpvp.api.tag.Tag;
import dev.notcacha.kitpvp.api.tag.applier.TagApplier;
import dev.notcacha.kitpvp.api.tag.create.TagCreate;
import dev.notcacha.kitpvp.api.tag.delete.TagDelete;
import dev.notcacha.kitpvp.core.binder.CoreModelBinder;
import dev.notcacha.kitpvp.core.tag.util.CoreTagApplier;
import dev.notcacha.kitpvp.core.tag.util.CoreTagCreate;
import dev.notcacha.kitpvp.core.tag.util.CoreTagDelete;
import dev.notcacha.kitpvp.core.util.TypeReferenceUtil;

public class TagModule extends AbstractModule {

    @Override
    protected void configure() {
        ModelBinder<Tag> userModelBinder = new CoreModelBinder<>(
                binder(),
                Tag.class,
                ModelBinderData.forStorage(
                        TypeReferenceUtil.getTypeOf(Tag.class),
                        "/tags/"
                )
        );

        userModelBinder.bindCache().bindDefault();
        userModelBinder.bindRepository();
        userModelBinder.bindProcessors()
                .bindFind()
                .bindDelete()
                .bindSave();

        bind(TagApplier.class).to(CoreTagApplier.class).singleton();
        bind(TagCreate.class).to(CoreTagCreate.class).singleton();
        bind(TagDelete.class).to(CoreTagDelete.class).singleton();
    }
}
