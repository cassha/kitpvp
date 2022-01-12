package dev.notcacha.kitpvp.core.hook;

import net.milkbowl.vault.economy.Economy;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.RegisteredServiceProvider;

import javax.inject.Inject;

public class VaultHook {

    @Inject private Plugin plugin;

    private Economy economy;

    private boolean hookStatus = false;

    public void register() {
        if (!plugin.getServer().getPluginManager().getPlugin("Vault").isEnabled()) {

            plugin.getServer().getLogger().warning("[KitPvP] The Vault is not found in server from create hook.");

            return;
        }

        RegisteredServiceProvider<Economy> registeredServiceProvider = plugin.getServer().getServicesManager().getRegistration(Economy.class);

        if (registeredServiceProvider == null) {

            plugin.getLogger().severe("[KitPvP] The RegisteredServiceProvider<Economy> is null.");

            return;
        }

        economy = registeredServiceProvider.getProvider();

        hookStatus = true;
    }

    public boolean isEnabled() {
        return hookStatus;
    }

    public Economy getEconomy() {
        if (economy == null) {
            plugin.getLogger().severe("[KitPvP] The hook of vault is not register.");

            return null;
        }

        return economy;
    }
}
