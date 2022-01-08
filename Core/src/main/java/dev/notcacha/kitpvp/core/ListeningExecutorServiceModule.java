package dev.notcacha.kitpvp.core;

import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;
import me.yushust.inject.AbstractModule;

import java.util.concurrent.Executors;

public class ListeningExecutorServiceModule extends AbstractModule {
    @Override
    protected void configure() {
        this.bind(ListeningExecutorService.class).toProvider(
                () ->
                        MoreExecutors.listeningDecorator(Executors.newCachedThreadPool()))
                .singleton();
    }
}
