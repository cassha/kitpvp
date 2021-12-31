package dev.notcacha.kitpvp.api.event;

import dev.notcacha.kitpvp.api.user.User;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;

public class UserLeaveEvent extends UserEvent {

    private static final HandlerList HANDLER_LIST = new HandlerList();

    public UserLeaveEvent(User user, Player player) {
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
