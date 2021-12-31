package dev.notcacha.kitpvp.core.mongo.module;

import me.yushust.inject.AbstractModule;
import dev.notcacha.kitpvp.api.mongo.MongoConnection;
import dev.notcacha.kitpvp.api.mongo.configuration.MongoConfiguration;
import dev.notcacha.kitpvp.core.mongo.DefaultMongoConnection;
import me.yushust.inject.Provides;
import org.bukkit.plugin.Plugin;

import javax.inject.Singleton;

public class MongoModule extends AbstractModule {

    @Provides
    @Singleton
    public MongoConfiguration provideMongoConfiguration(Plugin plugin) {
        return new MongoConfiguration() {

            @Override
            public String getUsername() {
                return plugin.getConfig().getString("mongo.username");
            }

            @Override
            public String getPassword() {
                return plugin.getConfig().getString("mongo.password");
            }

            @Override
            public String getHost() {
                return plugin.getConfig().getString("mongo.host");
            }

            @Override
            public int getPort() {
                return plugin.getConfig().getInt("mongo.port");
            }

            @Override
            public String getDatabase() {
                return plugin.getConfig().getString("mongo.database");
            }
        };
    }

    @Override
    protected void configure() {
        bind(MongoConnection.class).to(DefaultMongoConnection.class).singleton();
    }
}
