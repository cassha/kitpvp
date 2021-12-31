package dev.notcacha.kitpvp.core.async;

import dev.notcacha.kitpvp.api.async.Response;

import java.util.Optional;
import java.util.function.Consumer;

public class WrappedResponse<T> implements Response<T> {

    private final Response.Status state;
    private final T response;

    public WrappedResponse(Response.Status state, T response) {
        this.state = state;
        this.response = response;
    }

    @Override
    public Response.Status getState() {
        return this.state;
    }

    @Override
    public Optional<T> getResponse() {
        return Optional.ofNullable(this.response);
    }

    @Override
    public void ifSuccessful(Consumer<? super T> consumer) {
        if (isSuccessful()) {
            consumer.accept(response);
        }
    }
}
