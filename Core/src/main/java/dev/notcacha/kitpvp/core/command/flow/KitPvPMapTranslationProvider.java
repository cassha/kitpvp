package dev.notcacha.kitpvp.core.command.flow;

import javax.inject.Inject;
import com.google.inject.Singleton;
import dev.notcacha.kitpvp.api.message.MessageHandler;
import me.fixeddev.commandflow.Namespace;
import me.fixeddev.commandflow.bukkit.BukkitCommandManager;
import me.fixeddev.commandflow.translator.DefaultMapTranslationProvider;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@Singleton
public class KitPvPMapTranslationProvider extends DefaultMapTranslationProvider {

    private final MessageHandler messageHandler;

    @Inject
    public KitPvPMapTranslationProvider(MessageHandler messageHandler) {
        this.messageHandler = messageHandler;
    }

    @Override
    public String getTranslation(Namespace namespace, String key) {

        if (key.equals("command.no-permission")) {
            Player player = (Player) namespace.getObject(CommandSender.class, BukkitCommandManager.SENDER_NAMESPACE);

            return messageHandler.getMessage("no-permissions");
        }

        return super.getTranslation(namespace, key);
    }

}
