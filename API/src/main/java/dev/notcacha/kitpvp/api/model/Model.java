package dev.notcacha.kitpvp.api.model;

import org.bukkit.configuration.serialization.ConfigurationSerializable;

public interface Model extends ConfigurationSerializable {

    /**
     * @return The id from this model.
     */

    String getId();

}
