package dev.notcacha.kitpvp.core.user.module;

import dev.notcacha.kitpvp.core.model.MongoModelFindProcessor;
import me.yushust.inject.AbstractModule;
import dev.notcacha.kitpvp.api.ModelBinderData;
import dev.notcacha.kitpvp.api.binder.ModelBinder;
import dev.notcacha.kitpvp.api.model.processor.ModelFindProcessor;
import dev.notcacha.kitpvp.api.user.User;
import dev.notcacha.kitpvp.core.binder.CoreModelBinder;
import dev.notcacha.kitpvp.core.util.TypeReferenceUtil;

public class UserModule extends AbstractModule {

    @Override
    protected void configure() {
        ModelBinder<User> userModelBinder = new CoreModelBinder<>(
                binder(),
                User.class,
                ModelBinderData.forStorage(
                        TypeReferenceUtil.getTypeOf(User.class),
                        "/users/"
                )
        );

        userModelBinder.bindCache().bindDefault();
        userModelBinder.bindRepository();
        userModelBinder.bindProcessors()
                .bindCustom(
                        ModelFindProcessor.class,
                        MongoModelFindProcessor.class
                )
                .bindDelete()
                .bindSave();
    }
}
