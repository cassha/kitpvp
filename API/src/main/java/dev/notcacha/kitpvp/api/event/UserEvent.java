package dev.notcacha.kitpvp.api.event;

import dev.notcacha.kitpvp.api.user.User;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;

public abstract class UserEvent extends Event {

    private final User user;
    private final Player player;

    public UserEvent(User user, Player player) {
        this.user = user;
        this.player = player;
    }

    public User getUser() {
        return user;
    }

    public Player getPlayer() {
        return player;
    }
}
