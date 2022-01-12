package dev.notcacha.kitpvp.core.service;

import dev.notcacha.kitpvp.api.service.Service;
import dev.notcacha.kitpvp.core.hook.PlaceholderAPIHook;
import dev.notcacha.kitpvp.core.hook.VaultHook;

import javax.inject.Inject;

public class HookRegisterService implements Service {

    @Inject private PlaceholderAPIHook placeholderAPIHook;
    @Inject private VaultHook vaultHook;

    @Override
    public void start() {
        placeholderAPIHook.register();

        vaultHook.register();
    }
}
