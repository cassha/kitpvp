package dev.notcacha.kitpvp.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public interface Model {

    /**
     * @return The id from this model.
     */

    @JsonProperty("_id")
    String getId();

}
