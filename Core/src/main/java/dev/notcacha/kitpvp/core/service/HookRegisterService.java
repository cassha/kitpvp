package dev.notcacha.kitpvp.core.service;

import dev.notcacha.kitpvp.api.cache.ObjectCache;
import dev.notcacha.kitpvp.api.service.Service;
import dev.notcacha.kitpvp.api.user.User;
import dev.notcacha.kitpvp.core.hook.PlaceholderAPIHook;
import org.bukkit.Bukkit;

import javax.inject.Inject;

public class HookRegisterService implements Service {

    @Inject private ObjectCache<User> userObjectCache;

    @Override
    public void start() {
        if (!Bukkit.getServer().getPluginManager().isPluginEnabled("PlaceholderAPI")) {
            return;
        }

        new PlaceholderAPIHook(userObjectCache).register();
    }
}
