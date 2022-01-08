package dev.notcacha.kitpvp.api.mongo.configuration;

import com.mongodb.ConnectionString;
import com.mongodb.lang.Nullable;

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
     * @return The string mongo connection.
     */

    @Nullable
    String getStringConnection();

    /**
     * @return New ConnectionString instance using this properties.
     * */

    default ConnectionString toConnectionString() {
        return new ConnectionString(
                (getStringConnection() != null) ? getStringConnection() :
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
