package dev.notcacha.kitpvp.api.user.statistic;

import dev.notcacha.kitpvp.api.statistic.Statistic;

public interface UserStatistic {

    /**
     * @return The manager of kill from statistic.
     */
    Statistic getKillsStatistic();

    /**
     * @return The manager of death from statistic.
     */
    Statistic getDeathsStatistic();

}
