package dev.notcacha.kitpvp.api.util;

public interface Validate {

    /**
     * Validates if the passed object is not null.
     *
     * @param object  to be validated.
     * @param message that will be sent in case it is not valid.
     */

    static <T> T nonNull(T object, String message) {
        if (object == null) {
            throw new NullPointerException(message);
        }

        return object;
    }

}
