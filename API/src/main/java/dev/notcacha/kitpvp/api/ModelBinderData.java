package dev.notcacha.kitpvp.api;

import dev.notcacha.kitpvp.api.util.Validate;
import me.yushust.inject.key.TypeReference;

import java.util.HashMap;
import java.util.Map;

public interface ModelBinderData<T> {

    /**
     * @return The type from data of binder.
     */

    TypeReference<T> getType();

    /**
     * @return Alternative data in any case that is necessary
     */

    Map<String, Object> getAlternativeData();

    /**
     * @return New instance class of {@link ModelBinderData}.
     */

    static <T> ModelBinderData<T> create(TypeReference<T> reference) {
        Validate.nonNull(reference, "The Type Reference of ModelBinderData is null.");

        return new ModelBinderData<T>() {

            private final Map<String, Object> dataMap = new HashMap<>();

            @Override
            public TypeReference<T> getType() {
                return reference;
            }

            @Override
            public Map<String, Object> getAlternativeData() {
                return dataMap;
            }
        };
    }

    /**
     * @return New {@link ModelBinderData} from storage's.
     */

    static <T> ModelBinderData<T> forStorage(TypeReference<T> reference, String storageFolder) {
        ModelBinderData<T> modelDataBinder = create(reference);

        Validate.nonNull(storageFolder, "The storage folder from ModelBinderData is null");

        modelDataBinder.getAlternativeData().put("folder", storageFolder);

        return modelDataBinder;
    }
}
