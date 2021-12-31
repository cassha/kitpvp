package dev.notcacha.kitpvp.core.util;

import me.yushust.inject.key.TypeReference;
import me.yushust.inject.key.Types;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class TypeReferenceUtil {

    public static <T> TypeReference<T> getTypeOf(Class<T> clazz){
        return TypeReference.of(clazz);
    }

    public static TypeReference getTypeOf(Type type){
        return TypeReference.of(type);
    }

    @SuppressWarnings("unchecked")
    public static <T> TypeReference<T> getParameterized(Class<T> clazz, Class<?>... parameters){
        ParameterizedType type = Types.parameterizedTypeOf(clazz.getGenericSuperclass(), clazz, parameters);

        return getTypeOf(type);
    }

}
