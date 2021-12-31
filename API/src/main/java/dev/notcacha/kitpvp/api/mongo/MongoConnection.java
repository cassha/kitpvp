package dev.notcacha.kitpvp.api.mongo;

import com.mongodb.client.MongoClient;

import java.io.Closeable;

public interface MongoConnection extends Closeable {

    /**
     * Open connection to MongoDB database.
     */

    void start();

    /**
     * @return Access to MongoClient instance.
     */

    MongoClient getClient();
}
