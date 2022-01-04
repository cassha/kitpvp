package dev.notcacha.kitpvp.api.user;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import dev.notcacha.kitpvp.api.user.statistic.DefaultUserStatistic;
import dev.notcacha.kitpvp.api.user.statistic.UserStatistic;

import java.beans.ConstructorProperties;

@JsonSerialize(as = User.class)
public class KitUser implements User {

    private final String id;
    private final String username;
    private final UserStatistic userStatistic;

    public KitUser(String id, String username) {
        this(
                id,
                username,
                new DefaultUserStatistic()
        );
    }

    @ConstructorProperties({
            "_id", "username", "statistic"
    })
    public KitUser(String id, String username, UserStatistic userStatistic) {
        this.id = id;
        this.username = username;
        this.userStatistic = userStatistic;
    }

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public UserStatistic getStatistics() {
        return this.userStatistic;
    }
}
