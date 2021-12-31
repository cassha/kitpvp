package dev.notcacha.kitpvp.core.async;

import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import dev.notcacha.kitpvp.api.async.AsyncResponse;
import dev.notcacha.kitpvp.api.async.Callback;
import dev.notcacha.kitpvp.api.async.Response;

public class SimpleAsyncResponse<T> implements AsyncResponse<T> {

    private final ListenableFuture<Response<T>> responseFuture;

    public SimpleAsyncResponse(ListenableFuture<Response<T>> responseFuture) {
        this.responseFuture = responseFuture;
    }

    @Override
    public void callback(Callback<Response<T>> callback) {
        Futures.addCallback(this.responseFuture, this.wrapCallback(callback));
    }

    private FutureCallback<Response<T>> wrapCallback(Callback<Response<T>> callback) {
        return new FutureCallback<Response<T>>() {

            @Override
            public void onSuccess(Response<T> response) {
                callback.call(response);
            }

            @Override
            public void onFailure(Throwable throwable) {
               callback.handleException(throwable);
            }
        };
    }

}
