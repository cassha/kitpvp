package dev.notcacha.kitpvp.core.user.statistic;

import dev.notcacha.kitpvp.api.statistic.Statistic;
import dev.notcacha.kitpvp.api.user.statistic.UserStatistic;
import dev.notcacha.kitpvp.core.statistic.CoreStatistic;

public class CoreUserStatistic implements UserStatistic {

    private final Statistic killsStatistic;
    private final Statistic deathsStatistic;

    public CoreUserStatistic() {
        this(
                new CoreStatistic(),
                new CoreStatistic()
        );
    }

    public CoreUserStatistic(Statistic killsStatistic, Statistic deathsStatistic) {
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
