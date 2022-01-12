package dev.notcacha.kitpvp.core.command.flow.authorizer;

import me.fixeddev.commandflow.Authorizer;
import me.fixeddev.commandflow.Namespace;
import org.bukkit.command.CommandSender;

public class KitPvPAuthorizer implements Authorizer {
    @Override
    public boolean isAuthorized(Namespace namespace, String permission) {
        CommandSender sender = namespace.getObject(CommandSender.class, "SENDER");

        if (permission.isEmpty() || sender.hasPermission("kitpvp.*")) {
            return true;
        }

        return sender.hasPermission(permission);
    }
}
