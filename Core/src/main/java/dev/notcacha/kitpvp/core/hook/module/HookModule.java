package dev.notcacha.kitpvp.core.hook.module;

import dev.notcacha.kitpvp.core.hook.PlaceholderAPIHook;
import dev.notcacha.kitpvp.core.hook.VaultHook;
import me.yushust.inject.AbstractModule;

public class HookModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(PlaceholderAPIHook.class).singleton();

        bind(VaultHook.class).singleton();
    }
}
