package dev.notcacha.kitpvp.core.user;

import dev.notcacha.kitpvp.api.user.User;
import dev.notcacha.kitpvp.api.user.statistic.UserStatistic;
import dev.notcacha.kitpvp.core.statistic.CoreStatistic;
import dev.notcacha.kitpvp.core.user.statistic.CoreUserStatistic;

import java.util.HashMap;
import java.util.Map;

public class KitUser implements User {

    private final String id;
    private final String username;
    private final UserStatistic userStatistic;

    public KitUser(Map<String, Object> serializedUser) {
        this(
                serializedUser.get("_id").toString(),
                serializedUser.get("username").toString(),
                new CoreUserStatistic(
                        new CoreStatistic(
                                (Integer) serializedUser.get("kills")
                        ),
                        new CoreStatistic(
                                (Integer) serializedUser.get("deaths")
                        )
                )
        );
    }

    public KitUser(String id, String username) {
        this(
                id,
                username,
                new CoreUserStatistic()
        );
    }

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
    public Map<String, Object> serialize() {
        Map<String, Object> serialized = new HashMap<>();

        serialized.put("_id", id);
        serialized.put("username", username);
        serialized.put("kills", getStatistics().getKillsStatistic().getAmount());
        serialized.put("deaths", getStatistics().getDeathsStatistic().getAmount());

        return serialized;
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
