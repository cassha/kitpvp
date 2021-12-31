package dev.notcacha.kitpvp.api.mongo.configuration;

import com.mongodb.ConnectionString;

public interface MongoConfiguration {

    String STRING_CONNECTION = "mongodb://%s:%s@%s:%s/?authSource=%s";

    /**
     * @return The username.
     */

    String getUsername();

    /**
     * @return The password.
     */

    String getPassword();

    /**
     * @return The hostname.
     */

    String getHost();

    /**
     * @return The port.
     */

    int getPort();

    /**
     * @return The database.
     */

    String getDatabase();

    /**
     * @return New ConnectionString instance using this properties.
     * */

    default ConnectionString toConnectionString() {
        return new ConnectionString(
                String.format(
                        STRING_CONNECTION,
                        getUsername(),
                        getPassword(),
                        getHost(),
                        getPort(),
                        getDatabase()
                )
        );

    }

}
