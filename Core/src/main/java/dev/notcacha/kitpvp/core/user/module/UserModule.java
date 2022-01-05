package dev.notcacha.kitpvp.core.user.module;

import dev.notcacha.kitpvp.core.model.MongoModelFindProcessor;
import dev.notcacha.kitpvp.core.model.custom.MongoModelUserFindProcessor;
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
                        TypeReferenceUtil.getTypeOf(User.class)
                )
        );

        userModelBinder.bindCache().bindDefault();
        userModelBinder.bindProcessors()
                .bindCustom(
                        ModelFindProcessor.class,
                        MongoModelUserFindProcessor.class
                )
                .bindDelete()
                .bindSave();

        userModelBinder.bindRepository();
    }
}
