package dev.notcacha.kitpvp.api.user.statistic;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import dev.notcacha.kitpvp.api.statistic.Statistic;

@JsonDeserialize(as = DefaultUserStatistic.class)
public interface UserStatistic {

    /**
     * @return The manager of kill from statistic.
     */
    @JsonProperty("kills")
    Statistic getKillsStatistic();

    /**
     * @return The manager of death from statistic.
     */
    @JsonProperty("deaths")
    Statistic getDeathsStatistic();

}
