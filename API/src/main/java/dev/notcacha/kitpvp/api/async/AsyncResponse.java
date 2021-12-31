package dev.notcacha.kitpvp.api.async;

public interface AsyncResponse<T> {

    void callback(Callback<Response<T>> callback);
}
