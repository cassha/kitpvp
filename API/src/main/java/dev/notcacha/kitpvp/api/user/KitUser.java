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
    private String language;

    public KitUser(String id, String username) {
        this(
                id,
                username,
                new DefaultUserStatistic(),
                "en"
        );
    }

    @ConstructorProperties({
            "_id", "username", "statistic", "language"
    })
    public KitUser(String id, String username, UserStatistic userStatistic, String language) {
        this.id = id;
        this.username = username;
        this.userStatistic = userStatistic;
        this.language = language;
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

    @Override
    public String getLanguage() {
        return this.language;
    }

    @Override
    public void setLanguage(String language) {
        this.language = language;
    }
}
