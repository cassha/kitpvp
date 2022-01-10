package dev.notcacha.kitpvp.core.command.flow;

import javax.inject.Inject;
import javax.inject.Singleton;

import me.fixeddev.commandflow.Namespace;
import me.fixeddev.commandflow.translator.DefaultMapTranslationProvider;
import me.yushust.message.MessageHandler;

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
            return messageHandler.getMessage("no-permissions");
        }

        return super.getTranslation(namespace, key);
    }

}
