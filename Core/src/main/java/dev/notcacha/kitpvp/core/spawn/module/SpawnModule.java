package dev.notcacha.kitpvp.core.spawn.module;

import me.yushust.inject.AbstractModule;
import dev.notcacha.kitpvp.core.spawn.SpawnManager;

public class SpawnModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(SpawnManager.class).toProvider(() -> new SpawnManager(null)).singleton();
    }

}
