package dev.notcacha.kitpvp.api.event;

import dev.notcacha.kitpvp.api.user.User;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.Nullable;

public class UserDeathEvent extends UserEvent {

    private static final HandlerList HANDLER_LIST = new HandlerList();

    private final User killer;
    private final Player killerPlayer;

    public UserDeathEvent(User user, Player userPlayer, User killer, Player killerPlayer) {
        super(user, userPlayer);
        this.killer = killer;
        this.killerPlayer = killerPlayer;
    }

    @Override
    public HandlerList getHandlers() {
        return HANDLER_LIST;
    }

    public static HandlerList getHandlerList() {
        return HANDLER_LIST;
    }

    public @Nullable User getKiller() {
        return killer;
    }

    public @Nullable Player getKillerPlayer() {
        return killerPlayer;
    }
}
