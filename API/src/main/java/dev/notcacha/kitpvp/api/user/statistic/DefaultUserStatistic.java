package dev.notcacha.kitpvp.api.user.statistic;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import dev.notcacha.kitpvp.api.statistic.Statistic;
import dev.notcacha.kitpvp.api.statistic.DefaultStatistic;

import java.beans.ConstructorProperties;

@JsonSerialize(as = UserStatistic.class)
public class DefaultUserStatistic implements UserStatistic {

    private final Statistic killsStatistic;
    private final Statistic deathsStatistic;

    public DefaultUserStatistic() {
        this(
                new DefaultStatistic(),
                new DefaultStatistic()
        );
    }

    @ConstructorProperties({
            "kills", "deaths"
    })
    public DefaultUserStatistic(Statistic killsStatistic, Statistic deathsStatistic) {
        this.killsStatistic = killsStatistic;
        this.deathsStatistic = deathsStatistic;
    }

    @Override
    public Statistic getKillsStatistic() {
        return killsStatistic;
    }

    @Override
    public Statistic getDeathsStatistic() {
        return deathsStatistic;
    }
}
