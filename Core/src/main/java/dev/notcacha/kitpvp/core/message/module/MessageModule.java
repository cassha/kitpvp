package dev.notcacha.kitpvp.core.message.module;

import me.yushust.inject.AbstractModule;
import dev.notcacha.kitpvp.api.message.MessageHandler;
import dev.notcacha.kitpvp.core.message.CoreMessageHandler;

public class MessageModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(MessageHandler.class).to(CoreMessageHandler.class).singleton();
    }
}
