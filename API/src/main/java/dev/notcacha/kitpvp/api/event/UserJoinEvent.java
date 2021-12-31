package dev.notcacha.kitpvp.api.event;

import dev.notcacha.kitpvp.api.user.User;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;

public class UserJoinEvent extends UserEvent {

    private static final HandlerList HANDLER_LIST = new HandlerList();

    public UserJoinEvent(User user, Player player) {
        super(user, player);
    }

    @Override
    public HandlerList getHandlers() {
        return HANDLER_LIST;
    }

    public static HandlerList getHandlerList() {
        return HANDLER_LIST;
    }
}
