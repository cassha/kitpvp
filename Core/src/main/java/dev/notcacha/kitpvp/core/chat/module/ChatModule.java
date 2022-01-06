package dev.notcacha.kitpvp.core.chat.module;

import dev.notcacha.kitpvp.api.chat.ChatManager;
import dev.notcacha.kitpvp.api.chat.formatter.ChatFormatter;
import dev.notcacha.kitpvp.core.chat.DefaultChatManager;
import dev.notcacha.kitpvp.core.chat.formatter.DefaultChatFormatter;
import me.yushust.inject.AbstractModule;

public class ChatModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(ChatFormatter.class).to(DefaultChatFormatter.class).singleton();
        bind(ChatManager.class).to(DefaultChatManager.class).singleton();
    }
}
