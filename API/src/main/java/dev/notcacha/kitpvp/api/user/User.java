package dev.notcacha.kitpvp.api.user;

import dev.notcacha.kitpvp.api.model.Model;
import dev.notcacha.kitpvp.api.user.statistic.UserStatistic;

public interface User extends Model {

    /**
     * @return The username of this user.
     */

    String getUsername();

    /**
     * @return The statistics manager of this user.
     */

    UserStatistic getStatistics();
}
