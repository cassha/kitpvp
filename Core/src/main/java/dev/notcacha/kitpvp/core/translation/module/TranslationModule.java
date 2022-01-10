package dev.notcacha.kitpvp.core.translation.module;

import dev.notcacha.kitpvp.core.translation.DefaultLanguageProvider;
import dev.notcacha.kitpvp.core.translation.interceptor.ArrowMessageInterceptor;
import dev.notcacha.kitpvp.core.translation.interceptor.ColorMessageInterceptor;
import dev.notcacha.kitpvp.core.translation.messenger.DefaultMessenger;
import me.yushust.inject.AbstractModule;
import me.yushust.inject.Provides;
import me.yushust.message.MessageHandler;
import me.yushust.message.MessageProvider;
import me.yushust.message.bukkit.BukkitMessageAdapt;
import me.yushust.message.source.MessageSource;
import me.yushust.message.source.MessageSourceDecorator;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import javax.inject.Singleton;

public class TranslationModule extends AbstractModule {

    @Provides
    @Singleton
    public MessageHandler provideMessageHandler(Plugin plugin, DefaultLanguageProvider languageProvider) {
        MessageSource source = MessageSourceDecorator.decorate(BukkitMessageAdapt.newYamlSource(plugin, "lang_%lang%.yml"))
                .addFallbackLanguage("en")
                .get();

        MessageProvider messageProvider = MessageProvider.create(
                source,
                config -> {
                    config.specify(Player.class)
                            .setLinguist(languageProvider)
                            .setMessageSender(new DefaultMessenger());

                    config.addInterceptor(new ArrowMessageInterceptor())
                            .addInterceptor(new ColorMessageInterceptor());
                }
        );

        return MessageHandler.of(messageProvider);
    }

}
