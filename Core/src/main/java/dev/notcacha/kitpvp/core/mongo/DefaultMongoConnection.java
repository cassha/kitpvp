package dev.notcacha.kitpvp.core.mongo;

import dev.notcacha.kitpvp.api.mongo.MongoConnection;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.MongoDriverInformation;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import dev.notcacha.kitpvp.api.mongo.configuration.MongoConfiguration;
import fr.javatic.mongo.jacksonCodec.JacksonCodecProvider;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bukkit.plugin.Plugin;

import javax.inject.Inject;
import java.io.IOException;
import java.util.logging.Level;

public class DefaultMongoConnection implements MongoConnection {

    @Inject private Plugin plugin;
    @Inject private MongoConfiguration mongoConfiguration;
    @Inject private ObjectMapper objectMapper;

    private MongoClient mongoClient;


    @Override
    public void start() {
        if (mongoClient != null) {
            plugin.getLogger().log(Level.WARNING, "The connection from mongodb is already open.");
            return;
        }

        ConnectionString connectionString = mongoConfiguration.toConnectionString();

        CodecRegistry codecRegistry = CodecRegistries.fromRegistries(MongoClientSettings.getDefaultCodecRegistry(),
                CodecRegistries.fromProviders(new JacksonCodecProvider(objectMapper)));

        MongoClientSettings mongoClientSettings = MongoClientSettings.builder()
                .codecRegistry(codecRegistry)
                .applyConnectionString(connectionString)
                .build();

        MongoDriverInformation mongoDriverInformation = MongoDriverInformation.builder().driverName("sync").build();
        mongoClient = MongoClients.create(
                mongoClientSettings,
                mongoDriverInformation
        );

        plugin.getLogger().log(Level.WARNING, "[Database] MongoDB connection has been open.");
    }

    @Override
    public MongoClient getClient() {
        synchronized (this) {
            if (mongoClient == null) {
                start();

                return getClient();
            }

            return mongoClient;
        }
    }

    @Override
    public void close() {
        mongoClient.close();
    }
}
