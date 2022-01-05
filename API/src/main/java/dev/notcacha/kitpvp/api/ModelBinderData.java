package dev.notcacha.kitpvp.api;

import dev.notcacha.kitpvp.api.util.Validate;
import me.yushust.inject.key.TypeReference;


public interface ModelBinderData<T> {

    /**
     * @return The type from data of binder.
     */

    TypeReference<T> getType();

    /**
     * @return New instance class of {@link ModelBinderData}.
     */

    static <T> ModelBinderData<T> create(TypeReference<T> reference) {
        Validate.nonNull(reference, "The Type Reference of ModelBinderData is null.");

        return new ModelBinderData<T>() {

            @Override
            public TypeReference<T> getType() {
                return reference;
            }
        };
    }

    /**
     * @return New {@link ModelBinderData} from storage's.
     */

    static <T> ModelBinderData<T> forStorage(TypeReference<T> reference) {
        return create(reference);
    }
}
